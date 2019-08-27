package com.hujianbo.baseandroid.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author pengfei.zhang
 * @Date 2018/9/19
 * @Des
 */

public class DownloadInterceptor implements Interceptor {

    private DowloadListener downloadListener;

    public DownloadInterceptor(DowloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new FileResponseBody(response.body(), downloadListener)).build();
    }

}
