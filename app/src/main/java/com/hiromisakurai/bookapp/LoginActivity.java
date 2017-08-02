package com.hiromisakurai.bookapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://54.238.252.116";

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

                String errorMessageString = ValidationUtil.validateLogin(loginEmail, loginPassword, LoginActivity.this);
                //Log.i("errorMessageString", String.valueOf(errorMessageString));
                boolean valid = TextUtils.isEmpty(errorMessageString);
                if (valid) {
                    Log.i("Login validation", "OK");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserApi api = retrofit.create(UserApi.class);
                    Call<UserResponse> call = api.login(new User(loginEmail, loginPassword));
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()) {
//                                Log.i("Success, Token is ", String.valueOf(response.body().getRequestToken()));
//                                Log.i("Success, ID is ", String.valueOf(response.body().getUserId()));

                                SharedPreferences dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
                                SharedPreferences.Editor editor = dataStore.edit();
                                editor.putString("request_token", response.body().getRequestToken());
                                editor.putInt("user_id", response.body().getUserId());
                                editor.commit();
                                Log.i("data store, token ", String.valueOf(dataStore.getString("request_token", "noting")));
                                Log.i("data store, id ", String.valueOf(dataStore.getInt("user_id", 0)));

                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.i("Cannot login", String.valueOf(response));
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.i("onFailure", String.valueOf(t));
                        }
                    });
                } else {
                    ErrorDialogUtil.showDialog(errorMessageString, LoginActivity.this);
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
