package com.hujianbo.base.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class BaseApp extends Application {
    private static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;

    }
    public static BaseApp getInstance(){
        return baseApp;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
