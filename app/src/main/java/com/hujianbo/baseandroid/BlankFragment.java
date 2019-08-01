package com.hujianbo.baseandroid;

import com.hujianbo.base.base.BaseFragment;

public class BlankFragment extends BaseFragment {

    @Override
    protected void initView() {
        showErrorLayout();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected boolean showActionBar() {
        return false;
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected int setActionBar() {
        return 0;
    }

    @Override
    protected void reload() {
        super.reload();
        showDataLayout();

    }


}
