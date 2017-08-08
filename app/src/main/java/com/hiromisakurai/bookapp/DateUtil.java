package com.hiromisakurai.bookapp;

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
            Date date = dateFormat.parse(time);
            return new SimpleDateFormat(displayFormat, Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
