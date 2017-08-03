package com.hiromisakurai.bookapp;


import android.content.SharedPreferences;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class BookListFragment extends Fragment {
    private static final String BASE_URL = "http://54.238.252.116";

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

        SharedPreferences pref = getActivity().getSharedPreferences("DataStore", MODE_PRIVATE);
        int userId = pref.getInt(Constants.PrefKey.USER_ID, 0);

        //API通信開始
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookApi api = retrofit.create(BookApi.class);
        Call<FetchBookResponse> call = api.fetchBook(userId, "0-200");
        call.enqueue(new Callback<FetchBookResponse>() {
            @Override
            public void onResponse(Call<FetchBookResponse> call, Response<FetchBookResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("Success, result is ", String.valueOf(response.body()));
                } else {
                    Log.i("Cannot Fetch Book", String.valueOf(response));
                }
            }

            @Override
            public void onFailure(Call<FetchBookResponse> call, Throwable t) {
                Log.i("onFailure", String.valueOf(t));
            }
        });

        List<BookListItem> listItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            BookListItem item = new BookListItem(bmp, "Book title No. " + String.valueOf(i), "book price " + String.valueOf(i), "2017-07- 0" + String.valueOf(i));
            listItems.add(item);
        }

        CustomBookListAdapter adapter = new CustomBookListAdapter(this.getContext(), R.layout.custom_book_list, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                EditBookFragment edit = new EditBookFragment();
                ListView listView = (ListView)parent;
                BookListItem book = (BookListItem) listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.BundleKey.BUNDLE_IMAGE, book.getImage());
                bundle.putString(Constants.BundleKey.BUNDLE_TITLE, book.getTitle());
                bundle.putString(Constants.BundleKey.BUNDLE_PRICE, book.getPrice());
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
