package com.hujianbo.baseandroid.download;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {
    private volatile static NetWork INSTANCE;
    private DowloadListener listener;

    /**
     * 获取单例
     */
    public static NetWork getInstance() {
        if (INSTANCE == null) {
            synchronized (NetWork.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetWork();
                }
            }
        }
        return INSTANCE;
    }

    public Observable<ResponseBody> down(String url, DowloadListener listener) {
        this.listener = listener;
        DownloadInterceptor mInterceptor = new DownloadInterceptor(listener);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        //  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                //.baseUrl(UtilRetrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        return api.down(url);
    }
}
