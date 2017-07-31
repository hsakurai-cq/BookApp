package com.hiromisakurai.bookapp;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public class ValidationUtil {
    static public boolean validateForm(Drawable img, String title, String price, String date, Context context) {
        boolean valid = true;

        if (img == null) {
            ErrorDialogUtil.showDialog("Set Image.", context);
            valid = false;
        }
        if (TextUtils.isEmpty(title)) {
            ErrorDialogUtil.showDialog("Enter Title.", context);
            valid = false;
        }
        if (TextUtils.isEmpty(price)) {
            ErrorDialogUtil.showDialog("Enter Price.", context);
            valid = false;
        }
        if (TextUtils.isEmpty(date)) {
            ErrorDialogUtil.showDialog("Enter Date", context);
            valid = false;
        }
        return valid;
    }

    static  public boolean validateAccount(String email, String password, String passConfirm, Context context) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            ErrorDialogUtil.showDialog("Enter Email.", context);
            valid = false;
        }

        if (TextUtils.isEmpty(password)) {
            ErrorDialogUtil.showDialog("Enter Password.", context);
            valid = false;
        }

        if (TextUtils.isEmpty(passConfirm)) {
            ErrorDialogUtil.showDialog("Enter Password Again to Confirm.", context);
            valid = false;
        }

        if (password.length() < 6) {
            ErrorDialogUtil.showDialog("Too short, yor password.", context);
            valid = false;
        }

        if (!(password.equals(passConfirm))) {
            ErrorDialogUtil.showDialog("Password, not matching.", context);
            valid = false;
        }

        return valid;
    }
}
