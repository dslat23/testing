package com.npmstudent.testing;

import android.support.v4.app.Fragment;

/**
 * Created by npmstudent on 5/24/16.
 */
public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }

}
