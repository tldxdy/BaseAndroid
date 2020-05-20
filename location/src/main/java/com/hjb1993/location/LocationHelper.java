package com.hjb1993.location;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 定位辅助类 判断是否有定位权限

 * <p>
 * 使用注意 必须要调用onRequestPermissionsResult用于获取权限回调
 * <p>
 * 必须要在页面关闭时调用destroyLocation方法
 * <p>
 * Created by sx on 2017/8/7.
 */

public class LocationHelper {

    private LocationInterface mLocationInterface;//定位实现接口

    private PermissionHelper mPreHelper;//权限请求


    private LocationCallBackListener mLocationCallBackListener;//定位回调

    //需要进行定位功能的权限
    private List<String> needLocationPermissions;

    public LocationHelper(@NonNull Object obj, @NonNull LocationInterface locationInterface, @NonNull LocationCallBackListener locationCallBackListener) {
        this.mLocationInterface = locationInterface;
        this.mLocationInterface.init(locationCallBackListener);
        this.mLocationCallBackListener = locationCallBackListener;
        needLocationPermissions = new ArrayList<>();
        mPreHelper = new PermissionHelper(obj);
    }


    /**
     * 添加还需要申请的权限
     *
     * @param permissions
     */
    public void addPermissions(String permissions) {
        needLocationPermissions.add(permissions);
    }

    /**
     * 需要的定位权限
     */
    private void setLocationPermissions() {
        needLocationPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        needLocationPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        needLocationPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 启动定位
     */
    public void startLocation() {
        if (needRequestPermiss()) {

            setLocationPermissions();
            requestPermissions();
            return;
        }
        mLocationInterface.startLocation();
    }

    /**
     * 请求定位权限
     */
    private void requestPermissions() {
        mPreHelper.requestPermissions("定位需要打开权限",new PermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {
                mLocationInterface.startLocation();
            }

            @Override
            public void doAfterDenied(String... permission) {
                mLocationCallBackListener.noPermissions();
            }
        }, getPermissions());
    }

    private String[] getPermissions() {
        final int size = needLocationPermissions.size();
        String[] arr = needLocationPermissions.toArray(new String[size] );
        return arr;
    }

    /**
     * 是否需要申请权限
     *
     * @return
     */
    private boolean needRequestPermiss() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 关闭定位
     */
    public void stopLocation() {
        mLocationInterface.stopLocation();
    }

    /**
     * 关闭一些内容
     */
    public void destroyLocation() {
        mLocationInterface.destroyLocation();
    }

    /**
     * 获取权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPreHelper == null) return;
        mPreHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}