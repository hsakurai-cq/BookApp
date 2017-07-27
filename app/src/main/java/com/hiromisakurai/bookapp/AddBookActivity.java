package com.hiromisakurai.bookapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class AddBookActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    Button addButton;
    ImageView imageView;
    Button saveImageButton;
    EditText txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addButton = (Button)findViewById(R.id.buttonSave);
        saveImageButton = (Button)findViewById(R.id.button_saveImage);
        imageView = (ImageView)findViewById(R.id.bookImage);

        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");

                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView bookIV = (ImageView)findViewById(R.id.bookImage);
                EditText titleET = (EditText)findViewById(R.id.bookTitleEditText);
                EditText priceET = (EditText)findViewById(R.id.bookPriceEditText);
                EditText dateET = (EditText)findViewById(R.id.purchaseDateEditText);

                Drawable bookImg = bookIV.getDrawable();
                String titleStr = titleET.getText().toString();
                String priceStr = priceET.getText().toString();
                String dateStr = dateET.getText().toString();

                boolean validateResult = ValidationUtil.validateForm(bookImg, titleStr, priceStr, dateStr, AddBookActivity.this);
                if (validateResult) {
                    //ToDo 書籍追加処理
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        txtDate = (EditText)findViewById(R.id.purchaseDateEditText);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
