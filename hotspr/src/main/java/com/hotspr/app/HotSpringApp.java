package com.hotspr.app;

import android.app.Application;

import com.modulebase.log.CrashHandler;
import com.modulebase.log.XLogHelper;

public class HotSpringApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());


//        XLogHelper.initXlog(getApplicationContext());
    }
}
