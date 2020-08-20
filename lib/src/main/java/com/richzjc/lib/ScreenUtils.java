package com.richzjc.lib;

import android.content.Context;

/**
 * Created by zhangyang on 16/7/6.
 */
public class ScreenUtils {

    public static int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(dpValue * scale + 0.5f);
    }
}
