package com.secme.controller;

import com.secme.model.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/post")
public class PostController {

    @GetMapping(value = "/public")
    public Post publicEndpoint() {
        return new Post("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Post privateEndpoint() {
        return new Post("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/scopeadmin")
    @PreAuthorize("hasAuthority('read:admin-post')")
    public Post privateScopedAdminEndpoint() {
        return new Post("All good. Role:Amin");
    }

    @GetMapping(value = "/scopemanager")
    @PreAuthorize("hasAuthority('read:manager-post')")
    public Post privateScopedManagerEndpoint() {
        return new Post("All good. Role:Manager");
    }

    @GetMapping(value = "/scopeworker")
    @PreAuthorize("hasAuthority('read:worker-post')")
    public Post privateScopedWorkerEndpoint() {
        return new Post("All good. Role:Worker");
    }
}