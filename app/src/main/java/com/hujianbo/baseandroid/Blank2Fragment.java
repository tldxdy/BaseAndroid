package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hujianbo.base.base.BaseFragment;
import com.hujianbo.base.base.BaseObserver;
import com.hujianbo.http.Api;
import com.hujianbo.http.UtilRetrofit;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Blank2Fragment extends BaseFragment {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list)
    SmartRefreshLayout srl_list;


    NowAdapter adapter;
    List<TestBean.StoriesBean> list;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        srl_list.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();//结束加载
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();//结束刷新

            }
        });

        list = new ArrayList<>();
        adapter = new NowAdapter(mActivity,list);
        rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvList.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank2;
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
    }

    @Override
    protected void onLazyLoadOnce() {
        super.onLazyLoadOnce();
        BaseObserver observer = new BaseObserver<TestBean>(this){

            @Override
            public void success(TestBean testBeanBaseBean) {
                super.success(testBeanBaseBean);
                Log.e("AAAAA",testBeanBaseBean.toString());
                list.addAll(testBeanBaseBean.getStories());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void error() {
                super.error();
                Log.e("aaaa","错误");
            }
        };


        UtilRetrofit.getInstance().create(Api.class).test().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false, false, Color.parseColor("#ff0000"));
    }

}
