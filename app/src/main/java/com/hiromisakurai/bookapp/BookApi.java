package com.hiromisakurai.bookapp;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApi {

    @GET("/books")
    Call<FetchBookResponse> fetchBook(@Query("user_id") int userId, @Query("page") String page);

    //@Headers("Content-Type: application/json")
    @POST("/books")
    Call<JsonObject> addBook(@Body Book book);
    //Call<BookResponse> addBook(@Body Book book);

    @PATCH("/books{id}")
    Call<BookResponse> editBook(@Path("id") int bookId, @Body EditBookRequest editBookRequest);
}
