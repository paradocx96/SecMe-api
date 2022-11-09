/*
 * SSD - SecMe API
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 */
package com.secme.model;

/*
 * Model Class for Authentication Test
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 *
 * */
public class Auth {
    private final String message;

    public Auth(String message) {
        this.message = message;
    }

    public String getPost() {
        return this.message;
    }
}