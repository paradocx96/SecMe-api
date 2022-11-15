/*
 * SSD - SecMe API
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 */
package com.secme.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * API Controller for Root
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 *
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:3000")
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
