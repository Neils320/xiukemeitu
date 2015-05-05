package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.IoUtils;
import org.fireking.xiukemeitu.widget.MyViewPager;
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
    private MyViewPager pager = null;
    //tab适配器
    private HotPagerAdapter mHotPagerAdapter = null;

    //内容错误布局
    private RelativeLayout mErrorsLayout;
    private Button mTryAgain;
    //加载界面
    private RelativeLayout mLoadingLayout;

    private HorizontalScrollView mHorizontalScrollView;
    private RadioGroup mRadioGroup;

    private static final int GET_OK = 1;//获取成功
    private static final int GET_ERROR = 2;//获取失败


    private int paddingLeft = 0;
    private int paddingTop = 0;

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
        pager = (MyViewPager) getView().findViewById(R.id.pager);
        mHorizontalScrollView = (HorizontalScrollView) getView().findViewById(R.id.horizontal);
        mRadioGroup = (RadioGroup) getView().findViewById(R.id.radio_group);
        mErrorsLayout = (RelativeLayout) getView().findViewById(R.id.errors_layout);
        mTryAgain = (Button) getView().findViewById(R.id.try_again);

        paddingLeft = (int) getResources().getDimension(R.dimen.dip_12);
        paddingTop = (int) getResources().getDimension(R.dimen.dip_8);

        mLoadingLayout = (RelativeLayout) getView().findViewById(R.id.loading_layout);

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
        initRadioGroup(beans);
        mHotPagerAdapter = new HotPagerAdapter(beans, getActivity().getSupportFragmentManager());
        pager.setAdapter(mHotPagerAdapter);
    }

    /**
     * 追加滚动内容
     */
    private void initRadioGroup(List<CategoryBean> beans) {
        mRadioGroup.removeAllViews();
        for (int i = 0; i < beans.size(); i++) {
            RadioButton button = (RadioButton) View.inflate(getActivity(), R.layout.raidobutton, null);
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.MATCH_PARENT);
            button.setText(beans.get(i).getTitle());
            mRadioGroup.addView(button, lp);
        }

    }

}
