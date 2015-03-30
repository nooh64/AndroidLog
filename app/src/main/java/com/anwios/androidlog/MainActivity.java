package com.anwios.androidlog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import com.anwios.alog.ALog;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ALog.d("Hello World","Hoy");
        ALog.d(1,2,3,4,5,6,7,8,9);
        ALog.d(1.2,.13);
    }
}
