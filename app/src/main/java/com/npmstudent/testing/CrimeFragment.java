package com.npmstudent.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by npmstudent on 5/24/16.
 */
public class CrimeFragment extends Fragment {

    private static final String ARG_CRIMEID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQ_DATE = 0;

    private Crime crime;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;
    private UUID crimeID;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        crimeID = (UUID) getArguments().getSerializable(ARG_CRIMEID);
        crime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    public static CrimeFragment newInstance(UUID crimeID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIMEID,crimeID);
        CrimeFragment frag = new CrimeFragment();
        frag.setArguments(args);
        return frag;

    }

    @Override
    public void onPause(){
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(crime);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode != Activity.RESULT_OK){
            return;
        }
        if(reqCode == REQ_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            updateDate();
        }
    }

    private void updateDate(){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        dateButton.setText(format.format(crime.getDate()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.fragment_crime, container, false);

        dateButton = (Button)v.findViewById(R.id.crime_date);
        solvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        titleField = (EditText)v.findViewById(R.id.crime_title);

        titleField.setText(crime.getTitle());
        dateButton.setText(crime.getDate().toString());
        solvedCheckBox.setChecked(crime.isSolved());

        titleField.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //nothing
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        crime.setTitle(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //nothing
                    }
                }
        );

        updateDate();
        dateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQ_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        solvedCheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        crime.setSolved(isChecked);
                    }
                }
        );

        return v;
    }
}
