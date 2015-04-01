package com.anwios.androidlog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import com.anwios.alog.ALog;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ALog.setHierarchy(4);
        ALog.d("Message 1","Message 2","Message 3");
        ALog.d(1,2,3);
        ALog.d(1.2,.13);
        ALog.toast(this,"Hello");
    }
}
