package com.hotspr.app;

import android.app.Application;

import com.modulebase.log.CrashHandler;
import com.modulebase.log.XLogHelper;
import com.sunmi.utils.AidlUtil;

public class HotSpringApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
        AidlUtil.getInstance().connectPrinterService(this);
        //XLogHelper.initXlog(getApplicationContext());
    }
}
