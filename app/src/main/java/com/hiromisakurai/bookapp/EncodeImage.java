package com.hiromisakurai.bookapp;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class EncodeImage {

    public static String toBase64(Bitmap image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] b = bos.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }
}
