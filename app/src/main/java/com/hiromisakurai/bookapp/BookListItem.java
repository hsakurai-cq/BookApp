package com.hiromisakurai.bookapp;

import android.graphics.Bitmap;

public class BookListItem {

    private Bitmap image;
    private String title;
    private String price;
    private String purchaseDate;

    public BookListItem() {};

    public BookListItem(Bitmap image, String title, String price, String purchaseDate) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}
