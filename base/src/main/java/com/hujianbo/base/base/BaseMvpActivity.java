package com.hujianbo.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hujianbo.base.presenter.BasePresenter;
import com.hujianbo.base.view.BaseView;

/**
 * @author Administrator
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {


    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = initPresenter();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        if(null != presenter){
            presenter.detach();//释放presenter中解绑释放
            presenter = null;
        }
        super.onDestroy();

    }

    protected abstract P initPresenter();

    @Override
    public void dismissLoadingDialog() {
        removeProgressDialog();
    }

    @Override
    public void showLoadingDialog(String msg) {
        showProgressDialog();
    }
}
