package com.iblaze.deutschdeutschland.activity;

import android.os.Bundle;
import android.util.Log;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;

public class SplashActivity extends AppNavigationActivity {

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16)
        {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/

        setContentView(R.layout.activity_splash_home);

        openSplashFragment(FragmentState.REPLACE);
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },300);
*/

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
