package com.hiromisakurai.bookapp;

import android.graphics.Bitmap;


public class Book {
    private Bitmap image = null;
    private String title = null;
    private String price = null;
    private String date = null;

    public Book() {};

    public Book(Bitmap bookImage, String bookTitle, String bookPrice, String purchaseDate) {
        image = bookImage;
        title = bookTitle;
        price = bookPrice;
        date = purchaseDate;
    }

    public void setBookImage(Bitmap bookImage) {
        image = bookImage;
    }

    public void setBookTitle(String bookTitle) {
        title = bookTitle;
    }

    public void setBookPrice(String bookPrice) {
        price = bookPrice;
    }

    public void setPurchaseDate(String purchaseDate) {
        date = purchaseDate;
    }

    public Bitmap getBookImage() {
        return image;
    }

    public String getBookTitle() {
        return title;
    }

    public String getBookPrice() {
        return price;
    }

    public String getPurchaseDate() {
        return date;
    }


}
