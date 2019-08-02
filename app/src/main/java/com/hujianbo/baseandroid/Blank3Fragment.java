package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hujianbo.base.base.BaseFragment;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Blank3Fragment extends BaseFragment {



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
        qriv.setBorderColor(Color.parseColor("#ff00ff"));


        //showErrorLayout();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
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
                })
                .show();

    }
    private QMUIPopup mNormalPopup;
    @OnClick(R.id.qrbtn)
    protected void qrbtn(View view){
        //普通浮层
        showSimpleBottomSheetGrid();

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
        mActivity.statusBar(false,true, Color.parseColor("#666666"));
    }
}
