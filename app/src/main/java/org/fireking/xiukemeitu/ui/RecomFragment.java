package org.fireking.xiukemeitu.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.RecomData;
import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.ThreadPool;
import org.fireking.xiukemeitu.support.widget.MyMergeAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by wanggang on 15/5/4.
 */
public class RecomFragment extends BaseFragment {

    private ListView mRecomListview;
    private MyMergeAdapter myMergeAdapter;
    private View adBanner;
    private ViewPager slidingPager;//滚图
    private LinearLayout navLinear;//点
    private List<View> views = new ArrayList<>();

    private MyHandler handler;

    private SlidingPagerAdapter mSlidingPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recom, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecomListview = (ListView) getView().findViewById(R.id.recom_listview);
        myMergeAdapter = new MyMergeAdapter();
        initTopBanner();
        //获取网络数据
        handler = new MyHandler();
        getNetData();
    }

    private void initTopBanner() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metics);
        int screenPixels = metics.heightPixels;
        int pixcel = screenPixels / 3;
        adBanner = View.inflate(getActivity(), R.layout.view_sliding_image, null);
        adBanner.getLayoutParams().height = pixcel;
        slidingPager = (ViewPager) adBanner.findViewById(R.id.vPager);
        navLinear = (LinearLayout) adBanner.findViewById(R.id.nav_linear);
    }

    public void getNetData() {

        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
                RecomData data = new RecomData();
                try {
                    List<ImageBean> beans = data.getAd();
                    if (beans == null || beans.size() == 0) {
                        handler.sendEmptyMessage(2);
                    } else {
                        Message msg = handler.obtainMessage(3, beans);
                        msg.sendToTarget();
                    }
                } catch (Exception e) {
                    handler.sendEmptyMessage(4);
                    e.printStackTrace();
                }
            }
        });
    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2://数据为空
                    Toast.makeText(getActivity(), "数据为空!", Toast.LENGTH_SHORT).show();
                    break;
                case 3://ok
                    List<ImageBean> beans = (List<ImageBean>) msg.obj;
                    views.clear();
                    for (int i = 0; i < beans.size(); i++) {
                        View view = View.inflate(getActivity(), R.layout.sliding_image, null);
                        ImageView images = (ImageView) view.findViewById(R.id.image);
                        TextView title = (TextView) view.findViewById(R.id.title);
                        title.setText(beans.get(i).getTitle());
                        Picasso.with(getActivity()).load(beans.get(i).getImage()).placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha).into(images);
                        views.add(view);
                    }
                    mSlidingPagerAdapter = new SlidingPagerAdapter(views, getActivity());
                    slidingPager.setAdapter(mSlidingPagerAdapter);
                    myMergeAdapter.addView(adBanner);
                    break;
                case 4://解析失败
                    Toast.makeText(getActivity(), "解析失败!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}
