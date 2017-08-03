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

    public void setBookImage(String bookImage) {
        image_data = bookImage;
    }

    public void setBookTitle(String bookTitle) {
        name = bookTitle;
    }

    public void setBookPrice(int bookPrice) {
        price = bookPrice;
    }

    public void setPurchaseDate(String purchaseDate) {
        purchaseDate = purchaseDate;
    }

    public int getUserId() {
        return user_id;
    }

    public String getBookImage() {
        return image_data;
    }

    public String getBookTitle() {
        return name;
    }

    public int getBookPrice() {
        return price;
    }

    public String getPurchaseDate() {
        return purchase_date;
    }
}
