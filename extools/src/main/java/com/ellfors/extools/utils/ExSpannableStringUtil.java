package com.ellfors.extools.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;

/**
 * 字体改变工具类
 * 2018/4/8 14:33
 */
public class ExSpannableStringUtil
{
    /**
     * 改变字体颜色
     *
     * @param str   字符串
     * @param color 颜色
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeTextColor(String str, int color, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 改变背景颜色
     *
     * @param str   字符串
     * @param color 颜色
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder chanegBackground(String str, int color, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        BackgroundColorSpan span = new BackgroundColorSpan(color);
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 改变字体大小
     *
     * @param context 上下文
     * @param str     字符串
     * @param size    字体大小（单位dp）
     * @param start   开始位置
     * @param end     结束位置
     */
    public static SpannableStringBuilder changeTextSize(Context context, String str, int size, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(ExDensityUtil.dp2px(context, size));
        builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /**
     * 设置粗体
     *
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeTextBold(String str, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        StyleSpan bold = new StyleSpan(Typeface.BOLD);
        builder.setSpan(bold, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 设置斜体
     *
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeTextItalic(String str, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        StyleSpan italic = new StyleSpan(Typeface.ITALIC);
        builder.setSpan(italic, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 设置粗斜体
     *
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeTextBoldItalic(String str, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        StyleSpan bold_italic = new StyleSpan(Typeface.BOLD_ITALIC);
        builder.setSpan(bold_italic, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 设置上标
     *
     * @param context 上下文
     * @param str     字符串
     * @param topSize 上标字体大小（单位dp），为null则不改变大小
     * @param start   开始位置
     * @param end     结束位置
     */
    public static SpannableStringBuilder changeTopSpan(Context context, String str, Integer topSize, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        SuperscriptSpan span = new SuperscriptSpan();
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //调整字体大小
        if (topSize != null)
        {
            AbsoluteSizeSpan size = new AbsoluteSizeSpan(ExDensityUtil.dp2px(context, topSize));
            builder.setSpan(size, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return builder;
    }

    /**
     * 设置下标
     *
     * @param context    上下文
     * @param str        字符串
     * @param bottomSize 下标字体大小（单位dp），为null则不改变大小
     * @param start      开始位置
     * @param end        结束位置
     */
    public static SpannableStringBuilder changeBottomSpan(Context context, String str, Integer bottomSize, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        SubscriptSpan span = new SubscriptSpan();
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //调整字体大小
        if (bottomSize != null)
        {
            AbsoluteSizeSpan size = new AbsoluteSizeSpan(ExDensityUtil.dp2px(context, bottomSize));
            builder.setSpan(size, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return builder;
    }

    /**
     * 删除线
     *
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeDeleteLine(String str, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        StrikethroughSpan span = new StrikethroughSpan();
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 下滑线
     *
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableStringBuilder changeUnderLine(String str, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        UnderlineSpan span = new UnderlineSpan();
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 插入图片
     *
     * @param context 上下文
     * @param str     字符串
     * @param imgRes  图片ID
     * @param start   开始位置
     * @param end     结束位置
     */
    public static SpannableStringBuilder addTextImage(Context context, String str, int imgRes, int start, int end)
    {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str);
        ImageSpan span = new ImageSpan(context, imgRes);
        builder.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }

}
