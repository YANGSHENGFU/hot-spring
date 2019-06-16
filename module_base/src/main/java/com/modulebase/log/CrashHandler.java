package com.modulebase.log;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.modulebase.toolkit.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                collectDeviceInfo(mContext);// 收集设备参数信息
                saveCrashInfo2File(ex);// 保存日志文件
                Looper.loop();
            }
        }.start();

        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName:", versionName);
                infos.put("versionCode:", versionCode);

            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     * @return 返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        String currentTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        printWriter.write("Crash time: " + currentTime + "\n");
        printWriter.write(collectExtraCrashInfo(mContext) + "\n");
        printWriter.write("FATAL EXCEPTION: " + Thread.currentThread().getName() + "\n");
        printWriter.write("Process:" + mContext.getPackageName() + ", PID: " + android.os.Process.myPid() + ", TID: " + android.os.Process.myTid() + "\n");
        ex.printStackTrace(printWriter);//
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String time = formatter.format(new Date());
            String fileName = FileUtils.ROOTDIR+".crash." + time + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = FileUtils.CRASHDIR;
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }


    public static String collectExtraCrashInfo(Context context) {
        if (context == null) {
            return "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OS Version:").append(Build.VERSION.RELEASE).append('_').append(Build.VERSION.SDK_INT).append('\n');
            stringBuilder.append("Model:").append(Build.MODEL).append('\n');
            stringBuilder.append("Product:").append(Build.PRODUCT).append('\n');
            stringBuilder.append("Manufacturer:").append(Build.MANUFACTURER).append('\n');
            stringBuilder.append("CPU:").append(Build.CPU_ABI).append('\n');

            try {
                ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
                if (runningTaskInfos != null && runningTaskInfos.size() > 0) {
                    ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)runningTaskInfos.get(0);
                    stringBuilder.append("activity top task info:\n");
                    stringBuilder.append("task id " + runningTaskInfo.id + "\n");
                    stringBuilder.append("task description " + runningTaskInfo.description + "\n");
                    stringBuilder.append("task numActivities " + runningTaskInfo.numActivities + "\n");
                    stringBuilder.append("task baseActivity " + runningTaskInfo.baseActivity.getClassName() + "\n");
                    stringBuilder.append("task topActivity " + runningTaskInfo.topActivity.getClassName() + "\n");
                }
            } catch (Exception var5) {
                Log.e("AndroidRuntime", "error occured when collect info", var5);
            }

            return stringBuilder.toString();
        }
    }


}
