package org.fireking.xiukemeitu.support.utils;

import android.content.Context;

/**
 * 网络处理公共类
 * Created by wanggang on 15/4/24.
 */
public class NetUtils {

    /**
     * 网络是否可用
     *
     * @param context 上下文对象
     * @retun true：有可用网络，false：网络异常
     */
    public boolean getNetWork(Context context) {
        return false;
    }

    /**
     * 判断是否为手机网络
     *
     * @return true：手机网络，false：非手机网络
     */
    public boolean isMobile() {
        return false;
    }

    /**
     * 判断是否为wifi网络
     *
     * @return true：wifi网络，false：非wifi网络
     */
    public boolean isWifi() {
        return false;
    }
}
