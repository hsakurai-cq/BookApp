package com.hiromisakurai.bookapp;

import android.graphics.Bitmap;


public class Book {
    private Bitmap mBookImage = null;
    private String mBookTitle = null;
    private String mBookPrice = null;
    private String mPurchaseDate = null;

    public Book() {};

    public Book(Bitmap bookImage, String bookTitle, String bookPrice, String purchaseDate) {
        mBookImage = bookImage;
        mBookTitle = bookTitle;
        mBookPrice = bookPrice;
        mPurchaseDate = purchaseDate;
    }

    public void setBookImage(Bitmap bookImage) {
        mBookImage = bookImage;
    }

    public void setBookTitle(String bookTitle) {
        mBookTitle = bookTitle;
    }

    public void setBookPrice(String bookPrice) {
        mBookPrice = bookPrice;
    }

    public void setPurchaseDate(String purchaseDate) {
        mPurchaseDate = purchaseDate;
    }

    public Bitmap getBookImage() {
        return mBookImage;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getBookPrice() {
        return mBookPrice;
    }

    public String getPurchaseDate() {
        return mPurchaseDate;
    }


}
