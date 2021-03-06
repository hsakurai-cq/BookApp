package com.hiromisakurai.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String passwordConfirm = passwordConfirmET.getText().toString();

                String errorMessageString  = ValidationUtil.validateAccount(email, password, passwordConfirm, AccountActivity.this);
                boolean valid = TextUtils.isEmpty(errorMessageString);
                if (!valid) {
                    ErrorDialogUtil.showDialog(errorMessageString, AccountActivity.this);
                }
                Log.i("accountValidation", "OK");

                UserApi api = Client.setUp().create(UserApi.class);
                Call<UserResponse> call = api.signUp(new User(email, password));
                enqueue(call);
                return true;

            case R.id.action_back:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void enqueue(Call<UserResponse> call) {
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", String.valueOf(response));
                    return;
                }
                String requestToken = response.body().getRequestToken();
                int userId = response.body().getUserId();
                SharedPreferencesEditor.edit(requestToken, userId, AccountActivity.this);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("onFailure", String.valueOf(t));
            }
        });
    }
}
