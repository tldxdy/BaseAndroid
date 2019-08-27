package com.hujianbo.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.hujianbo.base.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

public class ImagePager2Adapter extends PagerAdapter {

    private List<String> imageList;
    private Context context;
    public ImagePager2Adapter(Context context,List<String> imageList){
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList == null ? 0 : imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PhotoView view = new PhotoView(context);
        GlideUtils.loadImage(context, imageList.get(position), view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((PhotoView)object);
    }
}
