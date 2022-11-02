package com.secme.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class WelcomeController {

    @GetMapping
    public JSONObject welcomeEndpoint() {
        JSONObject obj = new JSONObject();
        obj.put("success", true);
        obj.put("message", "SecMe API Sever Running!");
        obj.toString();
        return obj;
    }
}
