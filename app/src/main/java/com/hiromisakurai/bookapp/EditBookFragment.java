package com.hiromisakurai.bookapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static com.hiromisakurai.bookapp.R.id.bookImage;

public class EditBookFragment extends Fragment {

    private static final int READ_REQUEST_CODE = 42;
    ImageView imageView;

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
        Bitmap img = bundle.getParcelable("image");
        Log.i("image bitmap", String.valueOf(img));
        String title = bundle.getString("title");
        String price = bundle.getString("price");
        String purchaseDate = bundle.getString("purchaseDate");

        ImageView iv = (ImageView)view.findViewById(bookImage);
        EditText titleEdit = (EditText)view.findViewById(R.id.bookTitleEditText);
        EditText priceEdit = (EditText)view.findViewById(R.id.bookPriceEditText);
        EditText dateEdit = (EditText)view.findViewById(R.id.purchaseDateEditText);

        iv.setImageBitmap(img);
        titleEdit.setText(title);
        priceEdit.setText(price);
        dateEdit.setText(purchaseDate);

        Button btn = (Button)view.findViewById(R.id.button_saveImage);
        imageView = (ImageView)view.findViewById(R.id.bookImage);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");

                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });
    }

    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText)getActivity().findViewById(R.id.purchaseDateEditText);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
