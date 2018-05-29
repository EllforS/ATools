package com.ellfors.extools.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellfors.extools.R;
import com.ellfors.extools.popupwindow.manager.CommonPopupWindow;
import com.ellfors.extools.popupwindow.util.PopupWindowViewUtil;
import com.ellfors.extools.utils.ExDensityUtil;


/**
 * 弹出框工具类
 */
public class PopupWindowManager
{
    private CommonPopupWindow popupWindow;

    /**
     * 获取展开状态
     */
    public boolean isShowing()
    {
        return popupWindow != null && popupWindow.isShowing();
    }

    /**
     * 隐藏
     */
    public void dismiss()
    {
        if (popupWindow != null && popupWindow.isShowing())
            popupWindow.dismiss();
    }

    /**
     * 向上弹出
     *
     * @param context  上下文
     * @param view     点击弹出的View
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showUpPop(Context context, View view, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();
        popupWindow.showAsDropDown(view, 0, -(popupWindow.getHeight() + view.getMeasuredHeight()));
    }

    /**
     * 向下弹出
     *
     * @param context  上下文
     * @param view     点击弹出的View
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showDownPop(Context context, View view, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();

        //适配Android 7.0
        if (Build.VERSION.SDK_INT < 24)
        {
            popupWindow.showAsDropDown(view, 0, 0);
        }
        else
        {
            int[] location = new int[2];
            // 获取控件在屏幕的位置
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, y + view.getHeight());
        }

    }

    /**
     * 向左弹出
     *
     * @param context  上下文
     * @param view     点击弹出的View
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showLeftPop(Context context, View view, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
                .setAnimationStyle(R.style.AnimRight)
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();
        popupWindow.showAsDropDown(view, -popupWindow.getWidth(), -view.getHeight());
    }

    /**
     * 向右弹出
     *
     * @param context  上下文
     * @param view     点击弹出的View
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showRightPop(Context context, View view, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimLeft)
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();
        popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());
    }

    /**
     * 全屏弹出
     *
     * @param context  上下文
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showAll(Context context, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        View upView = LayoutInflater.from(context).inflate(layoutID, null);
        //测量View的宽高
        PopupWindowViewUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();
        popupWindow.showAtLocation(((Activity) context).findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }

    /**
     * 提醒弹出
     *
     * @param context  上下文
     * @param view     点击弹出的View
     * @param layoutID 弹出的LayoutID
     * @param listener 弹出的子View的监听事件
     */
    public void showReminder(Context context, View view, int layoutID, boolean touchOutSide, CommonPopupWindow.ViewInterface listener)
    {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(layoutID)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(listener)
                .setOutsideTouchable(touchOutSide)
                .create();
        popupWindow.showAsDropDown(view, (int) (-popupWindow.getWidth() + ExDensityUtil.dp2px(context, 20)), -(popupWindow.getHeight() + view.getMeasuredHeight()));
    }
}
