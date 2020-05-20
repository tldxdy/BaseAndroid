package com.hujianbo.http;

import com.hujianbo.base.model.BaseBean;
import com.hujianbo.baseandroid.TestBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;

import static com.hujianbo.http.BaseConfig.BASE_URL;

public interface Api {

    /**
     * 测试接口
     *
     * @return
     */
    @GET(BASE_URL + "news/latest")
    Observable<TestBean> test(@HeaderMap Map<String,String> map);
}
