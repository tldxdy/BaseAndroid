package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hujianbo.base.base.BaseFragment;
import com.hujianbo.base.base.BaseObserver;
import com.hujianbo.base.model.BaseBean;
import com.hujianbo.base.util.LogUtils;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIBasePopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Blank2Fragment extends BaseFragment {


    @BindView(R.id.qbtn)
    QMUIButton qbtn;

    @BindView(R.id.qrbtn)
    QMUIRoundButton qrbtn;

    @BindView(R.id.qriv)
    QMUIRadiusImageView2 qriv;

    @Override
    protected void initView() {
        qriv.setImageResource(R.mipmap.ic_launcher);
        qriv.setCircle(true);
        qriv.setBorderWidth(10);
        qriv.setBorderColor(Color.parseColor("#ffff00"));


        //showErrorLayout();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }
    BaseObserver observer;
    protected boolean showActionBar() {
        return false;
    }

    @Override
    protected void getIntentData() {
        observer = new BaseObserver<BaseBean<String>>(this){
            @Override
            public void error() {
                super.error();
            }

            @Override
            public void success(BaseBean<String> stringBaseBean) {
                LogUtils.e(stringBaseBean.getData());
            }
        };
    }

    @Override
    protected int setActionBar() {
        return 0;
    }

    @Override
    protected void reload() {
        super.reload();
        showDataLayout();

    }


    @OnClick(R.id.qbtn)
    protected void onClick(){
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    showCenterToast("成功发送");
                    BaseBean baseBean = new BaseBean<String>();
                    baseBean.setCode(200);
                    baseBean.setData("正常");
                    observer.onNext(baseBean);
                })
                .show();

    }
    private QMUIPopup mNormalPopup;
    @OnClick(R.id.qrbtn)
    protected void qrbtn(View view){
        //普通浮层

        if (mNormalPopup == null){
            mNormalPopup = new QMUIPopup(getContext(),QMUIPopup.DIRECTION_NONE);
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    QMUIDisplayHelper.dp2px(getContext(), 250), WRAP_CONTENT
            ));
            textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
            int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("目的IP： 目的端口： ");
            textView.setTextColor(0xffff0000);
            mNormalPopup.setContentView(textView);





        }
        mNormalPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mNormalPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
        mNormalPopup.show(view);
    }

    private void showSimpleBottomSheetGrid() {


        new QMUIBottomSheet.BottomListSheetBuilder(mActivity)
                .addItem(R.mipmap.ic_launcher, "分享到微信", "分享到微信")
                .addItem(R.mipmap.ic_launcher, "分享到朋友圈", "分享到朋友圈")
                .addItem(R.mipmap.ic_launcher, "分享到微博", "分享到微博")
                .addItem(R.mipmap.ic_launcher, "分享到私信", "分享到私信")
                .addItem(R.mipmap.ic_launcher, "保存到本地", "保存到本地")
                .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                    dialog.dismiss();
                }).build().show();

    }
    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false,false, Color.parseColor("#ff0000"));
    }

}
