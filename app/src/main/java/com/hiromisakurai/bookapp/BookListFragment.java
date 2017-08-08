package com.hiromisakurai.bookapp;


import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class BookListFragment extends Fragment {
    private int page = 180;
    ListView listView = getActivity().findViewById(R.id.list_book);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_list);


        SharedPreferences pref = getActivity().getSharedPreferences("DataStore", MODE_PRIVATE);
        int userId = pref.getInt(Constants.PrefKey.USER_ID, 0);

        BookApi api = Client.setUp().create(BookApi.class);
        Call<FetchBookResponse> call = api.fetchBook(userId, "0-"+ String.valueOf(page));
        page++;
        enqueue(call);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                EditBookFragment edit = new EditBookFragment();
                ListView listView = (ListView)parent;
                BookListItem book = (BookListItem) listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putInt(Constants.BundleKey.BUNDLE_ID, book.getId());
                bundle.putString(Constants.BundleKey.BUNDLE_IMAGE, book.getImage());
                bundle.putString(Constants.BundleKey.BUNDLE_TITLE, book.getTitle());
                bundle.putInt(Constants.BundleKey.BUNDLE_PRICE, book.getPrice());
                String date = DateUtil.changeFormat(book.getPurchaseDate());
                bundle.putString(Constants.BundleKey.BUNDLE_DATE, date);
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
                Log.i("Load More Button", "onClick");

                SharedPreferences pref = getActivity().getSharedPreferences(Constants.PrefKey.DATA_STORE, MODE_PRIVATE);
                int userId = pref.getInt(Constants.PrefKey.USER_ID, 0);

                //API通信開始
                BookApi api = Client.setUp().create(BookApi.class);
                Call<FetchBookResponse> call = api.fetchBook(userId, "0-"+ String.valueOf(page));
                page++;
                enqueue(call);
            }
        });
    }

    private void enqueue(Call<FetchBookResponse> call) {
        call.enqueue(new Callback<FetchBookResponse>() {
            @Override
            public void onResponse(Call<FetchBookResponse> call, Response<FetchBookResponse> response) {
                if (response.isSuccessful()) {
                    List<BookListItem> listItems = response.body().result;
                    CustomBookListAdapter adapter = new CustomBookListAdapter(getContext(), R.layout.custom_book_list, listItems);
                    listView.setAdapter(adapter);
                    listView.setSelection(listItems.size());
                    return;
                }
                Log.i("Cannot Fetch Book", String.valueOf(response));
            }
            @Override
            public void onFailure(Call<FetchBookResponse> call, Throwable t) {
                Log.i("onFailure", String.valueOf(t));
            }
        });
    }
}
