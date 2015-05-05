package org.fireking.xiukemeitu.support.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by wanggang on 15/5/5.
 */
public class ViewUtils {

    public int dip2px(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
