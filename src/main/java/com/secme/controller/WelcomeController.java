package com.secme.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
