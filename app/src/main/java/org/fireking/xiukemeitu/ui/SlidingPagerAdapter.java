package org.fireking.xiukemeitu.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wanggang on 15/5/6.
 */
public class SlidingPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<View> beans;

    public SlidingPagerAdapter(List<View> images, Context mContext) {
        this.mContext = mContext;
        this.beans = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(beans.get(position));
        return beans.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(beans.get(position));
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
