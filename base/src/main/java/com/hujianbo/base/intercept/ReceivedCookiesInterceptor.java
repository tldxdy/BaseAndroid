package com.hujianbo.base.intercept;

import android.text.TextUtils;

import com.hujianbo.base.base.BaseApp;
import com.hujianbo.base.util.UtilSPutil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;


import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        String url = chain.request().url().toString();
        List<String> cookies = originalResponse.headers("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {

            UtilSPutil.getInstance(BaseApp.getInstance()).setString("mycookie", originalResponse.headers("Set-Cookie").get(0));
        }


        return originalResponse;
    }
}
