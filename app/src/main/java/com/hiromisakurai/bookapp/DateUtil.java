package com.hiromisakurai.bookapp;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String originalFormat = "EEE, dd MMM yyyy HH:mm:ss z";
    private static final String displayFormat = "yyyy-MM-dd";

    public static String changeFormat(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(originalFormat, Locale.ENGLISH);
        try {
            if (TextUtils.isEmpty(time)) {
                time = "2000-01-01";
                return time;
            }
            Date date = dateFormat.parse(time);
            return new SimpleDateFormat(displayFormat, Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            Log.i("Parse error", String.valueOf(e));
        }
        return null;
    }
}
