package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.IoUtils;
import org.fireking.xiukemeitu.support.widget.PagerSlidingTabStrip;
import org.fireking.xiukemeitu.support.widget.WbViewPager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 美女tab切换，获取对应的所有分类信息
 * Created by wanggang on 15/5/4.
 */
public class HotFragment extends BaseFragment {

    //默认缓存两页的viewpager
    private WbViewPager pager = null;
    //tab适配器
    private HotPagerAdapter mHotPagerAdapter = null;

    //内容错误布局
    private RelativeLayout mErrorsLayout;
    private Button mTryAgain;
    //加载界面
    private RelativeLayout mLoadingLayout;

    private PagerSlidingTabStrip mPagerSlidingTabStrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pager = (WbViewPager) getView().findViewById(R.id.pager);
        mErrorsLayout = (RelativeLayout) getView().findViewById(R.id.errors_layout);
        mTryAgain = (Button) getView().findViewById(R.id.try_again);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) getView().findViewById(R.id.tabs);
        mPagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        mLoadingLayout = (RelativeLayout) getView().findViewById(R.id.loading_layout);
        mPagerSlidingTabStrip.setTextColorResource(R.color.color_fefefe);
        mPagerSlidingTabStrip.setSelectedTextColorResource(android.R.color.white);
        IoUtils utils = new IoUtils();
        String nav = utils.getAssetFile(getActivity(), "nav.json");
        List<CategoryBean> beans = new ArrayList<CategoryBean>();
        try {
            JSONArray array = new JSONArray(nav);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = (JSONObject) array.opt(i);
                beans.add(new CategoryBean(obj.getString("uri"), obj.getString("title")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHotPagerAdapter = new HotPagerAdapter(beans, getChildFragmentManager());

        pager.setAdapter(mHotPagerAdapter);
        pager.setOffscreenPageLimit(1);
        mPagerSlidingTabStrip.setViewPager(pager);
    }

}
