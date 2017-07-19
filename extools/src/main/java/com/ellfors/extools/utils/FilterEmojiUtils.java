package com.ellfors.extools.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EditText过滤Emoji表情工具类
 */
public class FilterEmojiUtils
{
    /**
     * 设置EditText不能输入Emoji表情
     *
     * @param editText
     */
    public static void filterEmoji(EditText editText)
    {
        editText.setFilters(new InputFilter[]{emojiFilter});
    }

    /**
     * 过滤Emoji表情
     */
    private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    private static InputFilter emojiFilter = new InputFilter()
    {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find())
            {
                return "";
            }
            return null;
        }
    };
}
