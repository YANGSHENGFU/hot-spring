package com.modulebase.log;

import android.text.TextUtils;


public class Log {
    public static boolean DEBUG;
    //是否在release版本显示大于d等级的log,如log.i,log.w,log.e
    private static boolean SHOW_HIGH_PRIORITY;
    private static int INVALID = -1;
    private static String DEFAULTTAG = "Hotspring";
    private static String APPLICATIONID = "com.gzqmh.goldenshark.app";

    static {
        Class configFile = null;
        java.lang.reflect.Field logDebug = null;
        java.lang.reflect.Field showHighPriority = null;
        try {
            configFile = Class.forName(APPLICATIONID + ".BuildConfig");
            logDebug = configFile.getField("LOG_DEBUG");
            logDebug.setAccessible(true);
            DEBUG = logDebug.getBoolean(null);
            showHighPriority = configFile.getField("LOG_SHOW_HIGH_PRIORITY");
            showHighPriority.setAccessible(true);
            SHOW_HIGH_PRIORITY = showHighPriority.getBoolean(null);
        } catch (Exception e) {
            e.printStackTrace();
            DEBUG = false;
            SHOW_HIGH_PRIORITY = true;
        }
    }

    private static String getClassName() {
        try {
            StackTraceElement[] sElements = new Throwable().getStackTrace();
            if (sElements != null && sElements.length >= 4) {
                String className = sElements[3].getFileName().replace(".java", "");
                return className;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DEFAULTTAG;
    }

    private static void debugAllLog(String tag , String msg) {
        // for debug , set to true
        if (false) {
            android.util.Log.d(DEFAULTTAG, tag + ":" + msg);
        }
    }

    private static int E(String tag, String msg) {
        debugAllLog(tag, msg);
        return android.util.Log.e(tag, msg);
    }

    private static int W(String tag, String msg) {
        debugAllLog(tag, msg);
        return android.util.Log.w(tag, msg);
    }

    private static int I(String tag, String msg) {
        debugAllLog(tag, msg);
        return android.util.Log.i(tag, msg);
    }

    private static int D(String tag, String msg) {
        debugAllLog(tag, msg);
        return android.util.Log.d(tag, msg);
    }

    private static int V(String tag, String msg) {
        debugAllLog(tag, msg);
        return android.util.Log.v(tag, msg);
    }


    public static int e(String msg) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return E(getTag(null), getMsg(msg));
        }
        return INVALID;
    }

    public static int w(String msg) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return W(getTag(null), getMsg(msg));
        }
        return INVALID;
    }

    public static int i(String msg) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return I(getTag(null), getMsg(msg));
        }
        return INVALID;
    }

    public static int d(String msg) {
        if (DEBUG) {
            return D(getTag(null), getMsg(msg));
        }
        return INVALID;
    }

    public static int v(String msg) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return V(getTag(null), getMsg(msg));
        }
        return INVALID;
    }


    public static int e() {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return E(getTag(null), getStackTraceString(new Throwable()));
        }
        return INVALID;
    }

    public static int w() {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return W(getTag(null), getStackTraceString(new Throwable()));
        }
        return INVALID;
    }

    public static int i() {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return I(getTag(null), getStackTraceString(new Throwable()));
        }
        return INVALID;
    }

    public static int d() {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return D(getTag(null), getStackTraceString(new Throwable()));
        }
        return INVALID;
    }

    public static int v() {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return V(getTag(null), getStackTraceString(new Throwable()));
        }
        return INVALID;
    }



    public static int e(String tag, String msg, Throwable tr) {
        return E(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int w(String tag, String msg, Throwable tr) {
        return W(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int i(String tag, String msg, Throwable tr) {
        return I(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int d(String tag, String msg, Throwable tr) {
        return D(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }

    public static int v(String tag, String msg, Throwable tr) {
        return V(getTag(tag), msg + '\n' + getStackTraceString(tr));
    }



    public static String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

    public static int w(String tag, Throwable tr) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return android.util.Log.w(tag, tr);
        }
        return INVALID;
    }

    public static int wtf(String tag, String msg) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return android.util.Log.wtf(tag, msg);
        }
        return INVALID;
    }

    public static int wtf(String tag, Throwable tr) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return android.util.Log.wtf(tag, tr);
        }
        return INVALID;
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return android.util.Log.wtf(tag, msg, tr);
        }
        return INVALID;
    }


    public static int e(String tag, String obj) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return E(getTag(tag), getMsg(obj));
        }
        return INVALID;
    }

    public static int w(String tag, String obj) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return V(getTag(tag), getMsg(obj));
        }
        return INVALID;
    }

    public static int i(String tag, String obj) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return V(getTag(tag), getMsg(obj));
        }
        return INVALID;
    }

    public static int d(String tag, String obj) {
        if (DEBUG ) {
            return V(getTag(tag), getMsg(obj));
        }
        return INVALID;
    }

    public static int v(String tag, String obj) {
        if (DEBUG || SHOW_HIGH_PRIORITY) {
            return V(getTag(tag), getMsg(obj));
        }
        return INVALID;
    }



    private static String getTag(String tag) {
        String logTag = null;
        if (tag != null) {
            if (tag instanceof String) {
                logTag = (String) tag;
            } else {
                logTag = tag.getClass().getSimpleName();
            }
        }
        if (TextUtils.isEmpty(logTag)) {
            logTag = getClassName();
        }
        return logTag;
    }

    private static String getMsg(String msg) {
        if (DEBUG) {
            msg = appendLogLoaction(msg);
        }
        return msg;
    }

    private static String appendLogLoaction(String msg) {
        StackTraceElement[] sElements = Thread.currentThread().getStackTrace();
        if (sElements != null && sElements.length >= 7) {
            int index = 6;
            String className = sElements[index].getFileName();
            //处理LogF调用
            if (!TextUtils.isEmpty(className) && className.equals("LogF.java")) {
                if (sElements.length >= 8) {
                    index = 7;
                    className = sElements[index].getFileName();
                } else {
                    return msg;
                }
            }
            String methodName = sElements[index].getMethodName();
            int lineNumber = sElements[index].getLineNumber();
            String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodNameShort).append(" ] ");
            if (!TextUtils.isEmpty(msg)) {
                stringBuilder.append(msg);
            }
            return stringBuilder.toString();
        }
        return msg;
    }

}

