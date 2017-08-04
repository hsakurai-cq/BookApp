package com.hiromisakurai.bookapp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        BookListItem bookListItem = mBooks.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(this.getContext()).load(bookListItem.getImage()).into(imageView);

        TextView title = (TextView) view.findViewById(R.id.bookTitle);
        title.setText(bookListItem.getTitle());

        TextView price = (TextView) view.findViewById(R.id.bookPrice);
        price.setText(String.valueOf(bookListItem.getPrice()));

        TextView dateEditText = (TextView) view.findViewById(R.id.purchaseDate);
        String time = bookListItem.getPurchaseDate();
        Log.i("time", String.valueOf(time));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        try {
            if (TextUtils.isEmpty(time)) {
                time = "2000-01-01";
                dateEditText.setText(time);
            }else {
                Date date = dateFormat.parse(time);
                String stringFromDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                dateEditText.setText(stringFromDate);
            }
        } catch (ParseException e) {
            Log.i("Parse error", String.valueOf(e));
        }

        return view;
    }
}
