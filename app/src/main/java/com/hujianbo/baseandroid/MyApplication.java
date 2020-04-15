package com.hujianbo.baseandroid;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.hujianbo.base.base.BaseApp;

public class MyApplication extends BaseApp {
    public static PersistentCookieJar cookieJar;

    @Override
    public void onCreate() {
        super.onCreate();

        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getInstance()));
    }


}
