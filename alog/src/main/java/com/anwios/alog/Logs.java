package com.anwios.alog;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class Logs {
    enum Type {D, E, I, V, W, WTF}

    // ===========================================================
    // Constants
    // ===========================================================
    private static final Logs INSTANCE = new Logs();
    private final String BREAK =     "|____________________________________________________________________________________________________________________________";
    private final String MSG_BREAK = "+============================================================================================================================";
    // ===========================================================
    // Fields
    // ===========================================================
    private String tag = "ATAG";
    private int hierarchyLevel = 1;
    private boolean showHierarchy = true;
    private boolean showHeaderFooter = true;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================
    public static Logs setTag(String tag) {
        INSTANCE.tag = tag;
        return INSTANCE;
    }

    public static Logs setHierarchy(int length) {
        INSTANCE.hierarchyLevel = length;
        return INSTANCE;
    }

    public static Logs showHeaderAndFooter(boolean b) {
        INSTANCE.showHeaderFooter = b;
        return INSTANCE;
    }

    public static Logs showHierarchy(boolean b) {
        INSTANCE.showHierarchy = b;
        return INSTANCE;
    }

    public static void a(Object o) {
        INSTANCE.putHeader(Type.I);
        INSTANCE.processObject(o);
        INSTANCE.putStackTrace(Type.I);
        INSTANCE.putFooter(Type.I);
    }

    public static void i(String... msg) {
        INSTANCE.putHeader(Type.I);
        INSTANCE.putMessage(Type.I, msg);
        INSTANCE.putStackTrace(Type.I);
        INSTANCE.putFooter(Type.I);
    }

    public static void v(String... msg) {
        INSTANCE.putHeader(Type.V);
        INSTANCE.putMessage(Type.V, msg);
        INSTANCE.putStackTrace(Type.V);
        INSTANCE.putFooter(Type.V);
    }

    public static void w(String... msg) {
        INSTANCE.putHeader(Type.W);
        INSTANCE.putMessage(Type.W, msg);
        INSTANCE.putStackTrace(Type.W);
        INSTANCE.putFooter(Type.W);
    }

    public static void wtf(String... msg) {
        INSTANCE.putHeader(Type.WTF);
        INSTANCE.putMessage(Type.WTF, msg);
        INSTANCE.putStackTrace(Type.WTF);
        INSTANCE.putFooter(Type.WTF);
    }

    public static void d(String... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
        INSTANCE.putFooter(Type.D);
    }

    public static void d(int... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
        INSTANCE.putFooter(Type.D);
    }
    public static void d(long... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
        INSTANCE.putFooter(Type.D);
    }
    public static void d(float... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
        INSTANCE.putFooter(Type.D);
    }
    public static void d(double... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
        INSTANCE.putFooter(Type.D);
    }

    public static void e(String msg) {
        INSTANCE.putHeader(Type.E);
        INSTANCE.putMessage(Type.E, msg);
        INSTANCE.putStackTrace(Type.E);
        INSTANCE.putFooter(Type.E);
    }

    public static void toast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    private void putStackTrace(Type type) {
        if (showHierarchy) {
            log(type, INSTANCE.BREAK);
            log(type, "| Stack Trace :");
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            for (int i = 0; i < INSTANCE.hierarchyLevel; i++) {
                String line = stackTraceElements[i + 4].getMethodName() + "  -> (" + stackTraceElements[i + 4].getFileName() + ":" + stackTraceElements[i + 4].getLineNumber()+")";
                String space = String.format("%" + (i + 1) * 2 + "s", " ");
                log(type, "|" + space + line);
            }
        }
    }

    private void putHeader(Type type) {
        if (showHeaderFooter) {
            log(type, "|");
            log(type, INSTANCE.MSG_BREAK);
            log(type, "| Message :");
        }
    }

    private void putFooter(Type type) {
        if (showHeaderFooter) {
            log(type, INSTANCE.MSG_BREAK);
            log(type, "|");
        }
    }

    private void putMessage(Type type, String... messages) {
        for (String message : messages)
        log(type, "| " + message);
    }

    private void putMessage(Type type, int... messages) {
        for (int message : messages)
            log(type, "| " + message);
    }

    private void putMessage(Type type, long... messages) {
        for (long message : messages)
            log(type, "| " + message);
    }

    private void putMessage(Type type, float... messages) {
        for (float message : messages)
            log(type, "| " + message);
    }

    private void putMessage(Type type, double... messages) {
        for (double message : messages)
            log(type, "| " + message);
    }

    private void log(Type type, String msg) {
        switch (type) {
            case D:
                Log.d(INSTANCE.tag, msg);
                break;
            case E:
                Log.e(INSTANCE.tag, msg);
                break;
            case I:
                Log.i(INSTANCE.tag, msg);
                break;
            case V:
                Log.v(INSTANCE.tag, msg);
                break;
            case W:
                Log.w(INSTANCE.tag, msg);
                break;
            case WTF:
                Log.wtf(INSTANCE.tag, msg);
                break;
        }
    }
    private void processObject(Object obj) {
        if(obj==null) {
            Logs.d(" its Null");
        }else if (obj instanceof File) {
            File file = (File) obj;
            String path = file.getAbsolutePath();
            String size;
            if (file.exists()) {
                size = " Size " + file.length() + " bytes";
            } else {
                size = " File not exists";
            }
            putMessage(Type.D," Path " + path, size);
        } else if (obj instanceof List) {
            List list= (List) obj;
            int size=list.size();
            putMessage(Type.D,"List Size " + size,"Elements :");
            for(Object o:list){
                putMessage(Type.D, "  " + o.toString());
            }
        }else if (obj instanceof Cursor) {
            Cursor cursor= (Cursor) obj;
            int size= cursor.getCount();
            int column=cursor.getColumnCount();
            putMessage(Type.D,"rows " + size,"columns "+column);
        }else if (obj instanceof TextView) {
            TextView textView= (TextView) obj;
            float x=textView.getX();
            float y=textView.getY();
            float hight=textView.getHeight();
            float width=textView.getWidth();
            CharSequence text=textView.getText();
            putMessage(Type.D," Text : " + text," x :"+x+", y :"+y," width :"+width+", hight :"+hight);
        }else if (obj instanceof ViewGroup) {
            ViewGroup group= (ViewGroup) obj;
            float x=group.getX();
            float y=group.getY();
            float hight=group.getHeight();
            float width=group.getWidth();
            int   childrens=group.getChildCount();
            putMessage(Type.D," Child Count :" + childrens," x :"+x+", y :"+y," width :"+width+", hight :"+hight);
        }else if (obj instanceof View) {
            View view= (View) obj;
            float x=view.getX();
            float y=view.getY();
            float hight=view.getHeight();
            float width=view.getWidth();
            putMessage(Type.D," x :"+x+", y :"+y," width :"+width+", hight :"+hight);
        }else{
            putMessage(Type.D," Class Name : "+obj.getClass().toString()," String value : "+obj.toString()," Hash Code : "+obj.hashCode());
        }

    }

}
