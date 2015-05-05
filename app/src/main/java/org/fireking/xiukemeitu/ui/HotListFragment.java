package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.CategoryData;
import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.data.bean.MeinvListBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanggang on 15/5/4.
 */
public class HotListFragment extends BaseFragment {

    private static final String ARG_INDEX = "index";

    private CategoryBean categoryBean;

    private StaggeredGridView mStaggeredGridView;
    private HotListAdapter mHotListAdapter;
    private SimpleHandler handler;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot_list, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mStaggeredGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
        mHotListAdapter = new HotListAdapter(getActivity());
        mStaggeredGridView.setAdapter(mHotListAdapter);
        handler = new SimpleHandler();
        getNetData();
    }

    /**
     * 获取数据
     */
    private void getNetData() {
        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
                CategoryData dao = new CategoryData();
                try {
                    MeinvListBean listBean = dao.getList(categoryBean.getUri());
                    if (listBean != null && listBean.getPageCount() != 0 && listBean.getBeans() != null || listBean.getBeans().size() != 0) {
                        Message msg = handler.obtainMessage(2,  listBean.getBeans());
                        msg.sendToTarget();
                    }else{

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class SimpleHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2:
                    List<ImageBean> bean = (List<ImageBean>) msg.obj;
                    mHotListAdapter.replace(bean);
                    break;
            }
        }
    }

}
