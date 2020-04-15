package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.hujianbo.base.adapter.QMUIFragmentPagerAdapter;
import com.hujianbo.base.base.BaseActivity;
import com.hujianbo.base.base.BaseFragment;
import com.hujianbo.base.adapter.BaseViewPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.qviewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    List<BaseFragment> baseFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected void initView() {

        QMUIFragmentPagerAdapter pagerAdapter = new QMUIFragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public BaseFragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new Blank1Fragment();
                    case 1:
                        return new Blank2Fragment();
                    case 2:
                        return new Blank3Fragment();
                    case 3:
                    default:
                        return new Blank4Fragment();

                }
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "TabSegment";
                    case 1:
                        return "CTopBar";
                    case 2:
                        return "IViewPager";
                    case 3:
                    default:
                        return "ViewPager";
                }
            }
        };

        baseFragments = new ArrayList<>();
        baseFragments.add(new Blank1Fragment());
        baseFragments.add(new Blank2Fragment());
        baseFragments.add(new Blank3Fragment());
        baseFragments.add(new Blank4Fragment());
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getSupportFragmentManager(),baseFragments,new String[]{"首页","新闻","通知","我的"});

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);

        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_TOP);//设置indicator显示位置
        mTabSegment.setHasIndicator(true);//设置是否需要显示 indicator
        mTabSegment.setupWithViewPager(mViewPager);


        mTabSegment.reset();
        mTabSegment.setHasIndicator(false);//设置是否需要显示 indicator
        QMUITabSegment.Tab component1 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "首页", true);
        QMUITabSegment.Tab component2 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "新闻", true);
        QMUITabSegment.Tab component3 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "通知", true);
        QMUITabSegment.Tab component4 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "我的", true);

        mTabSegment.addTab(component1);
        mTabSegment.addTab(component2);
        mTabSegment.addTab(component3);
        mTabSegment.addTab(component4);
        mTabSegment.notifyDataChanged();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected boolean showActionBar() {
        statusBar(false,false, Color.parseColor("#ffffff"));
        return false;
    }
}
