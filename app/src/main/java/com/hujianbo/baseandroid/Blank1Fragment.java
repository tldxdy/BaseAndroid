package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.azhon.appupdate.dialog.NumberProgressBar;
import com.azhon.appupdate.manager.DownloadManager;
import com.hujianbo.base.base.BaseFragment;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Blank1Fragment extends BaseFragment {


    private NumberProgressBar progressBar;
    private DownloadManager manager;
    private String url = "http://files.yunduancn.com/2020/03/23/1584928750203000.apk";


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
        qriv.setBorderColor(Color.parseColor("#ff0000"));


        //showErrorLayout();

    }

    @Override
    protected void initData() {

    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank1;
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
                    //startUpdate2();
                })
                .show();

    }

    private void startUpdate2() {
        manager = DownloadManager.getInstance(mActivity);
        manager.setApkName("ESFileExplorer.apk")
                .setApkUrl(url)
                .setSmallIcon(R.mipmap.ic_launcher)
                .download();
    }
    private QMUIPopup mNormalPopup;
    @OnClick(R.id.qrbtn)
    protected void qrbtn(View view){
        //普通浮层

        if (mNormalPopup == null){
            mNormalPopup = new QMUIPopup(mActivity,QMUIPopup.DIRECTION_NONE);
            TextView textView = new TextView(mActivity);
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    QMUIDisplayHelper.dp2px(mActivity, 250), WRAP_CONTENT
            ));
            textView.setLineSpacing(QMUIDisplayHelper.dp2px(mActivity, 4), 1.0f);
            int padding = QMUIDisplayHelper.dp2px(mActivity, 20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("目的IP： 目的端口： ");
            textView.setTextColor(0xffff0000);
            mNormalPopup.setContentView(textView);





        }
        mNormalPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mNormalPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
        mNormalPopup.show(view);
    }

    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false,false, Color.parseColor("#ffffff"));
    }
}
