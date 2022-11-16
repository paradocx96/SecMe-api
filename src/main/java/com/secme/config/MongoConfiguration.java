package com.secme.config;

/*
* IT19014128 - A.M.W.W.R.L. Wataketiya
*
* Reference - https://boot-microservices.hashnode.dev/spring-data-mongodb-configuring-secure-connection-between-spring-boot-and-mongodb-over-tls-using-certificates
*
* The approach taken is similar to the reference mentioned above
*
* Mongo autoconfiguration is disabled and is configured in this file with TLS
* Takes in MongoDBUri, DB name, caFileName and certificateKeyFileName from application.properties
* */

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Configuration
@EnableMongoRepositories("com.secme.repository")
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

    @Value("${server.ssl.certificate}")
    private String caFileName;

    @Value("${server.ssl.certificate-private-key}")
    private String certificateKeyFileName;


    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoDbUri);
        SSLContext sslContext = null;
        try {
            sslContext = createSSLContext();
        } catch (Exception e) {
            System.out.println("Exception in creating SSL Context : " + e);
            System.out.println("Server Cert : " + caFileName);
            return null;
        }
        SSLContext finalSslContext = sslContext;
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSslSettings(builder -> {
                    builder.enabled(true);
                    builder.context(finalSslContext);
                    builder.invalidHostNameAllowed(true);
                })
                .build();
        MongoClient client = MongoClients.create(settings);
        return client;
    }

    /*
    * Creates and returns and SSL context
    * Uses Bouncy castle libraries
    * */
    public SSLContext createSSLContext() throws Exception {
        // root CA
        TrustManagerFactory tmf;
        ClassPathResource resource = new ClassPathResource("static/"+caFileName);
        System.out.println("ClassPathResource : " + resource);
        InputStream is = resource.getInputStream();
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate caCert = (X509Certificate) cf.generateCertificate(is);
        tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null); // You don't need the KeyStore instance to come from a file.
        ks.setCertificateEntry("caCert", caCert);
        tmf.init(ks);

        // client key
        KeyManagerFactory keyFac;
        SSLContext sslContext = null;
        try {
            resource = new ClassPathResource("static/"+certificateKeyFileName);
            is = resource.getInputStream();
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null); // needs to be initialised, otherwise throws exception

            @SuppressWarnings("resource")
            PEMParser pemParser = new PEMParser(new InputStreamReader(is));
            PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
            KeyPair kp = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
            PrivateKey privateKey = kp.getPrivate();
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            X509CertificateHolder certificateHolder = (X509CertificateHolder) pemParser.readObject();
            X509Certificate certificate = certConverter.getCertificate(certificateHolder);
            keystore.setKeyEntry("alias", privateKey, "".toCharArray(), new Certificate[]{certificate});
            keyFac = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFac.init(keystore, "".toCharArray());

            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyFac.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            System.out.println("Error in creating ssl Context : " +e);
        } finally {
            is.close();
        }
        return sslContext;
    }


    @Override
    protected String getDatabaseName() {
        return this.mongoDbName;
    }
}
