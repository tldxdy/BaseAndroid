package com.hujianbo.base.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hujianbo.base.base.BaseFragment;

import java.util.List;



public class BaseViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;

    private List<BaseFragment> fragmentList;
    public BaseViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public BaseViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null&&titles.length==fragmentList.size()){
            return titles[position];
        }
        return null;

    }
}
