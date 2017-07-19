package com.ellfors.extools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ellfors.extools.R;

/**
 * 加载图片
 */
public class ExImageLoader
{
    /**
     * 加载图片
     */
    public static void loadImg(Context context, String url, ImageView iv)
    {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    /**
     * 加载图片带占位图
     */
    public static void loadImg(Context context, String url, ImageView iv, int placeholder, int error)
    {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.placeholderOf(placeholder == 0 ? R.drawable.default_img : placeholder)
                        .error(error == 0 ? R.drawable.default_img_failed : error))
                .into(iv);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImg(Context context, String url, ImageView iv, int placeholder, int error)
    {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform()
                        .placeholder(placeholder == 0 ? R.drawable.default_img : placeholder)
                        .error(error == 0 ? R.drawable.default_img_failed : error))
                .into(iv);
    }

    /**
     * 加载图片带监听
     */
    public static void loadImgAndListener(Context context, String url, ImageView iv, int placeholder, int error, final RequestListener<Drawable> listener)
    {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.placeholderOf(placeholder == 0 ? R.drawable.default_img : placeholder)
                        .error(error == 0 ? R.drawable.default_img_failed : error))
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                    {
                        listener.onLoadFailed(e, model, target, isFirstResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                    {
                        listener.onResourceReady(resource, model, target, dataSource, isFirstResource);
                        return false;
                    }
                })
                .into(iv);
    }

    /**
     * 获取图片
     */
    public static Bitmap getImg(Context context, String url, int width, int height)
    {
        try
        {
            return Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(RequestOptions.centerCropTransform()
                            .placeholder(R.drawable.default_img)
                            .error(R.drawable.default_img_failed))
                    .into(width, height)
                    .get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
