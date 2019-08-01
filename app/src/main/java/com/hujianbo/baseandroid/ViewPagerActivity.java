package com.hujianbo.baseandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hujianbo.base.base.BaseActivity;
import com.hujianbo.base.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import butterknife.BindView;

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.qviewpager)
    QMUIViewPager mViewPager;

    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

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

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        QMUITabSegment.Tab component1 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "新闻", false);
        QMUITabSegment.Tab component2 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "新闻", false);
        QMUITabSegment.Tab component3 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "新闻", false);
        QMUITabSegment.Tab component4 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_home_selected),
                "新闻", false);

        mTabSegment.addTab(component1);
        mTabSegment.addTab(component2);
        mTabSegment.addTab(component3);
        mTabSegment.addTab(component4);

        mTabSegment.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected boolean showActionBar() {
        statusBar(true,false,0);
        return false;
    }
}
