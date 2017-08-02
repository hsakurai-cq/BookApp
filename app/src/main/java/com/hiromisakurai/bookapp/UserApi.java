package com.hiromisakurai.bookapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {

    @Headers("Content-Type: application/json")
    @POST("/signup")
    Call<UserResponse> signUp(@Body User user);
}
