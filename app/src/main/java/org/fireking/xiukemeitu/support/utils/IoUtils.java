package org.fireking.xiukemeitu.support.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件操作
 * Created by wanggang on 15/4/24.
 */
public class IoUtils {

    /**
     * 从asset目录结果中获取指定文件的字符串格式内容
     * @param context 上下文对象
     * @param filename 文件名称
     * @return 返回的文件内容，如果返回null，则表示获取失败。
     */
    public String getAssetFile(Context context, String filename) {
        try {
            InputStream is = context.getAssets().open(filename);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
            String temp = "";
            StringBuffer stringBuffer = new StringBuffer("");
            byte[] data = new byte[2048];
            while ((temp = bfr.readLine()) != null) {
                stringBuffer.append(temp);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
