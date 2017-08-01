package com.hiromisakurai.bookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_account);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_account:
                EditText emailET = (EditText) findViewById(R.id.accountEmailEditText);
                EditText passwordET = (EditText) findViewById(R.id.accountPasswordEditText);
                EditText passwordConfirmET = (EditText) findViewById(R.id.accountPasswordConfirmEditText);

                String signUpEmail = emailET.getText().toString();
                String signUpPassword = passwordET.getText().toString();
                String signUpPasswordConfirm = passwordConfirmET.getText().toString();

                boolean validationResult = ValidationUtil.validateAccount(signUpEmail, signUpPassword, signUpPasswordConfirm, AccountActivity.this);
                if (validationResult) {
                    //Todo アカウント作成処理
                    Log.i("Account validation", String.valueOf(validationResult));
                }
                return true;

            case R.id.action_back:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
