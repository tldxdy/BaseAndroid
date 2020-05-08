package com.hujianbo.base.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hujianbo.base.R;
import com.hujianbo.base.util.AppManager;
import com.hujianbo.base.util.CustomToast;
import com.hujianbo.base.util.UtilIntent;
import com.hujianbo.base.util.UtilStatusBar;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected BaseActivity baseActivity;

    public CompositeDisposable mDisposable = new CompositeDisposable();


    /**
     * 全局的加载框对象，已经完成初始化.
     */
    private LoadingDialog mProgressDialog;
    /**
     * 初始化base布局
     */
    protected FrameLayout actionBarLayout;
    protected FrameLayout errorLayout;
    protected FrameLayout contentLayout;

    /**
     *
     */
    private TextView tvTitle;

    private ViewStub stubActionBar;
    private ViewStub stubError;
    private ImageButton ivBackBtn;
    private ImageView ivRight;
    private TextView tvActionBarRight;
    private TextView btnRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;
        AppManager.create().addActivity(this);
        getIntentData();
    }


    /**
     *              设置状态栏状态
     * @param isTranslucent     是否沉浸式状态栏（true为是）
     * @param isStatusBar       电池栏颜色（true为白色，false为黑色）
     * @param statusColor       不是沉浸式时，设置状态栏颜色
     */
    public void statusBar(boolean isTranslucent,boolean isStatusBar,int statusColor){
        if(isTranslucent){
            // 沉浸式状态栏
            QMUIStatusBarHelper.translucent(this);
            if(isStatusBar){
                QMUIStatusBarHelper.setStatusBarDarkMode(this);
            }else {
                QMUIStatusBarHelper.setStatusBarLightMode(this);
            }
        }else {
            UtilStatusBar.setStatusBarColor(this, statusColor, isStatusBar);
        }


    }

    /**
     * 设置标题
     *
     * @return
     */
    protected abstract String setTitle();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取Intent参数
     */
    protected abstract void getIntentData();

    /**
     * 是否显示标题栏
     *
     * @return
     */
    protected abstract boolean showActionBar();

    @SuppressLint("ResourceType")
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        contentLayout = findViewById(R.id.frame_layout);
        /** 布局替换base布局 **/
        LayoutInflater.from(this).inflate(layoutResID, contentLayout);
        /** 是否显示bar **/
        if(showActionBar()){
            if(stubActionBar == null){
                stubActionBar = findViewById(R.id.view_stub_actionbar);
                actionBarLayout = (FrameLayout) stubActionBar.inflate();
                actionBarLayout.setId(999);
            }
            /** 构建Base初始化ActionBar **/
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
            LayoutInflater.from(this).inflate(R.layout.base_actionbar, actionBarLayout);
            tvTitle = actionBarLayout.findViewById(R.id.tv_actionbar_title);
            ivBackBtn = actionBarLayout.findViewById(R.id.iv_back_btn);
            ivBackBtn.setOnClickListener(this);
            ivRight = actionBarLayout.findViewById(R.id.iv_actionbar_right);
            ivRight.setOnClickListener(this);
            tvActionBarRight = actionBarLayout.findViewById(R.id.tv_actionbar_right);
            tvActionBarRight.setOnClickListener(this);
            if (!TextUtils.isEmpty(setTitle())) {
                tvTitle.setText(setTitle());
            }

        }
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void setIvBackBtn(boolean falg) {
        ivBackBtn.setVisibility(falg?View.VISIBLE:View.GONE);
    }

    /**
     * 设置中间标题
     *
     * @param str
     */
    protected void setCenterTitle(String str) {
        tvTitle.setText(str);
    }
    /**
     * 显示右面的图标
     *
     * @param id
     */
    protected void showRightImage(int id) {
        if (tvActionBarRight != null) {
            tvActionBarRight.setVisibility(View.GONE);
        }
        if (ivRight != null) {
            ivRight.setVisibility(View.VISIBLE);
            ivRight.setImageResource(id);
        }
    }


    protected TextView getRightText() {
        return tvActionBarRight;
    }

    /**
     * 显示右面的文字
     *
     * @param str
     */
    protected void showRightText(String str) {
        if (ivRight != null) {
            ivRight.setVisibility(View.GONE);
        }
        if (tvActionBarRight != null) {
            tvActionBarRight.setVisibility(View.VISIBLE);
            tvActionBarRight.setText(str);
        }
    }

    protected void setRightTextColor(int color) {
        if (tvActionBarRight != null) {
            tvActionBarRight.setTextColor(color);
        }
    }

    protected void isShowRightImage(boolean b) {

        if (ivRight != null) {
            ivRight.setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }

    protected void isShowRightTextView(boolean b) {
        if (tvActionBarRight != null) {
            tvActionBarRight.setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_back_btn) {
            finishAnim(this);
        }else if(i == R.id.btn_refresh){
            reload();
        }
    }

    protected void reload() {
        initData();
    }


    /**
     * 显示自定义标题栏
     *
     * @param resId
     */
    protected void setCustomActionBar(@LayoutRes int resId) {
        actionBarLayout.setVisibility(View.VISIBLE);
        actionBarLayout.removeAllViews();
        LayoutInflater.from(this).inflate(resId, actionBarLayout);
    }

    /**
     * 显示有数据界面
     */
    protected void showDataLayout() {
        contentLayout.setVisibility(View.VISIBLE);
        if (errorLayout != null && errorLayout.getVisibility() != View.GONE) {
            errorLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 无数据界面
     */
    protected void showEmptyErrorLayout() {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(R.layout.base_empty_layout, errorLayout);
    }


    protected void showEmptyErrorLayout(String str) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(R.layout.base_empty_layout, errorLayout);
        TextView view = errorLayout.findViewById(R.id.tv_desc);
        btnRefresh = errorLayout.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(this);
        view.setText(str);
    }


    protected void showEmptyErrorLayout(String str, int image) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(R.layout.base_empty_layout, errorLayout);
        TextView view = errorLayout.findViewById(R.id.tv_desc);
        view.setText(str);
        ImageView imageView = errorLayout.findViewById(R.id.iv_empty);
        imageView.setImageResource(image);
    }

    protected void showEmptyErrorLayout(@LayoutRes int resId) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(resId, errorLayout);
    }


    /**
     * 无网络界面
     */
    protected void showErrorLayout() {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(R.layout.base_network_error_layout, errorLayout);
        btnRefresh = errorLayout.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(this);
    }

    protected void showErrorLayout(String str) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(R.layout.base_network_error_layout, errorLayout);
        btnRefresh = errorLayout.findViewById(R.id.btn_refresh);
        TextView textView = errorLayout.findViewById(R.id.tv_des);
        textView.setText(str);
        btnRefresh.setOnClickListener(this);
    }

    protected void showErrorLayout(@LayoutRes int resId) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(resId, errorLayout);

    }


    protected void showErrorLayout(@LayoutRes int resId, View.OnClickListener listener, @IdRes int... id) {
        contentLayout.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = findViewById(R.id.view_stub_error);
            errorLayout = (FrameLayout) stubError.inflate();
        } else {
            errorLayout.setVisibility(View.VISIBLE);
            errorLayout.removeAllViews();
        }
        if (actionBarLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) errorLayout.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, actionBarLayout.getId());
        }
        LayoutInflater.from(this).inflate(resId, errorLayout);
        for (int i = 0; i < id.length; i++) {
            findViewById(id[i]).setOnClickListener(listener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
        AppManager.create().finishActivity(this);
    }

    /**
     *          消息
     * @param text
     */
    public void showCenterToast(String text){
        CustomToast.makeText(this,text).show();
    }
    /**
     *          消息
     * @param text
     */
    public void showBottomToast(String text){
        CustomToast.makeBottomText(this,text).show();
    }

    /**
     * 显示加载框
     */
    public void showProgressDialog() {
        if(null == mProgressDialog){
            mProgressDialog = new LoadingDialog(this);
        }
        //如果加载框不显示， 那么就显示加载框
        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }
    /**
     * 显示加载框
     */
    public void showProgressDialogNoCancle() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingDialog(this,false);
        }
        //如果加载框不显示， 那么就显示加载框
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 移除加载框.
     */
    public void removeProgressDialog() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    /**
     * 页面跳转
     *
     * @param classes c
     */
    public void intent(final Class<?> classes) {
        UtilIntent.intentDIY(this, classes);
    }

    /**
     * 自定义页面跳转动画
     *
     * @param classes   目标
     * @param enterAnim 进入动画文件ID
     * @param exitAnim  退出动画文件ID
     */
    public void intent(Class<?> classes, int enterAnim, int exitAnim) {
        UtilIntent.intentDIY(this, classes,null, enterAnim, exitAnim);
    }

    /**
     * 页面跳转
     *
     * @param classes 目标
     * @param
     */
    public void intent(final Class<?> classes, final Bundle bundle) {
        UtilIntent.intentDIY(this, classes, bundle);
    }

    public void finishAnim(Activity activity) {
        UtilIntent.finishDIY(activity);
    }
    @Override
    public void onBackPressed() {
        finishAnim(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
