package com.hiromisakurai.bookapp;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.util.ArrayList;

public class ValidationUtil {

    static public boolean validateLogin(String email, String password, Context context) {
        boolean valid = true;
        ArrayList<String> errorArray = new ArrayList<String>();

        if (TextUtils.isEmpty(email)) {
            errorArray.add("Enter Email.");
            valid = false;
        }

        if (TextUtils.isEmpty(password)) {
            errorArray.add("Enter Password.");
            valid = false;
        }

        if (!valid) {
            String errorMessages = TextUtils.join("\n", errorArray);
            ErrorDialogUtil.showDialog(errorMessages, context);
            valid = false;
        }

        return valid;
    }

    static public boolean validateForm(Drawable img, String title, String price, String date, Context context) {
        boolean valid = true;
        ArrayList<String> errorArray = new ArrayList<String>();

        if (img == null) {
            errorArray.add("Set Image.");
            valid = false;
        }

        if (TextUtils.isEmpty(title)) {
            errorArray.add("Enter title.");
            valid = false;
        }

        if (TextUtils.isEmpty(price)) {
            errorArray.add("Enter Price.");
            valid = false;
        }

        if (TextUtils.isEmpty(date)) {
            errorArray.add("Enter Purchase Date");
            valid = false;
        }

        if (!valid) {
            String errorMessages = TextUtils.join("\n", errorArray);
            ErrorDialogUtil.showDialog(errorMessages, context);
        }

        return valid;
    }

    static  public boolean validateAccount(String email, String password, String passConfirm, Context context) {
        boolean valid = true;
        ArrayList<String> errorArray = new ArrayList<String>();

        if (TextUtils.isEmpty(email)) {
            errorArray.add("Enter Email.");
            valid = false;
        }

        if (TextUtils.isEmpty(password)) {
            errorArray.add("Enter Password.");
            valid = false;
        }

        if (TextUtils.isEmpty(passConfirm)) {
            errorArray.add("Enter Password Again to Confirm..");
            valid = false;
        }

        if (password.length() < 6) {
            errorArray.add("Too short, your password.");
            valid = false;
        }

        if (!(password.equals(passConfirm))) {
            errorArray.add("Password, not matching.");
            valid = false;
        }

        if (!valid) {
            String errorMessages = TextUtils.join("\n", errorArray);
            ErrorDialogUtil.showDialog(errorMessages, context);
        }

        return valid;
    }
}
