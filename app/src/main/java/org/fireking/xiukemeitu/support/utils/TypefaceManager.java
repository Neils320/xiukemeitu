package org.fireking.xiukemeitu.support.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.LruCache;


/**
 * 自定义字体使用管理
 * Created by wanggang on 15/4/29.
 */
public class TypefaceManager {

    private static final String TYPE_NAME = "";
    private static final String ROBOTO_REGULAR = "";

    private LruCache<String, Typeface> mCahce;
    private AssetManager assertManager;

    public TypefaceManager(AssetManager assertManager) {
        this.assertManager = assertManager;
        mCahce = new LruCache<String, Typeface>(3);
    }

    /**
     * 返回指定的字体
     *
     * @return 返回的需要字体
     */
    public Typeface getRobotoRegular() {
        return getTypeface(ROBOTO_REGULAR);
    }

    /**
     * 获取字体
     *
     * @param filename 字体名称
     * @return 返回获取的字体对象
     */
    private Typeface getTypeface(String filename) {
        Typeface typeface = mCahce.get(filename);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(assertManager, "fonts/" + filename);
            mCahce.put(filename, typeface);
        }
        return typeface;
    }
}
