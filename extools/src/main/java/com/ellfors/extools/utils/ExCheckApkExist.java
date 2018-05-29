package com.ellfors.extools.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 根据包名检测手机是否安装某个APK
 * 2018/3/29 18:13
 */
public class ExCheckApkExist
{
    /**
     * 微信
     */
    public static final String WEIXIN_PACKAGE = "com.tencent.mm";
    /**
     * QQ
     */
    public static final String QQ_PACKAGE = "com.tencent.mobileqq";

    /**
     * 检测手机是否安装某个程序
     */
    public static boolean checkApkExist(Context context, String packageName)
    {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null)
        {
            for (int i = 0; i < pinfo.size(); i++)
            {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
