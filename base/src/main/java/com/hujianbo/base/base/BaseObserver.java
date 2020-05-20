package com.hujianbo.base.base;

import com.hujianbo.base.R;
import com.hujianbo.base.model.BaseBean;
import com.hujianbo.base.util.UtilNetStatus;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;


public class BaseObserver<T extends BaseBean> extends DisposableObserver<T> {


    //不显示错误界面，Toast提示
    public static final int MODEL_TOAST = 1;
    //显示错误布局，不Toast提示
    public static final int MODEL_LAYOUT = 2;
    //都显示
    public static final int MODEL_ALL = 3;
    //都不显示
    public static final int MODEL_NO = 4;

    //列表加载模式（下拉刷新，上拉加载）
    public static final int MODEL_REFRESH = 5;
    public static final int MODEL_LOADMORE = 6;
    //不显示加载框
    public static final int MODEL_HIDE_DIALOG = 7;
    //不显示加载框，仅显示toast
    public static final int MODEL_HIDE_DIALOG_SHOW_TOAST = 8;
    //不显示加载框，仅显示错误界面
    public static final int MODEL_HIDE_DIALOG_SHOW_LAYOUT = 9;
    //不显示加载框，其它都显示
    public static final int MODEL_HIDE_DIALOG_SHOW_ALL = 10;
    //仅显示加载框
    public static final int MODEL_ONLY_SHOW_DIALOG = 11;
    //显示加载Layout，如果报错再显示错误布局
    public static final int MODEL_SHOW_LOADING_LAYOUT_AND_ERROR_LAYOUT = 12;
    public static final int MODEL_SHOW_DIALOG_TOAST = 13;

    private WeakReference<BaseActivity> mActivityWeakReference;
    private WeakReference<BaseFragment> mFragmentWeakReference;


    /**
     * 用于记录是否需要进行弹框，进度，以及各种状况
     */
    private int model = -1;

    public BaseObserver(BaseActivity activity){
        mActivityWeakReference = new WeakReference<>(activity);
        model = -1;
    }
    public BaseObserver(BaseActivity activity, int model){
        mActivityWeakReference = new WeakReference<>(activity);
        this.model = model;
    }

    public BaseObserver(BaseFragment fragment){
        mFragmentWeakReference = new WeakReference<>(fragment);
        model = -1;
    }
    public BaseObserver(BaseFragment fragment, int model){
        mFragmentWeakReference = new WeakReference<>(fragment);
        this.model = model;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mActivityWeakReference != null && mActivityWeakReference.get() != null) {
            if(!UtilNetStatus.isHasConnection(mActivityWeakReference.get())){
                mActivityWeakReference.get().showCenterToast("网络连接异常，请检查网络连接");
                return;
            }
            switch (model) {
                case MODEL_LAYOUT:
                case MODEL_ALL:
                case MODEL_ONLY_SHOW_DIALOG:
                case MODEL_SHOW_DIALOG_TOAST:
                    mActivityWeakReference.get().showProgressDialog();
                    break;
                default:
                    break;
            }
        }

        if (mFragmentWeakReference != null && mFragmentWeakReference.get() != null) {
            if (!UtilNetStatus.isHasConnection(mFragmentWeakReference.get().getContext())) {
                mFragmentWeakReference.get().showCenterToast("网络连接异常，请检查网络连接");
                return;
            }
            switch (model) {
                case MODEL_LAYOUT:
                case MODEL_ALL:
                case MODEL_ONLY_SHOW_DIALOG:
                case MODEL_SHOW_DIALOG_TOAST:
                    mFragmentWeakReference.get().showProgressDialog();
                    break;
                case MODEL_SHOW_LOADING_LAYOUT_AND_ERROR_LAYOUT:
                    mFragmentWeakReference.get().showErrorLayout(R.layout.fragment_base_loading);
                    break;
                default:
                    break;
            }
        }



    }

    @Override
    public void onNext(T t) {
        success(t);
//        if(t.getCode() == 200){
//
//          success(t);
//        }else {
//            error();
//        }
    }

    public void success(T t) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        if (mActivityWeakReference != null && mActivityWeakReference.get() != null) {
            mActivityWeakReference.get().removeProgressDialog();
           /** 做处理，上线后请勿打印 **/
            mActivityWeakReference.get().showCenterToast(e.toString());

        }
        if (mFragmentWeakReference != null && mFragmentWeakReference.get() != null) {
            mFragmentWeakReference.get().removeProgressDialog();
            /** 做处理，上线后请勿打印 **/
            mFragmentWeakReference.get().showCenterToast(e.toString());

        }

        error(null);
    }



    /**
     * 错误
     *
     * @param
     */
    public void error(T t) {
        switch (model) {
            case MODEL_ALL:
            case MODEL_LAYOUT:
            case MODEL_HIDE_DIALOG_SHOW_ALL:
            case MODEL_HIDE_DIALOG_SHOW_LAYOUT:
            case MODEL_SHOW_LOADING_LAYOUT_AND_ERROR_LAYOUT:


                if (mActivityWeakReference != null && mActivityWeakReference.get() != null) {
                    if (!UtilNetStatus.isHasConnection(mActivityWeakReference.get())) {
                        mActivityWeakReference.get().showErrorLayout();
                        return;
                    }
                    mActivityWeakReference.get().showErrorLayout("加载失败");
                }
                if (mFragmentWeakReference != null && mFragmentWeakReference.get() != null) {
                    if (!UtilNetStatus.isHasConnection(mFragmentWeakReference.get().getContext())) {
                        mFragmentWeakReference.get().showErrorLayout();
                        return;
                    }
                    mFragmentWeakReference.get().showErrorLayout("加载失败");
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onComplete() {
        if (mActivityWeakReference != null && mActivityWeakReference.get() != null) {
            mActivityWeakReference.get().removeProgressDialog();
        }
        if (mFragmentWeakReference != null && mFragmentWeakReference.get() != null) {
            mFragmentWeakReference.get().removeProgressDialog();
        }
    }
}
