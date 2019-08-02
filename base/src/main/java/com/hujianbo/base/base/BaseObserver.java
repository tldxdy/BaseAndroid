package com.hujianbo.base.base;

import com.hujianbo.base.model.BaseBean;
import com.hujianbo.base.util.UtilNetStatus;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;


public class BaseObserver<T extends BaseBean> extends DisposableObserver<T> {


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
            if(UtilNetStatus.isHasConnection(mActivityWeakReference.get())){
                mActivityWeakReference.get().showCenterToast("网络连接异常，请检查网络连接");
                return;
            }
        }

        if (mFragmentWeakReference != null && mFragmentWeakReference.get() != null) {
            if (!UtilNetStatus.isHasConnection(mFragmentWeakReference.get().getContext())) {
                mFragmentWeakReference.get().showCenterToast("网络连接异常，请检查网络连接");
                return;
            }
        }

    }

    @Override
    public void onNext(T t) {
        if(t.getCode() == 200){

            success(t);
        }else {
            error();
        }
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

        error();
    }



    /**
     * 错误
     *
     * @param
     */
    public void error() {

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
