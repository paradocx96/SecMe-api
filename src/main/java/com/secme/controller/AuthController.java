package com.secme.controller;

import com.secme.model.Auth;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    @GetMapping(value = "/public")
    public Auth publicEndpoint() {
        return new Auth("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Auth privateEndpoint() {
        return new Auth("All good. You are Authenticated.");
    }

    @GetMapping(value = "/scopeadmin")
    @PreAuthorize("hasAuthority('read:admin-post')")
    public Auth privateScopedAdminEndpoint() {
        return new Auth("All good. Role:Amin");
    }

    @GetMapping(value = "/scopemanager")
    @PreAuthorize("hasAuthority('read:manager-post')")
    public Auth privateScopedManagerEndpoint() {
        return new Auth("All good. Role:Manager");
    }

    @GetMapping(value = "/scopeworker")
    @PreAuthorize("hasAuthority('read:worker-post')")
    public Auth privateScopedWorkerEndpoint() {
        return new Auth("All good. Role:Worker");
    }
}