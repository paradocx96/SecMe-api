package com.secme.model;

public class Auth {
    private final String message;

    public Auth(String message) {
        this.message = message;
    }

    public String getPost() {
        return this.message;
    }
}