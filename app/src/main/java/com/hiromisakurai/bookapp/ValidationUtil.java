package com.hiromisakurai.bookapp;


import android.content.Context;
import android.graphics.drawable.Drawable;

public class ValidationUtil {
    static public boolean validateForm(Drawable img, String title, String price, String date, Context content) {
        boolean valid = true;

        if (img == null) {
            ErrorDialogUtil.showDialog("Set Image.", content);
            valid = false;
        }
        if (title.isEmpty()) {
            ErrorDialogUtil.showDialog("Enter Title.", content);
            valid = false;
        }
        if (price.isEmpty()) {
            ErrorDialogUtil.showDialog("Enter Price.", content);
            valid = false;
        }
        if (date.isEmpty()) {
            ErrorDialogUtil.showDialog("Enter Date", content);
            valid = false;
        }
        return valid;
    }
}
