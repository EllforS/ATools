package com.ellfors.extools.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ellfors.extools.R;
import com.ellfors.extools.utils.AppUtil;

public class BaseActivity extends AppCompatActivity
{
    public static Context mContext;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init()
    {
        mContext = this;
        progressDialog = new ProgressDialog(this);
    }

    /**
     * 省去类型转换 将此方法写在基类Activity
     */
    protected <T extends View> T $(int id)
    {
        return (T) super.findViewById(id);
    }

    /**
     * 显示自定义ProgrssDialog
     */
    public void showProgressDialog(String msg)
    {
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    /**
     *  显示默认ProgressDialog
     */
    public void showDefaultProgressDialog()
    {
        progressDialog.setMessage(getResources().getString(R.string.progressdialog_toast));
        progressDialog.show();
    }

    /**
     * 隐藏ProgressDialog
     */
    public void dismissProgressDialog()
    {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 判断ProgressDialog是否为显示状态
     */
    public boolean progressDialogIsShowing()
    {
        if (progressDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打印文本 长时间
     */
    public void showToast(String msg)
    {
        AppUtil.showToast(mContext,msg);
    }

    /**
     * 打印文本 短时间
     */
    public void showToast(int resId)
    {
        AppUtil.showToast(mContext,resId);
    }

    /**
     * 显示视图
     */
    public void showView(View view)
    {
        AppUtil.showView(view);
    }

    /**
     * 隐藏视图
     */
    public void hideViewGone(View view)
    {
        AppUtil.hideViewGone(view);
    }

    /**
     * 隐藏视图 保留位置
     */
    public void hideViewInvisible(View view)
    {
        AppUtil.hideViewInvisible(view);
    }

    /**
     * 判断视图是否显示
     */
    public boolean isShowView(View view)
    {
        return AppUtil.isShowView(view);
    }

    /**
     * 写入SharedPreferences数据
     */
    public void setStringSharedPreferences(String key, String value)
    {
        AppUtil.setStringSharedPreferences(mContext,key,value);
    }

    /**
     * 读取SharedPreferences数据
     */
    public String getStringSharedPreferences(String key, String defaultValue)
    {
        return AppUtil.getStringSharedPreferences(mContext,key,defaultValue);
    }

    /**
     * 判断是否为中文版
     */
    public boolean isZh()
    {
        return AppUtil.isZh(mContext);
    }

    /**
     * 检查是否存在SDCard
     */
    public boolean hasSdcard()
    {
        return AppUtil.hasSdcard();
    }

    /**
     * 验证是否存在可用网络
     */
    public boolean CheckNetworkState()
    {
        return AppUtil.CheckNetworkState(mContext);
    }

    /**
     * 验证网络状态 0 存在wifi网络， 1 存在2/3G网络，2无网络连接
     */
    public int currentNetwork()
    {
        return AppUtil.currentNetwork(mContext);
    }

    /**
     * 获取屏幕高度像素
     */
    public int getDisplayHeight()
    {
        return AppUtil.getDisplayHeight((Activity)mContext);
    }

    /**
     * 获取屏幕宽度像素
     */
    public int getDisplayWidth()
    {
        return AppUtil.getDisplayWidth((Activity)mContext);
    }

}
