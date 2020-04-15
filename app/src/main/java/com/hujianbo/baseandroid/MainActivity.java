package com.hujianbo.baseandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hujianbo.base.base.BaseActivity;
import com.hujianbo.base.base.BaseObserver;
import com.hujianbo.base.model.BaseBean;
import com.hujianbo.base.util.LogUtils;
import com.hujianbo.base.util.UtilStatusBar;
import com.hujianbo.http.Api;
import com.hujianbo.http.UtilRetrofit;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindFloat;
import butterknife.BindFont;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.os.Build.VERSION_CODES.O;

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

        //showEmptyErrorLayout();

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
//            case R.id.textview1:
//                showCenterToast("点击了文本" + num);
//                num++;
//                break;
//            case R.id.btn_refresh:
//                showDataLayout();
//                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
