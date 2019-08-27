package com.hujianbo.baseandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.hujianbo.base.adapter.BaseViewPagerAdapter;
import com.hujianbo.base.base.BaseActivity;
import com.hujianbo.base.base.BaseFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedScrollViewActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.nsv_bg)
    JudgeNestedScrollView nsvBg;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tab_layout_go)
    SlidingTabLayout tabLayoutGo;
    @BindView(R.id.fl_activity)
    FrameLayout flActivity;
    private String[] mTitles = {"分享单品链接", "分享营销专场", "分享商城"};
    private List<BaseFragment> fragments = new ArrayList<>();

    int toolBarPositionY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);
        ButterKnife.bind(this);

        fragments.add(new Blank2Fragment());
        fragments.add(new Blank2Fragment());
        fragments.add(new Blank2Fragment());

        pager.setAdapter(new BaseViewPagerAdapter(baseActivity.getSupportFragmentManager(), fragments, mTitles));
        pager.setOffscreenPageLimit(3);
        tabLayout.setViewPager(pager);
        tabLayoutGo.setViewPager(pager);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();//结束加载
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();//结束刷新

            }
        });
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected String setTitle() {
        return "嵌套滑动";
    }

    @Override
    protected void initView() {

        actionBarLayout.post(new Runnable() {
            @Override
            public void run() {

                dealWithViewPager();

            }

        });

        nsvBg.setFillViewport(true);
        nsvBg.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                tabLayout.getLocationOnScreen(location);
                int xPosition = location[0];
                int yPosition = location[1];
                Log.e("toolBarPositionY", toolBarPositionY + "========" + yPosition);
                if (yPosition < toolBarPositionY + QMUIDisplayHelper.getStatusBarHeight(getApplicationContext())) {
                    tabLayoutGo.setVisibility(View.VISIBLE);

                    nsvBg.setNeedScroll(false);
                } else {
                    tabLayoutGo.setVisibility(View.GONE);
                    nsvBg.setNeedScroll(true);
                }
            }
        });
    }

    /**
     * 计算出滑动到顶点时的最大高度
     */
    private void dealWithViewPager() {
        toolBarPositionY = actionBarLayout.getHeight();
        ViewGroup.LayoutParams params = pager.getLayoutParams();
        params.height = QMUIDisplayHelper.getScreenHeight(getApplicationContext()) - tabLayout.getHeight() - toolBarPositionY + 1;
        pager.setLayoutParams(params);

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
