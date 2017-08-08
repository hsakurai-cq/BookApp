package com.hiromisakurai.bookapp;

public class EditBookRequest {

    private String image_data;
    private String name;
    private int price;
    private String purchase_date;

    public EditBookRequest(String imageData, String title, int price, String purchaseDate) {
        this.image_data = imageData;
        this.name = title;
        this.price = price;
        this.purchase_date = purchaseDate;
    }
}
