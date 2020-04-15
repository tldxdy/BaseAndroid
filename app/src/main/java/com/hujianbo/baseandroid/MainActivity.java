package com.hujianbo.baseandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;


import com.hujianbo.base.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.frames)
    FrameLayout frames;
    private FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected String setTitle() {
        return "首页";
    }


    @Override
    protected void initView() {
        //setIvBackBtn(false);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frames, new BlankFragment(), "测试").commit();

        showEmptyErrorLayout();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected boolean showActionBar() {
        statusBar(true,false,Color.parseColor("#ffffff"));
        //UtilStatusBar.setStatusBarColor(baseActivity, Color.parseColor("#ffffff"), false);
        return false;
    }


    private int num = 0;
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn_refresh:
                showDataLayout();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
