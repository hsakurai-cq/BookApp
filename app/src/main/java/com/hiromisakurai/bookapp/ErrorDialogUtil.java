package com.hiromisakurai.bookapp;


import android.content.Context;
import android.support.v7.app.AlertDialog;

public class ErrorDialogUtil {
    public void showDialog(String error, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.alert_title)
                .setPositiveButton("OK", null)
                .setMessage(error);
        builder.show();
    }
}
