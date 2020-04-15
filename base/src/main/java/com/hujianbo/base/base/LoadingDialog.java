package com.hujianbo.base.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.hujianbo.base.R;


public class LoadingDialog extends Dialog {
	private boolean cancle = true;
	public LoadingDialog(Context context) {
		super(context, R.style.CustomDialog);
		// TODO Auto-generated constructor stub
	}

	public LoadingDialog(Context context, boolean cancle) {
		super(context, R.style.CustomDialog);
		this.cancle = cancle;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_loading);
		setCanceledOnTouchOutside(false);
		setCancelable(cancle);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}
}
