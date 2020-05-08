package com.hujianbo.base.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hujianbo.base.R;
import com.hujianbo.base.util.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by liu on 2017/9/26.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Unbinder mUnbinder;

    private View baseView;
    protected FrameLayout frameContext;
    protected FrameLayout frameError;
    protected FrameLayout frameActionBar;
    protected BaseActivity mActivity;
    protected View rootView;

    private ViewStub stubActionBar;
    private ViewStub stubError;

    protected boolean mIsLoadedData = false;
    public CompositeDisposable mDisposable = new CompositeDisposable();
    /**
     * 全局的加载框对象，已经完成初始化.
     */
    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected abstract boolean showActionBar();

    protected abstract void getIntentData();

    protected abstract int setActionBar();

    protected void reload() {

    }

    protected int loadingLayout() {
        return R.layout.fragment_base_loading;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            handleOnVisibilityChangedToUser(isVisibleToUser);
        }
    }

    /**
     * 处理对用户是否可见
     *
     * @param isVisibleToUser
     */
    private void handleOnVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {

            LogUtils.e("visableTag","isVisibleToUser current=>>" + this.getClass().getSimpleName() + "  " + isVisibleToUser);

            // 对用户可见
            if (!mIsLoadedData) {
                mIsLoadedData = true;
                onLazyLoadOnce();

            }
            onVisibleToUser();
        } else {
            // 对用户不可见
            onInvisibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(true);
        }
    }

    /**
     * 懒加载一次。如果只想在对用户可见时才加载数据，并且只加载一次数据，在子类中重写该方法
     */
    protected void onLazyLoadOnce() {
    }

    /**
     * 对用户可见时触发该方法。如果只想在对用户可见时才加载数据，在子类中重写该方法
     */
    protected void onVisibleToUser() {
    }




    /**
     * 对用户不可见时触发该方法
     */
    protected void onInvisibleToUser() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (baseView == null) {
            baseView = inflater.inflate(R.layout.fragment_base, container, false);
            frameContext = baseView.findViewById(R.id.frame_context);
            rootView = inflater.inflate(getLayoutId(), frameContext);
            if (showActionBar()) {
                if (stubActionBar == null) {
                    stubActionBar = baseView.findViewById(R.id.view_stub_actionbar);
                    frameActionBar = (FrameLayout) stubActionBar.inflate();
                    frameActionBar.setId(999);
                }
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameContext.getLayoutParams();
                params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
                inflater.inflate(setActionBar(), frameActionBar);
            }
            getIntentData();
            mUnbinder = ButterKnife.bind(this,frameContext);
            initView();
        }

        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    /**
     * 显示自定义标题栏
     *
     * @param resId
     */
    protected void setCustomActionBar(@LayoutRes int resId) {
        frameActionBar.setVisibility(View.VISIBLE);
        frameActionBar.removeAllViews();
        LayoutInflater.from(mActivity).inflate(resId, frameActionBar);
    }

    protected void showDataLayout() {
        if (frameContext != null) {
            frameContext.setVisibility(View.VISIBLE);
        }
        if (frameError != null && frameError.getVisibility() != View.GONE) {
            frameError.setVisibility(View.GONE);
        }
    }

    protected void showEmptyErrorLayout() {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(R.layout.base_empty_layout, frameError);
    }


    protected void showEmptyErrorLayout(String str) {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(R.layout.base_empty_layout, frameError);
        TextView view = frameError.findViewById(R.id.tv_desc);
        view.setText(str);
    }


    @SuppressLint("ResourceType")
    protected void showEmptyErrorLayout(String str, @IdRes int resId) {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(R.layout.base_empty_layout, frameError);
        TextView view = frameError.findViewById(R.id.tv_desc);
        ImageView imageView = frameError.findViewById(R.id.iv_empty);
        imageView.setImageResource(resId);
        view.setText(str);
    }

    protected void showErrorLayout() {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(R.layout.base_network_error_layout, frameError);
        frameError.findViewById(R.id.btn_refresh).setOnClickListener(this);
    }

    protected void showErrorLayout(String str) {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        View view = LayoutInflater.from(mActivity).inflate(R.layout.base_network_error_layout, frameError);
        TextView textView = view.findViewById(R.id.tv_des);
        textView.setText(str);
        view.findViewById(R.id.btn_refresh).setOnClickListener(this);
        frameError.findViewById(R.id.btn_refresh).setOnClickListener(this);
    }

    protected void showErrorLayout(@LayoutRes int resId) {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(resId, frameError);
    }


    protected void showErrorLayout(@LayoutRes int resId, View.OnClickListener listener, @IdRes int... id) {
        frameContext.setVisibility(View.GONE);
        if (stubError == null) {
            stubError = baseView.findViewById(R.id.view_stub_error);
            frameError = (FrameLayout) stubError.inflate();
        } else {
            frameError.setVisibility(View.VISIBLE);
            frameError.removeAllViews();
        }
        if (frameActionBar != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameError.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, frameActionBar.getId());
        }
        LayoutInflater.from(mActivity).inflate(resId, frameError);
        for (int i = 0; i < id.length; i++) {
            frameError.findViewById(id[i]).setOnClickListener(listener);
        }
    }


    public void showCenterToast(String text) {
        mActivity.showCenterToast(text);
    }
    public void showBottomToast(String text) {
        mActivity.showBottomToast(text);
    }


    public void showProgressDialog() {
        mActivity.showProgressDialog();
    }

    public void removeProgressDialog() {
        mActivity.removeProgressDialog();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_refresh) {
            reload();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
