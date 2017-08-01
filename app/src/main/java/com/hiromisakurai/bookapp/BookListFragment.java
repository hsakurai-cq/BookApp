package com.hiromisakurai.bookapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class BookListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_list);

        ListView listView = view.findViewById(R.id.list_book);

        List<Book> listItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            Book book = new Book(bmp, "Book title No. " + String.valueOf(i), "book price " + String.valueOf(i), "2017-07- 0" + String.valueOf(i));
            listItems.add(book);
        }

        CustomBookListAdapter adapter = new CustomBookListAdapter(this.getContext(), R.layout.custom_book_list, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                EditBookFragment edit = new EditBookFragment();
                ListView listView = (ListView)parent;
                Book book = (Book)listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.BundleKey.BUNDLE_IMAGE, book.getBookImage());
                bundle.putString(Constants.BundleKey.BUNDLE_TITLE, book.getBookTitle());
                bundle.putString(Constants.BundleKey.BUNDLE_PRICE, book.getBookPrice());
                bundle.putString(Constants.BundleKey.BUNDLE_DATE, book.getPurchaseDate());
                edit.setArguments(bundle);
                transaction.replace(R.id.fragment_container, edit);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Button loadMoreButton =(Button)view.findViewById(R.id.loadMoreButton);
        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo ListView更新処理
                Log.i("Load More Button", "onClick");
            }
        });
    }
}
