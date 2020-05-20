package com.hujianbo.base.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hujianbo.base.presenter.BasePresenter;
import com.hujianbo.base.view.BaseView;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    public P presenter;
    protected abstract P initPresenter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = initPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(null != presenter){
            presenter.detach();//释放presenter中解绑释放
            presenter = null;
        }
        super.onDestroyView();

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

}
