package com.hiromisakurai.bookapp;

import android.content.Intent;
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

                String errorMessageString = ValidationUtil.validateLogin(loginEmail, loginPassword, LoginActivity.this);
                //Log.i("errorMessageString", String.valueOf(errorMessageString));
                boolean valid = TextUtils.isEmpty(errorMessageString);
                if (valid) {
                    Log.i("Login validation", "OK");

                    UserApi api = Client.setUp().create(UserApi.class);
                    Call<UserResponse> call = api.login(new User(loginEmail, loginPassword));
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()) {
                                String requestToken = response.body().getRequestToken();
                                int userId = response.body().getUserId();
                                SharedPreferencesEditor.edit(requestToken, userId, LoginActivity.this);
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
