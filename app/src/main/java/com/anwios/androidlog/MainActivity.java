package com.anwios.androidlog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.anwios.alog.Logs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logs.setHierarchy(4);
        Logs.d("Message 1", "Message 2", "Message 3");
        Logs.d(1, 2, 3);
        Logs.d(1.2, .13);
        Logs.toast(this, "Hello");


        List list=new ArrayList<String>();
        list.add("qwert");
        list.add("asdfgh");
        list.add("zxcvbn");
        Logs.a(list);

        EditText b=new EditText(this);
        b.setText("Helllo");
        Logs.a(b);

        File f=new File("/hello");
        Logs.a(f);
    }
}
