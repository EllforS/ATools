package com.ellfors.extools.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ellfors.extools.R;
import com.ellfors.extools.utils.ExAppUtils;


public class ExBaseFragment extends Fragment {
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mView = view;
    }

    private void init() {
        mContext = getActivity();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(getResources().getString(R.string.progressdialog_toast));
    }

    /**
     * 省去类型转换 将此方法写在基类Activity
     */
    protected <T extends View> T $(int id)
    {
        return (T) mView.findViewById(id);
    }

    /**
     * 获取ProgressDialog实例
     */
    public ProgressDialog getProgressDialog()
    {
        return mProgressDialog;
    }

    /**
     * 显示自定义ProgrssDialog
     */
    public void showProgressDialog(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 显示默认ProgressDialog
     */
    public void showDefaultProgressDialog() {
        mProgressDialog.setMessage(getResources().getString(R.string.progressdialog_toast));
        mProgressDialog.show();
    }

    /**
     * 隐藏ProgressDialog
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 判断ProgressDialog是否为显示状态
     */
    public boolean progressDialogIsShowing() {
        if (mProgressDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打印文本 长时间
     */
    public void showToast(String msg) {
        ExAppUtils.showToast(mContext, msg);
    }

    /**
     * 打印文本 短时间
     */
    public void showToast(int resId) {
        ExAppUtils.showToast(mContext, resId);
    }

    /**
     * 显示视图
     */
    public void showView(View view) {
        ExAppUtils.showView(view);
    }

    /**
     * 隐藏视图
     */
    public void hideViewGone(View view) {
        ExAppUtils.hideViewGone(view);
    }

    /**
     * 隐藏视图 保留位置
     */
    public void hideViewInvisible(View view) {
        ExAppUtils.hideViewInvisible(view);
    }

    /**
     * 判断视图是否显示
     */
    public boolean isShowView(View view) {
        return ExAppUtils.isShowView(view);
    }

    /**
     * 写入SharedPreferences数据
     */
    public void setStringSharedPreferences(String key, String value) {
        ExAppUtils.setStringSharedPreferences(mContext, key, value);
    }

    /**
     * 读取SharedPreferences数据
     */
    public String getStringSharedPreferences(String key, String defaultValue) {
        return ExAppUtils.getStringSharedPreferences(mContext, key, defaultValue);
    }

    /**
     * 判断是否为中文版
     */
    public boolean isZh() {
        return ExAppUtils.isZh(mContext);
    }

    /**
     * 检查是否存在SDCard
     */
    public boolean hasSdcard() {
        return ExAppUtils.hasSdcard();
    }

    /**
     * 验证是否存在可用网络
     */
    public boolean CheckNetworkState() {
        return ExAppUtils.CheckNetworkState(mContext);
    }

    /**
     * 验证网络状态 0 存在wifi网络， 1 存在2/3G网络，2无网络连接
     */
    public int currentNetwork() {
        return ExAppUtils.currentNetwork(mContext);
    }

    /**
     * 获取屏幕高度像素
     */
    public int getDisplayHeight() {
        return ExAppUtils.getDisplayHeight((Activity) mContext);
    }

    /**
     * 获取屏幕宽度像素
     */
    public int getDisplayWidth() {
        return ExAppUtils.getDisplayWidth((Activity) mContext);
    }

}
