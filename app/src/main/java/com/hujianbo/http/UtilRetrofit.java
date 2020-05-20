package com.hujianbo.http;




import com.hujianbo.base.base.BaseApp;
import com.hujianbo.base.intercept.AddCookiesInterceptor;
import com.hujianbo.base.intercept.ReceivedCookiesInterceptor;
import com.hujianbo.baseandroid.MyApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liu on 2017/10/30.
 */

public class UtilRetrofit {
    public static volatile Retrofit retrofit = null;
    public static volatile Retrofit cookieRetrofit = null;

    public static final int CONNECT_TIME_OUT = 60;
    public static final int READ_TIME_OUT = 60;
    public static final int WRITE_TIME_OUT = 60;

    public static String BASE_URL = BaseConfig.BASE_URL;


    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (UtilRetrofit.class) {
                if (retrofit == null) {
                    HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();

                    if (!BaseConfig.IS_ONLINE) {
                        //显示日志
                        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(logInterceptor)
                            .cookieJar(MyApplication.cookieJar)// 设置封装好的cookieJar
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS).build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
                }
            }
        }
        return retrofit;
    }

    public static Retrofit getInstanceByCookie() {
        if (cookieRetrofit == null) {
            synchronized (UtilRetrofit.class) {
                if (cookieRetrofit == null) {
                    HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
                    if (!BaseConfig.IS_ONLINE) {
                        //显示日志
                        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }

                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ReceivedCookiesInterceptor())
                            .addInterceptor(new AddCookiesInterceptor())
                            .addInterceptor(logInterceptor)
                            .cookieJar(MyApplication.cookieJar)// 设置封装好的cookieJar
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS).build();
                    cookieRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                            client(client).
                            addConverterFactory(GsonConverterFactory.create()).
                            addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
                }
            }
        }
        return cookieRetrofit;
    }
}
