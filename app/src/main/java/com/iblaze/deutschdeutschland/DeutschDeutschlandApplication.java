package com.iblaze.deutschdeutschland;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ADNROID1 on 20-09-2017.
 */

public class DeutschDeutschlandApplication extends Application {

    public static DeutschDeutschlandApplication application;

    public static DeutschDeutschlandApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("arial.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
