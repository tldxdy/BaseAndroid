package com.hujianbo.baseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hujianbo.base.base.BaseActivity;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    @Override
    protected String setTitle() {
        return "constrainLayout 2.0";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected boolean showActionBar() {
        return true;
    }
}
