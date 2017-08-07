package com.hiromisakurai.bookapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesEditor {

    public static void edit(String requestToken, int userId, Context context) {
        SharedPreferences dataStore = context.getSharedPreferences("DataStore", MODE_PRIVATE);
        //SharedPreferences dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        SharedPreferences.Editor editor = dataStore.edit();
        editor.putString(Constants.PrefKey.REQUEST_TOKEN, requestToken);
        editor.putInt(Constants.PrefKey.USER_ID, userId);
        editor.apply();

        Log.i("Success, token ", String.valueOf(dataStore.getString(Constants.PrefKey.REQUEST_TOKEN, "noting")));
        Log.i("Success, id ", String.valueOf(dataStore.getInt(Constants.PrefKey.USER_ID, 0)));
    }
}