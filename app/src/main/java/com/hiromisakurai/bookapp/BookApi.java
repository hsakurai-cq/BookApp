package com.hiromisakurai.bookapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookApi {

    //@Headers("Content-Type: application/json")
    @POST("/books")
    Call<BookResponse> addBook(@Body Book book);
//    Call<JsonObject> addBook(@Body Book book, @Header("Authorization") String authorization);
}
