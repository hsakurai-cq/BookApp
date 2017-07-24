package com.hiromisakurai.bookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    ErrorDialogUtil emailErrorLog = new ErrorDialogUtil();
                    emailErrorLog.showDialog("No email, Please enter your email!", LoginActivity.this);
                }
                if (TextUtils.isEmpty(password)) {
                    ErrorDialogUtil passwordErrorLog = new ErrorDialogUtil();
                    passwordErrorLog.showDialog("No password, Please enter your password!", LoginActivity.this);
                }
            }
        });
    }
}
