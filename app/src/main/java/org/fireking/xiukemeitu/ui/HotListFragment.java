package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fireking.xiukemeitu.support.utils.BaseFragment;

/**
 * Created by wanggang on 15/5/4.
 */
public class HotListFragment extends BaseFragment {

    private static final String ARG_INDEX = "index";

    private int mParamIndex;

    public static HotListFragment newInstance(int param1) {
        HotListFragment fragment = new HotListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HotListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamIndex = getArguments().getInt(ARG_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
