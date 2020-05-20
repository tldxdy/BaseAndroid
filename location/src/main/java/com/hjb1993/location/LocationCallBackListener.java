package com.hjb1993.location;

public interface LocationCallBackListener {

    void locationSuccessful(LocationModel locationModel);//定位成功

    void locationFailure(String msg);//定位失败

    void noPermissions();//没有定位权限
}
