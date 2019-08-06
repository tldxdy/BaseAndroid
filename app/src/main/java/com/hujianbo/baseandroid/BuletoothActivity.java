package com.hujianbo.baseandroid;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.hujianbo.base.base.BaseActivity;

public class BuletoothActivity extends BaseActivity {

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buletooth);
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected void initView() {
        // Initializes Bluetooth adapter.

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected boolean showActionBar() {
        statusBar(true,false, Color.parseColor("#ffffff"));
        return false;
    }
}
