package com.hiromisakurai.bookapp;

public class UserRequest {

    private String email;
    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequest(User userData) {
        this.email = userData.getEmail();
        this.password = userData.getPassword();
    }
}
