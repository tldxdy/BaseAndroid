package com.hujianbo.baseandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hujianbo.base.base.BaseFragment;
import com.hujianbo.base.base.ImagePageActivity;
import com.qmuiteam.qmui.layout.QMUIButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIDrawableHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUISpanHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Blank1Fragment extends BaseFragment {


    @BindView(R.id.qbtn)
    QMUIButton qbtn;

    @BindView(R.id.qrbtn)
    QMUIRoundButton qrbtn;

    @BindView(R.id.qriv)
    QMUIRadiusImageView2 qriv;

    @BindView(R.id.chat_input)
    ChatInputView chat_input;

    @Override
    protected void initView() {
        qbtn.setText("点击查看大图");
        qriv.setImageResource(R.mipmap.ic_launcher);
        qriv.setCircle(true);
        qriv.setBorderWidth(10);
        qriv.setBorderColor(Color.parseColor("#ff0000"));

        chat_input.setMenuContainerHeight(QMUIDisplayHelper.dp2px(mActivity,0));

        chat_input.setMenuClickListener(new OnMenuClickListener() {

            @Override
            public boolean onSendTextMessage(CharSequence input) {
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {

            }

            @Override
            public boolean switchToMicrophoneMode() {
                return false;
            }

            @Override
            public boolean switchToGalleryMode() {
                return false;
            }

            @Override
            public boolean switchToCameraMode() {
                return false;
            }

            @Override
            public boolean switchToEmojiMode() {
                return false;
            }
        });
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
        ArrayList<String> imageUrl = new ArrayList<>();
        imageUrl.add("http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg");
        imageUrl.add("http://pic26.nipic.com/20121221/9252150_142515375000_2.jpg");
        imageUrl.add("http://pic16.nipic.com/20111006/6239936_092702973000_2.jpg");
        imageUrl.add("http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg");
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("确定", (dialog, index) -> {
                    dialog.dismiss();
                    showCenterToast("成功发送");
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("imgList",imageUrl);
                    mActivity.intent(ImagePageActivity.class,bundle);
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

    @Override
    protected void onVisibleToUser() {
        super.onVisibleToUser();
        mActivity.statusBar(false,false, Color.parseColor("#ffffff"));
    }
}
