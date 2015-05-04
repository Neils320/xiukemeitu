package org.fireking.xiukemeitu.support.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共列表内容适配器
 * Created by wanggang on 15/4/24.
 */
public abstract class CommAdapter<T> extends BaseAdapter{

    private List<T> temp = new ArrayList<>();

    /**
     * 对外显示填充内容
     * @return 已经填充到adapter的内容
     */
    public List<T> list(){
        return temp;
    }

    /**
     * 替代当前adapter的内容
     * @param collection 新的填充内容集合
     */
    public void replace(List<T> collection){
        temp = collection;
        notifyDataSetChanged();
    }

    /**
     * 在当前adapter内容的尾部追加内容
     * @param collection 需要追加的新的内容集合
     */
    public void add(List<T> collection){
        temp.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return temp.size();
    }

    @Override
    public Object getItem(int position) {
        return temp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return handlerView(position, convertView, parent);
    }

    /**
     * 真实处理
     * @param positon 当前view位置
     * @param convertView 当前view
     * @param parent 父类容器
     * @return  view对象
     */
    public abstract  View handlerView(int positon, View convertView, ViewGroup parent);
}
