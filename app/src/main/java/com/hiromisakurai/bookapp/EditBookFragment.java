package com.hiromisakurai.bookapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hiromisakurai.bookapp.AddBookActivity.encodeToBase64;

public class EditBookFragment extends Fragment implements OnDateDialogClickListener {
    private static final int READ_REQUEST_CODE = 42;
    private static final String IMAGE_TYPE = "image/*";
    private static final String DIALOG_KEY = "DatePicker";

    private ImageView imageView;
    private EditText titleEditText;
    private EditText priceEditText;
    private EditText dateEditText;
    private int bookId;

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
        //Bitmap img = bundle.getParcelable(Constants.BundleKey.BUNDLE_IMAGE);
        //Log.i("image bitmap", String.valueOf(img));

        bookId = bundle.getInt(Constants.BundleKey.BUNDLE_ID);
        String title = bundle.getString(Constants.BundleKey.BUNDLE_TITLE);
        int price = bundle.getInt(Constants.BundleKey.BUNDLE_PRICE);
        String purchaseDate = bundle.getString(Constants.BundleKey.BUNDLE_DATE);

        imageView = (ImageView)view.findViewById(R.id.bookImage);
        titleEditText = (EditText)view.findViewById(R.id.bookTitleEditText);
        priceEditText = (EditText)view.findViewById(R.id.bookPriceEditText);
        dateEditText  = (EditText)view.findViewById(R.id.purchaseDateEditText);

        Glide.with(getActivity()).load(bundle.getString(Constants.BundleKey.BUNDLE_IMAGE)).into(imageView);
        titleEditText.setText(title);
        priceEditText.setText(String.valueOf(price));
        dateEditText.setText(purchaseDate);

        Button saveImageButton = (Button)view.findViewById(R.id.button_saveImage);
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
                Log.i("action_edit", String.valueOf(item.getItemId()));

                Drawable bookImg = imageView.getDrawable();
                String titleStr = titleEditText.getText().toString();
                String priceStr = priceEditText.getText().toString();
                String dateStr = dateEditText.getText().toString();

                String errorMessageString = ValidationUtil.validateForm(bookImg, titleStr, priceStr, dateStr, getActivity());
                boolean valid = TextUtils.isEmpty(errorMessageString);
                if (valid) {
                    Log.i("Edit Book validation", "OK");

                    int priceInt = Integer.parseInt(priceStr);
                    Bitmap bitmapImage = ((BitmapDrawable) bookImg).getBitmap();
                    String decoded = encodeToBase64(bitmapImage);

                    BookApi api = Client.setUp().create(BookApi.class);
                    Call<JsonObject> call = api.editBook(bookId, new EditBookRequest(decoded, titleStr, priceInt, dateStr));
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()) {
                                Log.i("Success, book_id is ", String.valueOf(response.body()));
                                getFragmentManager().popBackStack();
                            } else {
                                Log.i("Cannot Add Book", String.valueOf(response));
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.i("onFailure", String.valueOf(t));
                        }
                    });

                } else {
                    ErrorDialogUtil.showDialog(errorMessageString, getActivity());
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
