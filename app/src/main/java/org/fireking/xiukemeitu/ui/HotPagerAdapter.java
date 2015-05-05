package org.fireking.xiukemeitu.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import org.fireking.xiukemeitu.data.bean.CategoryBean;

import java.util.List;

/**
 * Created by wanggang on 15/5/4.
 */
public class HotPagerAdapter extends FragmentStatePagerAdapter {

    private List<CategoryBean> categoryBeans;

    public HotPagerAdapter(List<CategoryBean> categoryBeans, FragmentManager fm) {
        super(fm);
        this.categoryBeans = categoryBeans;
    }

    @Override
    public Fragment getItem(int position) {
        return HotListFragment.newInstance(categoryBeans.get(position));
    }

    @Override
    public int getCount() {
        return categoryBeans.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryBeans.get(position).getTitle();
    }
}