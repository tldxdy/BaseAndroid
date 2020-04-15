package com.hujianbo.http;

import com.hujianbo.base.model.BaseBean;
import com.hujianbo.baseandroid.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    /**
     * 测试接口
     *
     * @return
     */
    @GET("news/latest")
    Observable<TestBean> test();
}
