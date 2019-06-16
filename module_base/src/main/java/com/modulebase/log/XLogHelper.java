package com.modulebase.log;

import android.app.ActivityManager;
import android.content.Context;

import com.modulebase.BuildConfig;
import com.modulebase.toolkit.FileUtils;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

import java.util.List;

public class XLogHelper {

    private static Context mContext ;

    public static void initXlog(Context context){
        mContext = context ;
        System.loadLibrary("stlport_shared");
        System.loadLibrary("marsxlog");
        openXlog();
        Log.setLogImp(new Xlog());
    }

    private static void openXlog(){
        if (BuildConfig.DEBUG) {
            Xlog.open(false , Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, FileUtils.CACHEDIR, FileUtils.XLOGDIR, "Operator"+getProcessName(mContext), "");
            Xlog.setConsoleLogOpen(false);
        } else {
            Xlog.open(false , Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, FileUtils.CACHEDIR, FileUtils.XLOGDIR, "Operator"+getProcessName(mContext), "");
            Xlog.setConsoleLogOpen(false);
        }
    }

    public static void closeXlog(){
        Log.appenderClose();
    }

    /**
     * 获取进程名
     * @param context
     * @return
     */
    public static String getProcessName(Context context) {
        if(context == null){
            return "";
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> rapis = am.getRunningAppProcesses();
        if(rapis == null){
            rapis = am.getRunningAppProcesses();
        }
        if(rapis!=null) {
            for (ActivityManager.RunningAppProcessInfo rapi : rapis) {
                if(rapi.pid == android.os.Process.myPid()){
                    return rapi.processName;
                }
            }
        }
        return null;
    }

}
