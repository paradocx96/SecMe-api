package com.secme.controller;

import com.secme.model.Auth;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller Class for Authentication Test
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 *
 * */
@RestController
@RequestMapping(path = "api/auth")
@CrossOrigin(origins = "https://localhost:3000", allowedHeaders = "*", exposedHeaders = "*")
public class AuthController {

    // Endpoint for Public Access - api/auth/public - GET
    @GetMapping(value = "/public")
    public Auth publicEndpoint() {
        return new Auth("Do not need to be authenticated. (api/auth/public)");
    }

    // Endpoint for Authenticated Access - api/auth/private - GET
    @GetMapping(value = "/private")
    public Auth privateEndpoint() {
        return new Auth("Authenticated. (api/auth/private)");
    }

    // Endpoint for Admin Access - api/auth/admin - GET
    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('read:admin-post')")
    public Auth privateScopedAdminEndpoint() {
        return new Auth("Authenticated. Role:Amin");
    }

    // Endpoint for Manager Access - api/auth/manager - GET
    @GetMapping(value = "/manager")
    @PreAuthorize("hasAuthority('read:manager-post')")
    public Auth privateScopedManagerEndpoint() {
        return new Auth("Authenticated. Role:Manager");
    }

    // Endpoint for Worker Access - api/auth/worker - GET
    @GetMapping(value = "/worker")
    @PreAuthorize("hasAuthority('read:worker-post')")
    public Auth privateScopedWorkerEndpoint() {
        return new Auth("Authenticated. Role:Worker");
    }
}