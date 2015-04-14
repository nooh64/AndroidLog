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
    private final String BREAK = "|____________________________________________________________________________________________________________________________";
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

    public static void a(File file) {
        INSTANCE.putHeader(Type.I);
        if (file == null) {
            INSTANCE.printNull();

        } else {
            String path = file.getAbsolutePath();
            String size;
            if (file.exists()) {
                size = " Size " + file.length() + " bytes";
            } else {
                size = " File not exists";
            }
            INSTANCE.putMessage(Type.D, " Path " + path, size);
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(List list) {
        INSTANCE.putHeader(Type.I);
        if (list == null) {
            INSTANCE.printNull();

        } else {
            int size = list.size();
            INSTANCE.putMessage(Type.D, "List Size " + size, "Elements :");
            for (Object o : list) {
                INSTANCE.putMessage(Type.D, "  " + o.toString());
            }
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(Cursor cursor) {
        INSTANCE.putHeader(Type.I);
        if (cursor == null) {
            INSTANCE.printNull();

        } else {
            int size = cursor.getCount();
            int column = cursor.getColumnCount();
            INSTANCE.putMessage(Type.D, "rows " + size, "columns " + column);
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(TextView textView) {
        INSTANCE.putHeader(Type.I);
        if (textView == null) {
            INSTANCE.printNull();

        } else {
            float hight = textView.getHeight();
            float width = textView.getWidth();
            CharSequence text = textView.getText();
            INSTANCE.putMessage(Type.D, " Text : " + text, " width :" + width + ", hight :" + hight);
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(ViewGroup viewGroup) {
        INSTANCE.putHeader(Type.I);
        if (viewGroup == null) {
            INSTANCE.printNull();

        } else {
            float hight = viewGroup.getHeight();
            float width = viewGroup.getWidth();
            int childrens = viewGroup.getChildCount();
            INSTANCE.putMessage(Type.D, " Child Count :" + childrens, " width :" + width + ", hight :" + hight);
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(View view) {
        INSTANCE.putHeader(Type.I);
        if (view == null) {
            INSTANCE.printNull();

        } else {
            float hight = view.getHeight();
            float width = view.getWidth();
            INSTANCE.putMessage(Type.D,  " width :" + width + ", hight :" + hight);
        }
        INSTANCE.putStackTrace(Type.I);
    }
    public static void a(Object obj) {
        INSTANCE.putHeader(Type.I);
        INSTANCE.putMessage(Type.D, " Class Name : " + obj.getClass().toString(), " String value : " + obj.toString(), " Hash Code : " + obj.hashCode());
        INSTANCE.putStackTrace(Type.I);
    }

    public static void i(String... msg) {
        INSTANCE.putHeader(Type.I);
        INSTANCE.putMessage(Type.I, msg);
        INSTANCE.putStackTrace(Type.I);
    }

    public static void v(String... msg) {
        INSTANCE.putHeader(Type.V);
        INSTANCE.putMessage(Type.V, msg);
        INSTANCE.putStackTrace(Type.V);
    }

    public static void w(String... msg) {
        INSTANCE.putHeader(Type.W);
        INSTANCE.putMessage(Type.W, msg);
        INSTANCE.putStackTrace(Type.W);
    }

    public static void wtf(String... msg) {
        INSTANCE.putHeader(Type.WTF);
        INSTANCE.putMessage(Type.WTF, msg);
        INSTANCE.putStackTrace(Type.WTF);
    }

    public static void d(String... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
    }

    public static void d(int... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
    }

    public static void d(long... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
    }

    public static void d(float... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
    }

    public static void d(double... msg) {
        INSTANCE.putHeader(Type.D);
        INSTANCE.putMessage(Type.D, msg);
        INSTANCE.putStackTrace(Type.D);
    }

    public static void e(String msg) {
        INSTANCE.putHeader(Type.E);
        INSTANCE.putMessage(Type.E, msg);
        INSTANCE.putStackTrace(Type.E);
    }


    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void putStackTrace(Type type) {
        if (showHierarchy) {
            log(type, INSTANCE.BREAK);
            log(type, "| Stack Trace :");
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            for (int i = 0; i < INSTANCE.hierarchyLevel; i++) {
                String line = stackTraceElements[i + 4].getMethodName() + "  -> (" + stackTraceElements[i + 4].getFileName() + ":" + stackTraceElements[i + 4].getLineNumber() + ")";
                String space = String.format("%" + (i + 1) * 2 + "s", " ");
                log(type, "|" + space + line);
            }
        }
        if (showHeaderFooter) {
            log(type, INSTANCE.MSG_BREAK);
            log(type, "|");
        }
    }

    private void putHeader(Type type) {
        if (showHeaderFooter) {
            log(type, "|");
            log(type, INSTANCE.MSG_BREAK);
            log(type, "| Message :");
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

    private void printNull() {
        Logs.d(" its Null");
    }



}
