package com.hiromisakurai.bookapp;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    public static String validateLogin(String email, String password, Context context) {
        List<String> errorArray = new ArrayList<>();

        if (TextUtils.isEmpty(email)) {
            errorArray.add("Enter Email.");
        }

        if (TextUtils.isEmpty(password)) {
            errorArray.add("Enter Password.");
        }

        if (errorArray.isEmpty()) {
            return null;
        } else {
            String errorMessages = TextUtils.join("\n", errorArray);
            return errorMessages;
        }
    }

    public static String validateForm(Drawable img, String title, String price, String date, Context context) {
        List<String> errorArray = new ArrayList<>();

        if (img == null) {
            errorArray.add("Set Image.");
        }

        if (TextUtils.isEmpty(title)) {
            errorArray.add("Enter title.");
        }

        if (TextUtils.isEmpty(price)) {
            errorArray.add("Enter Price.");
        }

        if (TextUtils.isEmpty(date)) {
            errorArray.add("Enter Purchase Date");
        }

        if (errorArray.isEmpty()) {
            return null;
        } else {
            String errorMessages = TextUtils.join("\n", errorArray);
            return errorMessages;
        }
    }

    public static String validateAccount(String email, String password, String passConfirm, Context context) {
        List<String> errorArray = new ArrayList<>();

        if (TextUtils.isEmpty(email)) {
            errorArray.add("Enter Email.");
        }

        if (TextUtils.isEmpty(password)) {
            errorArray.add("Enter Password.");
        }

        if (TextUtils.isEmpty(passConfirm)) {
            errorArray.add("Enter Password Again to Confirm..");
        }

        if (password.length() < 6) {
            errorArray.add("Too short, your password.");
        }

        if (!(password.equals(passConfirm))) {
            errorArray.add("Password, not matching.");
        }

        if (errorArray.isEmpty()) {
            return null;
        } else {
            String errorMessages = TextUtils.join("\n", errorArray);
            return errorMessages;
        }
    }
}
