package com.hujianbo.base.intercept;
import com.hujianbo.base.util.AppUtils;
import com.hujianbo.base.util.UtilSPutil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

//        HashSet<String> preferences = (HashSet) MyApplication.getContext().getSharedPreferences("config",
//                MyApplication.getContext().MODE_PRIVATE).getStringSet("cookie", null);
        String cookie = UtilSPutil.getInstance(AppUtils.getApp()).getString("mycookie");
        if (cookie != null) {
            builder.addHeader("Cookie", cookie);
//                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }
        return chain.proceed(builder.build());
    }
}