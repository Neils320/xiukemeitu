package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.TypeData;
import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.ThreadPool;
import org.fireking.xiukemeitu.widget.MyViewPager;

import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


/**
 * 美女tab切换，获取对应的所有分类信息
 * Created by wanggang on 15/5/4.
 */
public class HotFragment extends BaseFragment implements MaterialTabListener {

    //5.0风格的tabhost
    private MaterialTabHost tabhost = null;
    //默认缓存两页的viewpager
    private MyViewPager pager = null;
    //tab适配器
    private HotPagerAdapter mHotPagerAdapter = null;

    private MyHandler handler = null;

    private static final int GET_OK = 1;//获取成功
    private static final int GET_ERROR = 2;//获取失败

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabhost = (MaterialTabHost) getView().findViewById(R.id.materialTabHost);
        pager = (MyViewPager) getView().findViewById(R.id.pager);
        handler = new MyHandler();
        getMeinvCategory();
    }

    MyViewPager.OnPageChangeListener pageChangeListener = new MyViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            tabhost.setSelectedNavigationItem(position);
        }
    };

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    /**
     * 获取美女图片分类
     */
    public void getMeinvCategory() {

        //开线程获取网络内容
        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                TypeData dao = new TypeData();
                try {
                    List<CategoryBean> categorys = dao.getTypes();
                    msg.what = GET_OK;
                    msg.obj = categorys;
                    msg.sendToTarget();
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = GET_ERROR;
                    msg.sendToTarget();
                }
            }
        });
    }


    /**
     * 信息消息处理
     */
    public class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_OK:
                    List<CategoryBean> beans = (List<CategoryBean>) msg.obj;
                    if (beans.isEmpty()) return;
                    mHotPagerAdapter = new HotPagerAdapter(beans, getActivity().getSupportFragmentManager());
                    pager.setAdapter(mHotPagerAdapter);
                    pager.setOnPageChangeListener(pageChangeListener);
                    //insert all tabs to adapter
                    for (int i = 0; i < mHotPagerAdapter.getCount(); i++) {
                        tabhost.addTab(
                                tabhost.newTab().setText(mHotPagerAdapter.getPageTitle(i)).setTabListener(HotFragment.this)
                        );
                    }
                    break;
                case GET_ERROR:

                    break;
            }
        }
    }
}
