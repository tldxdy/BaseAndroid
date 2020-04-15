package com.hujianbo.base.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hujianbo.base.R;
import com.hujianbo.base.base.BaseApp;



public class CustomToast extends Toast {

	LayoutInflater inflater = null;

	private TextView messageView = null;

	public CustomToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		View toastView = inflater.inflate(R.layout.toast_layout, null);
		messageView = toastView.findViewById(R.id.toast_message);
		setView(toastView);
	}

	@Override
	public void setText(CharSequence s) {
		// TODO Auto-generated method stub
		messageView.setText(s);
	}

	@Override
	public void setText(int resId) {
		// TODO Auto-generated method stub
		messageView.setText(resId);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	public static CustomToast makeText(Context context,CharSequence text) {
		CustomToast toast = new CustomToast(context);
		toast.setText(text);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		return toast;
	}

	public static CustomToast makeBottomText(Context context,CharSequence text) {
		CustomToast toast = new CustomToast(context);
		toast.setText(text);
		toast.setGravity(Gravity.BOTTOM, 0, 80);
		toast.setDuration(Toast.LENGTH_SHORT);
		return toast;
	}

}
