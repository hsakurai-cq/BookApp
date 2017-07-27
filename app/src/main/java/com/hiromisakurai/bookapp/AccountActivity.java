package com.hiromisakurai.bookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailET = (EditText) findViewById(R.id.AccountEmailEditText);
                EditText passwordET = (EditText) findViewById(R.id.AccountPasswordEditText);
                EditText passwordConfirmET = (EditText) findViewById(R.id.AccountPasswordConfirmEditText);

                String signUpEmail = emailET.getText().toString();
                String signUpPassword = passwordET.getText().toString();
                String signUpPasswordConfirm = passwordConfirmET.getText().toString();

                boolean validationResult = ValidationUtil.validateAccount(signUpEmail, signUpPassword, signUpPasswordConfirm, AccountActivity.this);
                if (validationResult) {
                    //Todo アカウント作成処理
                    Log.i("Account validation true", String.valueOf(validationResult));
                }
            }
        });
    }
}
