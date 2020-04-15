package com.hujianbo.baseandroid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.hujianbo.base.base.BaseFragment;
import com.hujianbo.base.util.photoutils.PhotoUtils;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Blank3Fragment extends BaseFragment {


    private static final int NEED_CAMERA = 200;
    @BindView(R.id.qbtn)
    QMUIButton qbtn;

    @BindView(R.id.qrbtn)
    QMUIRoundButton qrbtn;

    @BindView(R.id.qriv)
    QMUIRadiusImageView2 qriv;

    @Override
    protected void initView() {
        initPhotoError();
        qriv.setImageResource(R.mipmap.ic_launcher);
        qriv.setCircle(true);
        qriv.setBorderWidth(10);
        qriv.setBorderColor(Color.parseColor("#ff00ff"));

//aaaaaaaaaaaaaaaaaaaaaa
        //showErrorLayout();

        PhotoUtils.getInstance().init(mActivity,true, new PhotoUtils.OnSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                //asdddddddddddddddddddddddd
                Glide.with(mActivity).load(outputUri).into(qriv);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank3;
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

    private void initPhotoError(){
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
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

    @OnClick(R.id.qriv)
    protected void qriv(View view){
        Log.e("aaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaa");
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA}, NEED_CAMERA);
            }else {
                setPhoto();
            }
        } else {
            setPhoto();
        }
    }

    private void setPhoto() {
        final String[] items = new String[]{"拍照", "相册"};
        //普通浮层
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                PhotoUtils.getInstance().takePhoto();
                                break;
                            case 1:
                                PhotoUtils.getInstance().selectPhoto();
                                break;
                                default:
                                    break;
                        }
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case NEED_CAMERA:
                // 如果权限被拒绝，grantResults 为空
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    setPhoto();
                } else {

                }
                break;
            default:
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtils.getInstance().bindForResult(requestCode, resultCode, data);
    }

    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false,true, Color.parseColor("#666666"));
    }
}
