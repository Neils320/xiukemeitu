package org.fireking.xiukemeitu.support.utils;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.Map;

/**
 * 网络请求处理
 * Created by wanggang on 15/4/29.
 */
public abstract class SimpleAsyncHttpResponseHandler<T> extends AsyncHttpResponseHandler {

    //请求结果数据回调
    private HttpUtils.DataCallBack<T> callback;

    //是否使用请求缓存
    private boolean isUseRequestCache = false;

    //是否使用对象缓存
    private boolean isUseObjectCache = false;

    /**
     * 设置请求结果数据回调
     *
     * @param callback 回调参数
     */
    public SimpleAsyncHttpResponseHandler(HttpUtils.DataCallBack<T> callback) {
        this.callback = callback;
    }

    /**
     * 数据请求成功处理，自带缓存，用户只需要实现对应的空方法，即可引入对应的缓存
     * @param statusCode 请求状态码
     * @param headers header数据
     * @param responseBody 请求返回主体
     */
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        //缓存请求
        boolean isRequestCache = cacheRequest(getRequestURI().getPath(), new String(responseBody));
        if (isRequestCache) {
            setIsUseRequestCache(true);
        }
        //解析请求到的数据
        T t = parse(new String(responseBody));
        //缓存解析到的对象
        if (t != null) {
            boolean isObjectCache = cacheObject(getRequestURI().getPath(), t);
            if (isObjectCache) {
                setIsUseObjectCache(true);
            }
        }
        callback.result(t);
    }

    /**
     * 请求失败处理
     * @param statusCode 请求失败状态码
     * @param headers headers
     * @param responseBody 消息主体
     * @param error 错误异常信息
     */
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        callback.error(new String(responseBody), statusCode, error);
    }

    /**
     * 解析获取内容的结果
     *
     * @param result 结果字符串
     * @return 处理之后的结果
     */
    public abstract T parse(String result);

    /**
     * 获取参数，用户自己实现
     *
     * @param url url地址
     * @return 用户处理之后的内容
     * <br />get： 将url跟param进行拼接
     * <br />post: 返回requestparam对象
     */
    public Object getParams(String url) {
        return null;
    }

    /**
     * 拼接参数，适用于get请求
     * @param url 请求uri
     * @param params 请求参数
     * @return 拼接参数之后返回结果
     */
    protected String megerParam(String url, Map<String, String> params){
        StringBuffer stringBuffer = new StringBuffer("");
        int count = 0;
        for(Map.Entry<String, String> map : params.entrySet()){
            stringBuffer.append(map.getKey()).append("=").append(map.getValue());
            if(count != params.size() -1){
                stringBuffer.append("&");
            }
        }
        return url + "?" + stringBuffer.toString();
    }

    /**
     * 缓存请求到的数据
     *
     * @param result 请求到的数据
     * @return 是否缓存成功
     */
    public boolean cacheRequest(String uri, String result) {
        return false;
    }

    /**
     * 缓存对象
     *
     * @param t 需要进行缓存的对象
     * @return 是否缓存成功
     */
    public boolean cacheObject(String uri, T t) {
        return false;
    }

    public boolean isUseRequestCache() {
        return isUseRequestCache;
    }

    public void setIsUseRequestCache(boolean isUseRequestCache) {
        this.isUseRequestCache = isUseRequestCache;
    }

    public boolean isUseObjectCache() {
        return isUseObjectCache;
    }

    public void setIsUseObjectCache(boolean isUseObjectCache) {
        this.isUseObjectCache = isUseObjectCache;
    }
}
