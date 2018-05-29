package com.ellfors.extools.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关工具类
 * 2018/3/26 15:33
 */
public class ExStringUtil
{
    /**
     * 拆分字符串列表
     */
    public static List<String> getStringList(String str)
    {
        String[] value = str.split(",");
        return Arrays.asList(value);
    }

    /**
     * 先除以100，再返回保留两位小数的String
     */
    public static String getNumberFormat(double price)
    {
        return stripZeros(new DecimalFormat("#0.00").format(price * 0.01));
    }

    /**
     * 先除以100，再返回保留两位小数的String
     */
    public static String getNumberFormat(long price)
    {
        return stripZeros(new DecimalFormat("#0.00").format(price * 0.01));
    }

    /**
     * 先除以100，再返回保留两位小数的String
     */
    public static String getNumberFormat(int price)
    {
        return stripZeros(new DecimalFormat("#0.00").format(price * 0.01));
    }

    /**
     * 先除以100，再返回保留两位小数的String
     */
    public static String getNumberFormat(float price)
    {
        return stripZeros(new DecimalFormat("#0.00").format(price * 0.01));
    }

    /**
     * 去掉后面多余的0
     */
    public static String stripZeros(String value)
    {
//        String str = new BigDecimal(value).stripTrailingZeros().toPlainString();
//        return "0.00".equals(str) ? "0" : str;
        return value;
    }

    /**
     * 整理手机号以 *** **** **** 方式显示
     */
    public static String getIntervalPhone(String str)
    {
        if (TextUtils.isEmpty(str))
            return "";
        if (str.length() < 11)
            return str;
        return str.substring(0, 3) + " " + str.substring(3, 7) + " " + str.substring(7, 11);
    }

    /**
     * 判断是否为email
     */
    public static boolean isEmail(String strEmail)
    {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 判断是否为手机号
     */
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        boolean isValid = false;
        /*
         * 可接受的电话格式有：
         */
        String expression = "^\\(?((1)\\d{2})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
        /*
         * 可接受的电话格式有：
         */
        String expression2 = "^\\(?((1)\\d{2})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);

        Pattern pattern2 = Pattern.compile(expression2);
        Matcher matcher2 = pattern2.matcher(inputStr);
        if (matcher.matches() || matcher2.matches())
        {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断是否为QQ
     */
    public static boolean isQQ(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]{5,10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断字符串是否同事包含字母和数字,6~20位长度
     */
    public static boolean isLetterDigit(String str)
    {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
            {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i)))
            {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        return isDigit && isLetter && str.matches(regex);
    }
}
