package com.nooh64.alog;


import android.util.Log;


public class ALog {
    enum Type {D, E, I, V, W, WTF}

    // ===========================================================
    // Constants
    // ===========================================================
    private static final ALog INSTANCE = new ALog();
    private final String BREAK = "+____________________________________________________________________________________________";
    private final String MSG_BREAK = "+============================================================================================";
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
    public static ALog setTag(String tag) {
        INSTANCE.tag = tag;
        return INSTANCE;
    }

    public static ALog setHierarchy(int length) {
        INSTANCE.hierarchyLevel = length;
        return INSTANCE;
    }

    public static ALog showHeaderAndFooter(boolean b) {
        INSTANCE.showHeaderFooter = b;
        return INSTANCE;
    }

    public static ALog showHierarchy(boolean b) {
        INSTANCE.showHierarchy = b;
        return INSTANCE;
    }

    public static void i(String msg) {
        INSTANCE.putHeader(Type.I);
        INSTANCE.putMessage(Type.I, msg);
        INSTANCE.putStackTrace(Type.I);
        INSTANCE.putFooter(Type.I);
    }

    public static void v(String msg) {
        INSTANCE.putHeader(Type.V);
        INSTANCE.putMessage(Type.V, msg);
        INSTANCE.putStackTrace(Type.V);
        INSTANCE.putFooter(Type.V);
    }

    public static void w(String msg) {
        INSTANCE.putHeader(Type.W);
        INSTANCE.putMessage(Type.W, msg);
        INSTANCE.putStackTrace(Type.W);
        INSTANCE.putFooter(Type.W);
    }

    public static void wtf(String msg) {
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

    private void putStackTrace(Type type) {
        if (showHierarchy) {
            log(type, INSTANCE.BREAK);
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            for (int i = 0; i < INSTANCE.hierarchyLevel; i++) {
                String line = stackTraceElements[i + 4].getMethodName() + "  > " + stackTraceElements[i + 4].getFileName() + ":" + stackTraceElements[i + 4].getLineNumber();
                String space = String.format("%" + (i + 1) * 2 + "s", " ");
                switch (type) {
                    case D:
                        Log.d(INSTANCE.tag, "|" + space + line);
                        break;
                    case E:
                        Log.e(INSTANCE.tag, "|" + space + line);
                        break;
                    case I:
                        Log.i(INSTANCE.tag, "|" + space + line);
                        break;
                    case V:
                        Log.v(INSTANCE.tag, "|" + space + line);
                        break;
                    case W:
                        Log.w(INSTANCE.tag, "|" + space + line);
                        break;
                    case WTF:
                        Log.wtf(INSTANCE.tag, "|" + space + line);
                        break;
                }
            }
        }
    }

    private void putHeader(Type type) {
        if (showHeaderFooter) {
            log(type, ".");
            log(type, INSTANCE.MSG_BREAK);
        }
    }

    private void putFooter(Type type) {
        if (showHeaderFooter) {
            log(type, INSTANCE.MSG_BREAK);
            log(type, ".");
        }
    }

    private void putMessage(Type type, String... messages) {
        for (String message : messages)
        log(type, "| " + messages);
    }

    private void putMessage(Type type, int... messages) {
        for (int message : messages)
            log(type, "| " + messages);
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


}
