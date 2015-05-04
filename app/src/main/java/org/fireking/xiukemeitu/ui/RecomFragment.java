package org.fireking.xiukemeitu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.support.utils.BaseFragment;

/**
 * Created by wanggang on 15/5/4.
 */
public class RecomFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recom, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
