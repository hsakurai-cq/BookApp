package com.hiromisakurai.bookapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import static com.hiromisakurai.bookapp.R.id.bookImage;

public class EditBookFragment extends Fragment implements OnDateDialogClickListener {

    private static final int READ_REQUEST_CODE = 42;
    private static final String IMAGE_TYPE = "image/*";
    private static final String DIALOG_KEY = "DatePicker";
    private ImageView imageView;
    private Button saveImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_edit_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_edit);

        Bundle bundle = getArguments();
        Bitmap img = bundle.getParcelable(BookListFragment.BUNDLE_IMAGE);
        //Log.i("image bitmap", String.valueOf(img));
        String title = bundle.getString(BookListFragment.BUNDLE_TITLE);
        String price = bundle.getString(BookListFragment.BUNDLE_PRICE);
        String purchaseDate = bundle.getString(BookListFragment.BUNDLE_DATE);

        ImageView iv = (ImageView)view.findViewById(bookImage);
        EditText titleEdit = (EditText)view.findViewById(R.id.bookTitleEditText);
        EditText priceEdit = (EditText)view.findViewById(R.id.bookPriceEditText);
        EditText dateEdit = (EditText)view.findViewById(R.id.purchaseDateEditText);

        iv.setImageBitmap(img);
        titleEdit.setText(title);
        priceEdit.setText(price);
        dateEdit.setText(purchaseDate);

        saveImageButton = (Button)view.findViewById(R.id.button_saveImage);
        imageView = (ImageView)view.findViewById(R.id.bookImage);

        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType(IMAGE_TYPE);
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit, menu);
        menu.findItem(R.id.action_add).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:
                ImageView bookIV = (ImageView)getActivity().findViewById(R.id.bookImage);
                EditText titleET = (EditText)getActivity().findViewById(R.id.bookTitleEditText);
                EditText priceET = (EditText)getActivity().findViewById(R.id.bookPriceEditText);
                EditText dateET = (EditText)getActivity().findViewById(R.id.purchaseDateEditText);

                Drawable bookImg = bookIV.getDrawable();
                String titleStr = titleET.getText().toString();
                String priceStr = priceET.getText().toString();
                String dateStr = dateET.getText().toString();

                boolean validateResult = ValidationUtil.validateForm(bookImg, titleStr, priceStr, dateStr, getActivity());
                if (validateResult) {
                    //ToDo 書籍編集処理
                    Log.i("Validation result", String.valueOf(validateResult));
                }
                return true;

            case R.id.action_back:
                getFragmentManager().popBackStack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText)getActivity().findViewById(R.id.purchaseDateEditText);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog();
                dialog.setListener(EditBookFragment.this);
                FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                dialog.show(ft, DIALOG_KEY);
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

    @Override
    public void onDateDialogClickListener(int year, int month, int day) {
        EditText editText = (EditText) getActivity().findViewById(R.id.purchaseDateEditText);
        String dateStr = year + "-" + (month + 1) + "-" + day;
        editText.setText(dateStr);
    }
}
