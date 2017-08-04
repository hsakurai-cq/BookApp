package com.hiromisakurai.bookapp;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String changeFormat(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            if (TextUtils.isEmpty(time)) {
                time = "2000-01-01";
                return time;
            }else {
                Date date = dateFormat.parse(time);
                String stringFromDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                return stringFromDate;
            }
        } catch (ParseException e) {
            Log.i("Parse error", String.valueOf(e));
        }
        return null;
    }
}
