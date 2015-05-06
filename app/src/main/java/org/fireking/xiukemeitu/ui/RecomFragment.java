package org.fireking.xiukemeitu.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.RecomData;
import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.data.bean.RecomBean;
import org.fireking.xiukemeitu.support.utils.BaseFragment;
import org.fireking.xiukemeitu.support.utils.ThreadPool;
import org.fireking.xiukemeitu.support.widget.MyMergeAdapter;

import java.io.IOException;
import java.util.List;

/**
 * Created by wanggang on 15/5/4.
 */
public class RecomFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener {

    private ListView mRecomListview;
    private MyMergeAdapter myMergeAdapter;

    private MyHandler handler;

    private  RecomData data;

    private SliderLayout mSliderLayout;
    private RelativeLayout mSliderParentPanel;
    private PagerIndicator mPagerIndicator;

    private RecomListAdapter mRecomListAdapter;

    public RecomFragment(){
        data = new RecomData();
    }

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
        handler = new MyHandler();
        getNetData();
    }

    private void initTopBanner() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metics);
        int screenPixels = metics.heightPixels;
        int pixcel = screenPixels / 3;
        mSliderParentPanel = (RelativeLayout) View.inflate(getActivity(), R.layout.view_sliding_image, null);
        mSliderParentPanel.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, pixcel));
        mSliderLayout = (SliderLayout) mSliderParentPanel.findViewById(R.id.slider);
        mPagerIndicator = (PagerIndicator) mSliderParentPanel.findViewById(R.id.custom_indicator);
        mRecomListAdapter = new RecomListAdapter(getActivity());
        myMergeAdapter.addView(mSliderParentPanel);
        myMergeAdapter.addAdapter(mRecomListAdapter);
        mRecomListview.setAdapter(myMergeAdapter);
    }

    public void getNetData() {

        //获取广告数据
        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
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


        //获取列表数据
        ThreadPool.newInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<RecomBean> recomBeans = data.getRecomList();
                    if(recomBeans == null || recomBeans.size() == 0){
                        handler.sendEmptyMessage(5);//获取列表数据为空了
                    }else{
                        Message msg= handler.obtainMessage(6, recomBeans);
                        msg.sendToTarget();
                    }
                } catch (IOException e) {
                    handler.sendEmptyMessage(7);//解析出错了,json网络请求被拒绝
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        ImageBean bean = (ImageBean) baseSliderView.getBundle().getSerializable("bean");
    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2://
                    Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case 3://ok
                    List<ImageBean> beans = (List<ImageBean>) msg.obj;
                    for (int i = 0; i < beans.size(); i++) {
                        ImageBean bean = beans.get(i);
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        textSliderView.description(bean.getTitle()).image(bean.getImage()).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(RecomFragment.this);

                        if (textSliderView.getBundle() != null) {
                            textSliderView.getBundle().putSerializable("bean", bean);
                        }
                        mSliderLayout.addSlider(textSliderView);
                    }
                    mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
                    mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                    mSliderLayout.setDuration(4000);
                    myMergeAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    List<RecomBean> recomBeans = (List<RecomBean>) msg.obj;
                    mRecomListAdapter.replace(recomBeans);
                    myMergeAdapter.notifyDataSetChanged();
                    break;
                case 7:
                    Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}
