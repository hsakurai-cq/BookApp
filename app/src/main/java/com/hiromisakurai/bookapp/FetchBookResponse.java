package com.hiromisakurai.bookapp;

import java.util.List;

public class FetchBookResponse {

    public List<BookListItem> result;

    public FetchBookResponse(List<BookListItem> result) {
        this.result = result;
    }
}
