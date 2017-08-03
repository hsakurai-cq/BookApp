package com.hiromisakurai.bookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CustomBookListAdapter extends ArrayAdapter<BookListItem> {

    private int mResource;
    private List<BookListItem> mBooks;
    private LayoutInflater mInflater;

    public CustomBookListAdapter(Context context, int resource, List<BookListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mBooks = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView !=null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        BookListItem book = mBooks.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageBitmap(book.getImage());

        TextView title = (TextView) view.findViewById(R.id.bookTitle);
        title.setText(book.getTitle());

        TextView price = (TextView) view.findViewById(R.id.bookPrice);
        price.setText(book.getPrice());

        TextView date = (TextView) view.findViewById(R.id.purchaseDate);
        date.setText(book.getPurchaseDate());

        return view;
    }
}
