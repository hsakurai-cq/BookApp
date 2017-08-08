package com.hiromisakurai.bookapp;

public class UserResponse {

    private String request_token;
    private int user_id;

    public UserResponse(String request_token, int user_id) {
        this.request_token = request_token;
        this.user_id = user_id;
    }

    public String getRequestToken() {
        return this.request_token;
    }

    public int getUserId() {
        return this.user_id;
    }
}
