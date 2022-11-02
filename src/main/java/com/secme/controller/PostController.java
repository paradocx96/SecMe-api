package com.secme.controller;

import com.secme.model.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/post", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PostController {

    @GetMapping(value = "/public")
    public Post publicEndpoint() {
        return new Post("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Post privateEndpoint() {
        return new Post("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    public Post privateScopedEndpoint() {
        return new Post("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }
}