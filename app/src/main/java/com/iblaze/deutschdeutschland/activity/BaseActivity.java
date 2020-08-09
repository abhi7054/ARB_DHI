package com.iblaze.deutschdeutschland.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Chirag Solanki on 12-07-2017.
 */

public class BaseActivity extends AppCompatActivity {

    void fragmentChange(Fragment fragment, FragmentState fragmentState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentState == FragmentState.ADD) {
            transaction.add(R.id.fragment_container, fragment);
        } else {
            transaction.replace(R.id.fragment_container, fragment);
        }
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();

    }

    void fragmentChangeWithoutBackstackEntry(Fragment fragment, FragmentState fragmentState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentState == FragmentState.ADD) {
            transaction.add(R.id.fragment_container, fragment);
        } else {
            transaction.replace(R.id.fragment_container, fragment);
        }
//        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

}
