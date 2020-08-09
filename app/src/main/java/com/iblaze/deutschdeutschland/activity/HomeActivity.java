package com.iblaze.deutschdeutschland.activity;

import android.os.Bundle;
import android.util.Log;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;

public class HomeActivity extends AppNavigationActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);

        openHomeFragment(FragmentState.REPLACE);
    }


    @Override
    public void onBackPressed() {
        Log.e("Tag", "Counter " + getSupportFragmentManager().getBackStackEntryCount());
        int i = getSupportFragmentManager().getBackStackEntryCount();
        if (i > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            this.finish();
        }
    }
}
