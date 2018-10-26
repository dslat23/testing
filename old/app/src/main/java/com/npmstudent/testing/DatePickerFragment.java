package com.npmstudent.testing;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by npmstudent on 5/24/16.
 */
public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE="date";
    public static final String EXTRA_DATE = "testing.date";

    private DatePicker datePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calen = Calendar.getInstance();
        calen.setTime(date);
        int year = calen.get(Calendar.YEAR);
        int month = calen.get(Calendar.MONTH);
        int day = calen.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        datePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        datePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        }).create();
    }

    private void sendResult(int resCode, Date date){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resCode, intent);
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);
        DatePickerFragment frag = new DatePickerFragment();
        frag.setArguments(args);
        return frag;
    }

}
