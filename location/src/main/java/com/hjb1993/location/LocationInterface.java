package com.hjb1993.location;

import android.content.Context;

public interface LocationInterface {

    void init(LocationCallBackListener listener); //定位的初始化

    void startLocation();  //开始定位

    void stopLocation(); //停止定位

    void destroyLocation();//销毁定位或一些资源释放

}