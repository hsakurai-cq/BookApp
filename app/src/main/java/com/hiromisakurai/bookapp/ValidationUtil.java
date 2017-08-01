package com.hiromisakurai.bookapp;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    public static boolean validateLogin(String email, String password, Context context) {
        boolean valid = true;
        List<String> errorArray = new ArrayList<>();

        if (TextUtils.isEmpty(email)) {
            //errorArray.add("Enter Email.");
            valid = false;
        }

        if (TextUtils.isEmpty(password)) {
            //errorArray.add("Enter Password.");
            valid = false;
        }

//        if (!valid) {
//            //String errorMessages = TextUtils.join("\n", errorArray);
//            //ErrorDialogUtil.showDialog(errorMessages, context);
//        }

        return valid;
    }

    public static boolean validateForm(Drawable img, String title, String price, String date, Context context) {
        boolean valid = true;
        //List<String> errorArray = new ArrayList<>();

        if (img == null) {
            //errorArray.add("Set Image.");
            valid = false;
        }

        if (TextUtils.isEmpty(title)) {
            //errorArray.add("Enter title.");
            valid = false;
        }

        if (TextUtils.isEmpty(price)) {
            //errorArray.add("Enter Price.");
            valid = false;
        }

        if (TextUtils.isEmpty(date)) {
            //errorArray.add("Enter Purchase Date");
            valid = false;
        }

//        if (!valid) {
//            String errorMessages = TextUtils.join("\n", errorArray);
//            ErrorDialogUtil.showDialog(errorMessages, context);
//        }

        return valid;
    }

    public static boolean validateAccount(String email, String password, String passConfirm, Context context) {
        boolean valid = true;
        //List<String> errorArray = new ArrayList<>();

        if (TextUtils.isEmpty(email)) {
            //errorArray.add("Enter Email.");
            valid = false;
        }

        if (TextUtils.isEmpty(password)) {
            //errorArray.add("Enter Password.");
            valid = false;
        }

        if (TextUtils.isEmpty(passConfirm)) {
            //errorArray.add("Enter Password Again to Confirm..");
            valid = false;
        }

        if (password.length() < 6) {
            //errorArray.add("Too short, your password.");
            valid = false;
        }

        if (!(password.equals(passConfirm))) {
            //errorArray.add("Password, not matching.");
            valid = false;
        }

//        if (!valid) {
//            String errorMessages = TextUtils.join("\n", errorArray);
//            ErrorDialogUtil.showDialog(errorMessages, context);
//        }

        return valid;
    }
}
