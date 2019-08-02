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

public class Blank4Fragment extends BaseFragment {


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
        qriv.setBorderColor(Color.parseColor("#0000ff"));


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

//        if (mNormalPopup == null){
//            mNormalPopup = new QMUIPopup(getContext(),QMUIPopup.DIRECTION_NONE);
//            TextView textView = new TextView(getContext());
//            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
//                    QMUIDisplayHelper.dp2px(getContext(), 250), WRAP_CONTENT
//            ));
//            textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
//            int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
//            textView.setPadding(padding, padding, padding, padding);
//            textView.setText("目的IP： 目的端口： ");
//            textView.setTextColor(0xffff0000);
//            mNormalPopup.setContentView(textView);
//
//
//
//
//
//        }
//        mNormalPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
//        mNormalPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
//        mNormalPopup.show(view);
    }




    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        new QMUIBottomSheet.BottomGridSheetBuilder(mActivity)
                .addItem(R.mipmap.ic_launcher, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "保存到本地", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case TAG_SHARE_WECHAT_FRIEND:
                            Toast.makeText(mActivity, "分享到微信", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_WECHAT_MOMENT:
                            Toast.makeText(mActivity, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_WEIBO:
                            Toast.makeText(mActivity, "分享到微博", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_CHAT:
                            Toast.makeText(mActivity, "分享到私信", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_LOCAL:
                            Toast.makeText(mActivity, "保存到本地", Toast.LENGTH_SHORT).show();
                            break;
                    }
                })
                .build().show();
    }
    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false,false, Color.parseColor("#ffff00"));
    }
}
