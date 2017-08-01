package com.hiromisakurai.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isFirstLaunch();

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = emailEditText.getText().toString();
                String loginPassword = passwordEditText.getText().toString();

                boolean validationResult = ValidationUtil.validateLogin(loginEmail, loginPassword, LoginActivity.this);
                if (validationResult) {
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                } else {
                    ErrorDialogUtil.showDialog("Please enter the correct information.", LoginActivity.this);
                }
            }
        });
    }

    public void isFirstLaunch() {
        if(AppLaunchChecker.hasStartedFromLauncher(this)){
            Log.i("AppLaunchChecker","2回目以降");
        } else {
            Log.i("AppLaunchChecker","初回起動");
            Intent intent = new Intent(getApplication(), AccountActivity.class);
            startActivity(intent);
        }
        AppLaunchChecker.onActivityCreate(this);
    }
}
