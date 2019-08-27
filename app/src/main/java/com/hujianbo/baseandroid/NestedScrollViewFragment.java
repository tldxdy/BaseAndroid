package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.hujianbo.base.base.BaseFragment;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class NestedScrollViewFragment extends BaseFragment {



    @Override
    protected void initView() {
        mUnbinder = ButterKnife.bind(this,frameContext);




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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }



}
