package com.ellfors.extools.base;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

public class ExBaseApplication extends Application {

    private static Stack<Activity> stack;

    //双重校验锁
    private volatile static ExBaseApplication instance; //加入volatile防止JVM重排序

    public ExBaseApplication() {

    }

    public static ExBaseApplication getInstance() {

        //第一次检查
        if (instance == null) {
            //加入同步锁，保证线程安全
            synchronized (ExBaseApplication.class) {
                //第二次检查
                if (instance == null) {
                    instance = new ExBaseApplication();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        instance = this;
        stack = new Stack<Activity>();
    }

    /**
     * 添加到堆
     *
     * @param activity
     */
    public void addTask(Activity activity) {
        if (stack == null) {
            stack = new Stack<Activity>();
        }
        stack.add(activity);
    }

    /**
     * 获取当前Activity
     *
     * @return activity
     */
    public Activity currentActivity() {
        Activity activity = stack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = stack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            stack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 判断实例中是否有当前Activity
     *
     * @param cls
     * @return
     */
    public boolean hasActivity(Class<?> cls) {
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = stack.size(); i < size; i++) {
            if (null != stack.get(i)) {
                stack.get(i).finish();
            }
        }
        stack.clear();
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }
}
