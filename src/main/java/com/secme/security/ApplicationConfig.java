///*
// * SSD - SecMe API
// *
// * @author IT19180526 - S.A.N.L.D. Chandrasiri
// * @version 1.0
// */
//package com.secme.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///*
// * Application Configuration for CORS
// *
// * @author IT19180526 - S.A.N.L.D. Chandrasiri
// * @version 1.0
// *
// * */
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig implements WebMvcConfigurer {
//
//    // Client Original URL
//    @Value("${client.original.url}")
//    private String client_url;
//
//    // Allowing CORS for Client Original URL
//    @Override
//    public void addCorsMappings(final CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(client_url)
//                .allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE)
//                .allowedMethods(HttpMethod.GET.name())
//                .maxAge(86400);
//    }
//}
