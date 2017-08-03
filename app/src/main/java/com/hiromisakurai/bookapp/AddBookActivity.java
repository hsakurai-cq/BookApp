package com.hiromisakurai.bookapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBookActivity extends AppCompatActivity implements OnDateDialogClickListener {
    private static final String BASE_URL = "http://54.238.252.116";
    private static final int READ_REQUEST_CODE = 42;
    private static final String DIALOG_KEY = "DatePicker";
    private static final String IMAGE_TYPE = "image/*";

    private ImageView imageView;
    private Button saveImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        saveImageButton = (Button)findViewById(R.id.button_saveImage);
        imageView = (ImageView)findViewById(R.id.bookImage);

        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType(IMAGE_TYPE);
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_upload:
                ImageView bookIV = (ImageView) findViewById(R.id.bookImage);
                EditText titleET = (EditText) findViewById(R.id.bookTitleEditText);
                EditText priceET = (EditText) findViewById(R.id.bookPriceEditText);
                EditText dateET = (EditText) findViewById(R.id.purchaseDateEditText);

                Drawable bookImg = bookIV.getDrawable();
                String titleStr = titleET.getText().toString();
                String priceStr = priceET.getText().toString();

                String dateStr = dateET.getText().toString();



                String errorMessageString = ValidationUtil.validateForm(bookImg, titleStr, priceStr, dateStr, AddBookActivity.this);
                boolean valid = TextUtils.isEmpty(errorMessageString);
                if (valid) {
                    Log.i("Add Book validation", "OK");
                    int priceInt = Integer.parseInt(priceStr);
                    Bitmap bitmapImage = ((BitmapDrawable) bookImg).getBitmap();
                    String decoded = encodeToBase64(bitmapImage);
                    Log.i("decoded", String.valueOf(decoded));

                    SharedPreferences pref = getSharedPreferences("DataStore", MODE_PRIVATE);
                    int userId = pref.getInt(Constants.PrefKey.USER_ID, 0);
                    //String token = pref.getString(Constants.PrefKey.REQUEST_TOKEN, null);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    BookApi api = retrofit.create(BookApi.class);
                    Call<BookResponse> call = api.addBook(new Book(userId, decoded, titleStr, priceInt, dateStr));
                    call.enqueue(new Callback<BookResponse>() {
                        @Override
                        public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                            if (response.isSuccessful()) {
                                Log.i("Success, book_id is ", String.valueOf(response.body().getBookId()));
                                finish();
                            } else {
                                Log.i("Cannot Add Book", String.valueOf(response));
                            }
                        }
                        @Override
                        public void onFailure(Call<BookResponse> call, Throwable t) {
                            Log.i("onFailure", String.valueOf(t));
                        }
                    });
                } else {
                    ErrorDialogUtil.showDialog(errorMessageString, AddBookActivity.this);
                }
                return true;

            case R.id.action_back:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText)findViewById(R.id.purchaseDateEditText);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog();
                dialog.setListener(AddBookActivity.this);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, DIALOG_KEY);
            }
        });
    }

    @Override
    public void onDateDialogClickListener(int year, int month, int day) {
        EditText editText = (EditText) findViewById(R.id.purchaseDateEditText);
        String dateStr = year + "-" + (month + 1) + "-" + day;
        editText.setText(dateStr);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] b = bos.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }
}
