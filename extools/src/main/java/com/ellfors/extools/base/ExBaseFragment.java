package com.ellfors.extools.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ellfors.extools.R;


public class ExBaseFragment extends Fragment
{
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private View mView;
    private Toast mToast;
    private int mToastY = 0;// toast默认显示高度

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mView = view;
    }

    private void init()
    {
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
    public void showProgressDialog(String msg)
    {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 显示默认ProgressDialog
     */
    public void showDefaultProgressDialog()
    {
        mProgressDialog.setMessage(getResources().getString(R.string.progressdialog_toast));
        mProgressDialog.show();
    }

    /**
     * 隐藏ProgressDialog
     */
    public void dismissProgressDialog()
    {
        if (mProgressDialog != null)
        {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 判断ProgressDialog是否为显示状态
     */
    public boolean progressDialogIsShowing()
    {
        return mProgressDialog.isShowing();
    }

    /**
     * toast 文字
     */
    public void showToast(int textResId)
    {
        showToast(getString(textResId), 0);
    }

    /**
     * toast 文字带图片
     */
    public void showToast(int textResId, int iconResId)
    {
        showToast(getString(textResId), iconResId);
    }

    /**
     * toast 文字
     */
    public void showToast(String text)
    {
        showToast(text, 0);
    }

    /**
     * toast 文字带图片
     */
    @SuppressLint("ShowToast")
    public void showToast(String text, int iconResId)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }
        TextView tv;
        if (mToast == null)
        {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
            LayoutInflater inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            tv = (TextView) inflate.inflate(R.layout.dialog_toast, null);
            mToast.setView(tv);
            mToastY = mToast.getYOffset();
        }
        tv = (TextView) mToast.getView();
        tv.setText(text);
        Drawable icon = null;
        if (iconResId > 0)
        {
            try
            {
                icon = getResources().getDrawable(iconResId);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            mToast.setGravity(Gravity.BOTTOM, 0, mToastY);
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
        mToast.show();
    }

    /**
     * 取消toast
     */
    public void cancelToast()
    {
        if (mToast != null)
            mToast.cancel();
    }
}
