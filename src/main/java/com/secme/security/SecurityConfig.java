package com.secme.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.oauth2Login();

        //temporarily disabled csrf for testing
        //enable csrf tokens and remove the below line
        // http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers("/api/post/public").permitAll()
                .mvcMatchers("/api/messages/**").permitAll()
                .mvcMatchers("/api/files/**").permitAll()
                .mvcMatchers("/api/post/private").authenticated()
                .mvcMatchers("/api/post/scopeadmin").hasAuthority("create:file")
                .mvcMatchers("/api/post/scopemanager").hasAuthority("create:file")
                .mvcMatchers("/api/post/scopeworker").hasAuthority("create:message")
                .and().cors()
                .and().oauth2ResourceServer().jwt();
        return http.build();

//        http.authorizeRequests()
//                .antMatchers("api/post/scopeadmin", "api/post/scopemanager", "api/post/scopeworker")
//                .authenticated()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .cors()
//                .and()
//                .oauth2ResourceServer()
////                .authenticationEntryPoint(authenticationErrorHandler)
//                .jwt()
//                .decoder(jwtDecoder())
//                .jwtAuthenticationConverter(makePermissionsConverter());
//        return http.build();

    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, this::withAudience);
        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    private OAuth2TokenValidatorResult withAudience(final Jwt token) {
        OAuth2Error audienceError = new OAuth2Error(
                OAuth2ErrorCodes.INVALID_TOKEN,
                "The token was not issued for the given audience",
                "https://datatracker.ietf.org/doc/html/rfc6750#section-3.1"
        );

        return token.getAudience().contains(audience)
                ? OAuth2TokenValidatorResult.success()
                : OAuth2TokenValidatorResult.failure(audienceError);
    }

    private JwtAuthenticationConverter makePermissionsConverter() {
        JwtGrantedAuthoritiesConverter jwtAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtAuthoritiesConverter.setAuthoritiesClaimName("permissions");
        jwtAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(jwtAuthoritiesConverter);

        return jwtAuthConverter;
    }
}