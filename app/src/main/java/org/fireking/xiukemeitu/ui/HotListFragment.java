package org.fireking.xiukemeitu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.CategoryData;
import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.fireking.xiukemeitu.data.bean.MeinvListBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.ThreadPool;

/**
 * Created by wanggang on 15/5/4.
 */
public class HotListFragment extends BaseFragment {

    private static final String ARG_INDEX = "index";

    private CategoryBean categoryBean;

    private TextView position;

    public static HotListFragment newInstance(CategoryBean param1) {
        HotListFragment fragment = new HotListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_INDEX, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HotListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryBean = (CategoryBean) getArguments().getSerializable(ARG_INDEX);
        }
        Log.d("info", "获取到的信息 :" + categoryBean.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot_list, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        position = (TextView) getView().findViewById(R.id.position);
        position.setText(categoryBean.getTitle());
        getNetData();
    }

    /**
     * 获取数据
     */
    private void getNetData() {

        Log.d("info", categoryBean.getTitle());
        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
                CategoryData dao = new CategoryData();
                try {
                    MeinvListBean listBean = dao.getList(categoryBean.getUri());
                    Log.e("info", listBean.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
