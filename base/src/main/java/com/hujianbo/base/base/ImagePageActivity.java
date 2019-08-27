package com.hujianbo.base.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;
import com.hujianbo.base.R;
import com.hujianbo.base.adapter.ImagePager2Adapter;
import com.hujianbo.base.adapter.ImagePagerAdapter;
import com.hujianbo.base.util.GlideUtils;
import com.hujianbo.base.view.ImagePager;

import java.util.ArrayList;
import java.util.List;


public class ImagePageActivity extends BaseActivity {

    ImageView ivBack;
    TextView tvNum;
    ImagePager mPager;

    private List<String> imgList;
    private int total = 0;
    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_page);
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvNum = findViewById(R.id.tv_num);
        mPager = findViewById(R.id.pager);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tvNum.setText((i + 1) + "/" + total);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tvNum = findViewById(R.id.tv_num);
        if(imgList != null && !imgList.isEmpty()){
//            ArrayList<PhotoView> data = new ArrayList<>();
//            for (int i = 0; i < imgList.size(); i++){
//                PhotoView view = new PhotoView(baseActivity);
//                GlideUtils.loadImage(baseActivity, imgList.get(i), view);
//                data.add(view);
//            }
//            ImagePagerAdapter adapter = new ImagePagerAdapter();
//            adapter.setData(data);
//            mPager.setAdapter(adapter);
//            total = data.size();

            ImagePager2Adapter adapter = new ImagePager2Adapter(baseActivity,imgList);
            mPager.setAdapter(adapter);
            total = imgList.size();

            tvNum.setText((position+1)+"/" + total);
            mPager.setCurrentItem(position);
        }
        ivBack = findViewById(R.id.iv_back);
        ivBack.setColorFilter(0xffffffff);
        ivBack.setOnClickListener(this);
        tvNum.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            showCenterToast("没有图片");
            onBackPressed();
            return;
        }
        imgList = bundle.getStringArrayList("imgList");
        if(imgList.isEmpty()){
            showCenterToast("没有图片");
            onBackPressed();
            return;
        }
        total = imgList.size();
        position = bundle.getInt("position",0);
    }

    @Override
    protected boolean showActionBar() {
        statusBar(false, true, Color.parseColor("#000000"));
        return false;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if(view.getId() == R.id.iv_back){
            onBackPressed();
        }
    }
}
