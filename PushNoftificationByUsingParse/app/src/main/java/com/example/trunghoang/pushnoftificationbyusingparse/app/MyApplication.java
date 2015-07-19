package com.example.trunghoang.pushnoftificationbyusingparse.app;

import android.app.Application;

import com.example.trunghoang.pushnoftificationbyusingparse.helper.ParseUtils;

/**
 * Created by trunghoang on 7/18/15.
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // register with parse
        ParseUtils.registerParse(this);
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }
}
