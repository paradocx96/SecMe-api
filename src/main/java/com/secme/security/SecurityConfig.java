package com.secme.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationErrorHandler authenticationErrorHandler;

    private final OAuth2ResourceServerProperties resourceServerProps;

    @Value("${auth0.audience}")
    private String audience;

    @Override
    public void configure(final WebSecurity web) throws Exception {
        String exclusionRegex = String.format(
                "^(?!%s|%s|%s).*$",
                "/api/post/",
                "/api/files/",
                "/api/messages/"
        );

        web.ignoring().regexMatchers(exclusionRegex);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/post/public").permitAll()
                .mvcMatchers("/api/post/private").permitAll()
                .antMatchers(
                        "/api/post/**",
                        "/api/files/**",
                        "/api/messages/**"
                )
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .cors()
                .and()
                .oauth2ResourceServer()
                .authenticationEntryPoint(authenticationErrorHandler)
                .jwt()
                .decoder(makeJwtDecoder())
                .jwtAuthenticationConverter(makePermissionsConverter());
    }

    private JwtDecoder makeJwtDecoder() {
        String issuer = resourceServerProps.getJwt().getIssuerUri();
        NimbusJwtDecoder decoder = JwtDecoders.<NimbusJwtDecoder>fromIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> tokenValidator = new DelegatingOAuth2TokenValidator<>(withIssuer, this::withAudience);

        decoder.setJwtValidator(tokenValidator);
        return decoder;
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
