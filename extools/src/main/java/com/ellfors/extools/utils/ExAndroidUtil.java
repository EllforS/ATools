package com.ellfors.extools.utils;

/**
 * AndroidUtil
 * 2018/3/27 10:06
 */

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

/**
 * 获取系统信息的工具类
 */
public class ExAndroidUtil
{
    private static final String TAG = "AndroidUtil";
    private static String DevicesId = "";
    private static String IMEI = "";
    private static String IMSI = "";
    private static String MAC_ADDRESS = "";
    private static String BUILD = "";
    private static final String uuidKey = "uuidKey";

    /**
     * 获取当前版本名
     */
    public static String getAppVersionName(Context context)
    {
        String VERSION_NAME = "";
        try
        {
            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            VERSION_NAME = packageInfo.versionName;

            if (TextUtils.isEmpty(VERSION_NAME))
            {
                return "";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return VERSION_NAME;
    }

    /**
     * 获取当前版本号
     */
    public static int getAppVersionCode(Context context)
    {
        int VERSION_CODE = 0;
        try
        {
            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            VERSION_CODE = packageInfo.versionCode;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return VERSION_CODE;
    }

    /**
     * 启动应用，传递需要启动的包名
     */
    public static boolean launchApp(Context context, String pkgName)
    {
        if (pkgName.equals(context.getPackageName()))
        {
            return true;
        }
        try
        {
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(pkgName);
            if (launchIntent != null)
            {
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(pkgName));
                // 添加启动游戏任务完成
                return true;
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    /**
     * 获取当前进程占用内存
     */
    public static float getProcessMemoryInfo(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int pid = android.os.Process.myPid();
        if (activityManager != null)
        {
            android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(new int[]{pid});
            return (float) memoryInfoArray[0].getTotalPrivateDirty() / 1024;
        }
        else
        {
            return 0;
        }
    }

    /**
     * 修复小米MI2的JarFile没有实现Closeable导致崩溃问题
     */
    public static void closeCloseable(Closeable obj)
    {
        try
        {
            if (obj != null)
                obj.close();
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage());
        }
    }


    /**
     * 获取设备唯一标识
     */
    @SuppressLint("HardwareIds")
    public static String getUniqueId(Context context)
    {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        try
        {
            return ExMD5Util.md5(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return id;
        }
    }

    /**
     * apk是否是debug版本
     */
    public static boolean isApkDebugable(Context context)
    {
        try
        {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取ApiKey
     */
    public static String getMetaValue(Context context, String metaKey)
    {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null)
        {
            return null;
        }
        try
        {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
            {
                metaData = ai.metaData;
            }
            if (null != metaData)
            {
                apiKey = metaData.getString(metaKey);
            }
        }
        catch (NameNotFoundException e)
        {
            Log.e(TAG, e.getMessage());
        }
        return apiKey;
    }
}