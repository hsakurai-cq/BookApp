package com.hiromisakurai.bookapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBookFragment extends Fragment {

    public EditBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolBarTitle = (TextView)getActivity().findViewById(R.id.toolbar_main_title);
        Button button = (Button)getActivity().findViewById(R.id.button_add);
        toolBarTitle.setText(R.string.toolbar_title_edit);
        button.setText(R.string.toolbar_button_save);

        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String price = bundle.getString("price");
        String purchaseDate = bundle.getString("purchaseDate");
        EditText titleEdit = (EditText)view.findViewById(R.id.bookTitleEditText);
        EditText priceEdit = (EditText)view.findViewById(R.id.bookPriceEditText);
        EditText dateEdit = (EditText)view.findViewById(R.id.purchaseDateEditText);
        titleEdit.setText(title);
        priceEdit.setText(price);
        dateEdit.setText(purchaseDate);
        //Log.i("edit fragment title ", title);
    }
}
