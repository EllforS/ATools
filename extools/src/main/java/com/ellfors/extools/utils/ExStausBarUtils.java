package com.ellfors.extools.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

/**
 * 沉浸式状态栏
 */

public class ExStausBarUtils
{
    private static SystemBarTintManager tintManager;

    /**
     * @description 设置沉浸式状态栏
     * @author zeminwang
     * @time 16/5/6 13:47
     * @verison v1.0
     */
    @TargetApi(19)
    public static void immerseStatusBar(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintColor(Color.TRANSPARENT);
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    @TargetApi(19)
    public static void immerseStatusBar(Activity activity, int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintColor(color);
            tintManager.setStatusBarTintEnabled(true);
        }
    }
}
