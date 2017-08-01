package com.hiromisakurai.bookapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnDateDialogClickListener listener;
    public void setListener(OnDateDialogClickListener listener) {
        this.listener = listener;
    }

    public Dialog onCreateDialog(Bundle savedInstance) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        if (listener != null) {
            listener.onDateDialogClickListener(year, month, day);
        }
    }
}
