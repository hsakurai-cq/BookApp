package com.hiromisakurai.bookapp;

public class BookListItem {

    private int id;
    private String image_url;
    private String name;
    private int price;
    private String purchase_date;

    public BookListItem() {};

    public BookListItem(int id, String image, String title, int price, String purchaseDate) {
        this.id = id;
        this.image_url = image;
        this.name = title;
        this.price = price;
        this.purchase_date = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image_url;
    }

    public String getTitle() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPurchaseDate() {
        return purchase_date;
    }
}
