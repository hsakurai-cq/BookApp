package com.hiromisakurai.bookapp;

public class Book {

    private int user_id;
    private String image_data;
    private String name;
    private int price;
    private String purchase_date;

    public Book(int userId, String bookImage, String bookTitle, int bookPrice, String purchaseDate) {
        this.user_id = userId;
        this.image_data = bookImage;
        this.name = bookTitle;
        this.price = bookPrice;
        this.purchase_date = purchaseDate;
    }
}
