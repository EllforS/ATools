package com.ellfors.extools.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;
import com.orhanobut.logger.Settings;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Logger打印工具类
 * 2018/3/26 15:25
 */
public class ExLoggerUtil
{
    private static String mTag = "default";
    private static Settings settings;

    /**
     * Init Your Log
     *
     * @param tag          //打印的TAG
     * @param methodCount  //打印方法行数
     * @param hideThread   //是否隐藏线程
     * @param methodOffset //偏移量
     */
    public static void init(String tag, int methodCount, boolean hideThread, int methodOffset)
    {
        mTag = tag;
        if (hideThread)
        {
            settings = Logger.init(mTag)
                    .methodCount(methodCount)
                    .methodOffset(methodOffset);
        }
        else
        {
            settings = Logger.init(mTag)
                    .hideThreadInfo()
                    .methodCount(methodCount)
                    .methodOffset(methodOffset);
        }
    }

    /**
     * Close Log
     */
    public static void close()
    {
        settings.logLevel(LogLevel.NONE);
    }

    /**
     * Open Log
     */
    public static void open()
    {
        settings.logLevel(LogLevel.FULL);
    }

    /**
     * Log Json
     *
     * @param str
     */
    public static void json(String str)
    {
        Logger.json(str);
    }

    /**
     * Log Xml
     *
     * @param str
     */
    public static void xml(String str)
    {
        Logger.xml(str);
    }

    /**
     * Log String Verbose
     *
     * @param str
     */
    public static void v(String str)
    {
        Logger.v(str);
    }

    /**
     * Log String Info
     *
     * @param str
     */
    public static void i(String str)
    {
        Logger.i(str);
    }

    /**
     * Log String Warn
     *
     * @param str
     */
    public static void w(String str)
    {
        Logger.w(str);
    }

    /**
     * Log String Debug
     *
     * @param str
     */
    public static void d(String str)
    {
        Logger.d(str);
    }

    /**
     * Log String Error
     *
     * @param str
     */
    public static void e(String str)
    {
        Logger.e(str);
    }

    /**
     * Log List
     *
     * @param list
     */
    public static void list(List list)
    {
        Logger.d(list);
    }

    /**
     * Log Map
     *
     * @param map
     */
    public static void map(Map map)
    {
        Logger.d(map);
    }

    /**
     * Log Set
     *
     * @param set
     */
    public static void set(Set set)
    {
        Logger.d(set);
    }

    /**
     * Log Exception
     *
     * @param e
     * @param message
     */
    public static void exception(Exception e, String message)
    {
        Logger.e(e, message);
    }

    /**
     * Use Your tag
     *
     * @param str
     * @return
     */
    public static Printer t(String str)
    {
        return Logger.t(str);
    }
}
