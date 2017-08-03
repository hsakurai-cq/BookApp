package com.hiromisakurai.bookapp;

public class BookResponse {

    private int book_id;

    public BookResponse(int bookId) {
        this.book_id = bookId;
    }

    public int getBookId() {
        return this.book_id;
    }
}
