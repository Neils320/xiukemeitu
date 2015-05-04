package org.fireking.xiukemeitu.support.utils;

/**
 * 请求地址配置项
 * Created by wanggang on 15/4/29.
 */
public class URLconfig {

    /**
     * 基础URI地址
     */
    private static final String BASE_URI = "https://api.weibo.com/2/";

    /**
     * 好友模块
     */
    public static final class Friend {

        //好友分组数据请求
        public static final String GROUP_CATEGORY = BASE_URI + "friendships/groups.json";

    }

    /**
     * 获取公共请求地址
     *
     * @return 请求地址
     */
    public static String getBaseUri() {
        return BASE_URI;
    }

}
