package org.fireking.xiukemeitu.support.utils;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * 网络请求公共处理类
 * Created by wanggang on 15/4/24.
 */
public class HttpUtils {

    final int TIME_OUT = 30 * 1000;

    AsyncHttpClient clitent;

    public HttpUtils() {
        clitent = new AsyncHttpClient();
        clitent.setResponseTimeout(TIME_OUT);
        clitent.setTimeout(TIME_OUT);
        clitent.setConnectTimeout(TIME_OUT);
    }

    /**
     * 指定Get请求操作
     *
     * @param url     请求使用url
     * @param handler 请求结果处理handler
     */
    public void doGet(String url, SimpleAsyncHttpResponseHandler handler) {
        String uri = url;
        if (handler.getParams(url) != null)
            uri = (String) handler.getParams(url);
        clitent.get(uri, handler);
    }


    /**
     * 执行Post请求操作
     *
     * @param url     请求使用的url地址
     * @param params  请求参数
     * @param handler 请求结果处理handler
     */
    public void doPost(String url, Map<String, String> params,
                       SimpleAsyncHttpResponseHandler handler) {
        RequestParams params1 = new RequestParams();
        if (handler.getParams(url) != null) {
            params1 = (RequestParams) handler.getParams(url);
        }
        clitent.post(url, params1, handler);
    }

    /**
     * 进行消息处理
     *
     * @param <T> 处理结果参数
     */
    public interface DataCallBack<T> {
        /**
         * 成功处理
         *
         * @param t 真实处理使用参数
         */
        void result(T t);

        /**
         * 错误结果处理
         *
         * @param error 错误返回消息
         * @param code  服务器返回的错误代码
         */
        void error(String error, int code, Throwable throwable);
    }
}
