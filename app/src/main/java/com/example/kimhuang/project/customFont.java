package com.example.kimhuang.project;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Donnapa on 06-Apr-16.
 */
public class customFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/THSarabunNew Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
