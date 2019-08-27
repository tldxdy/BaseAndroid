package com.hujianbo.baseandroid.download;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {
    @Streaming//注明为流文件，防止retrofit将大文件读入内存
    @GET
    Observable<ResponseBody> down(@Url String url);
}