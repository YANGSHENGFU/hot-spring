package com.modulebase.log;

import com.modulebase.BuildConfig;

public class LogF {

    private static boolean LOG_WRITE_CONRTRO ;
    private static boolean DEBUG ;

    static {
        DEBUG = BuildConfig.DEBUG ;
    }


    public static int v(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        com.tencent.mars.xlog.Log.v(tag,msg);
        return Log.v(tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (msg == null) {
            msg = "null";
        }
        com.tencent.mars.xlog.Log.v(tag,msg);
        return Log.v(tag, msg, tr);
    }


    public static int d(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG){
            com.tencent.mars.xlog.Log.d(tag,msg);
        }
        return Log.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG){
            com.tencent.mars.xlog.Log.d(tag,msg,tr);
        }
        return Log.d(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG || LOG_WRITE_CONRTRO){
            com.tencent.mars.xlog.Log.i(tag,msg);
        }
        return Log.i(tag, msg);
    }


    public static int w(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG || LOG_WRITE_CONRTRO){
            com.tencent.mars.xlog.Log.w(tag,msg);
        }
        return Log.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG || LOG_WRITE_CONRTRO){
            com.tencent.mars.xlog.Log.w(tag,msg,tr);
        }
        return Log.w(tag, msg, tr);
    }

    public static int e(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG || LOG_WRITE_CONRTRO){
            com.tencent.mars.xlog.Log.e(tag,msg);
        }
        return Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (msg == null) {
            msg = "null";
        }
        if(DEBUG || LOG_WRITE_CONRTRO){
            com.tencent.mars.xlog.Log.e(tag,msg,tr);
        }
        return Log.e(tag, msg, tr);
    }

}
