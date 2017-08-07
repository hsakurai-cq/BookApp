package com.hiromisakurai.bookapp;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String changeFormat(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        try {
            if (TextUtils.isEmpty(time)) {
                time = "2000-01-01";
                return time;
            }else {
                Date date = dateFormat.parse(time);
                return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
            }
        } catch (ParseException e) {
            Log.i("Parse error", String.valueOf(e));
        }
        return null;
    }
}
