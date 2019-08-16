package com.hujianbo.base.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.hujianbo.base.R;
import com.hujianbo.base.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

public class BaseApp extends Application {
    private static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        init();
    }

    private void init() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                //全局设置（优先级最低）
//                layout.setEnableRefresh(true);
                layout.setEnableAutoLoadMore(true);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableOverScrollBounce(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
                layout.setEnableScrollContentWhenRefreshed(true);
                layout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
                layout.setDisableContentWhenLoading(true);//是否在加载的时候禁止列表的操作
                return new MaterialHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";//"上拉加载更多";
            ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";//"释放立即加载";
            ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新...";//"正在刷新...";
            ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载...";//"正在加载...";
            ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成";//"加载完成";
            ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败";//"加载失败";
            ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";//"没有更多数据了";
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    public static BaseApp getInstance(){
        return baseApp;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
