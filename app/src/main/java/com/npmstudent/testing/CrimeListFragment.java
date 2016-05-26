package com.npmstudent.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by npmstudent on 5/24/16.
 */
public class CrimeListFragment extends Fragment{

    private RecyclerView crimeRecyclerView;
    private CrimeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        crimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(adapter == null){
            adapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(adapter);
        }else{
            adapter.setCrimes(crimes);
            adapter.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox solvedCheckBox;
        private Crime crime;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView)itemView.findViewById(R.id.list_item_crime_title_view);
            dateTextView = (TextView)itemView.findViewById(R.id.list_item_crime_date_view);
            solvedCheckBox = (CheckBox)itemView.findViewById(R.id.list_item_crime_check);
            solvedCheckBox.setEnabled(false);
        }

        @Override
        public void onClick(View v){
            Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
            startActivity(intent);
        }

        public void bindCrime(Crime crime){
            this.crime = crime;
            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            titleTextView.setText(this.crime.getTitle());
            dateTextView.setText(format.format(this.crime.getDate()));
            solvedCheckBox.setChecked(this.crime.isSolved());
        }

    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes){
            this.crimes = crimes;
        }

        public void setCrimes(List<Crime> crimes){
            this.crimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_list_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = crimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount(){
            return crimes.size();
        }

    }
}
