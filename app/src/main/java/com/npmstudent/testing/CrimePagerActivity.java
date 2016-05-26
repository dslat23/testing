package com.npmstudent.testing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by npmstudent on 5/24/16.
 */
public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIMEID = "com.npmstudent.testing.crime_id";

    private ViewPager viewPager;
    private List<Crime> crimes;

    public static Intent newIntent(Context context, UUID crimeID){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIMEID, crimeID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIMEID);

        viewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view);
        crimes = CrimeLab.get(this).getCrimes();
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });

        for(int i = 0; i < crimes.size(); i++){
            if(crimes.get(i).getId().equals(crimeID)){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
