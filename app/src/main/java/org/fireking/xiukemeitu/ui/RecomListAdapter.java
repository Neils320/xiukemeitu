package org.fireking.xiukemeitu.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.bean.RecomBean;
import org.fireking.xiukemeitu.support.utils.CommAdapter;

import java.util.List;

/**
 * Created by wanggang on 15/5/6.
 */
public class RecomListAdapter extends CommAdapter<RecomBean> {


    public RecomListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View handlerView(int positon, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_recom_list, null);
            holder.cardView1 = (CardView) convertView.findViewById(R.id.card1);
            holder.cardView2 = (CardView) convertView.findViewById(R.id.card2);
            holder.lin1 = (LinearLayout) convertView.findViewById(R.id.lin1);
            holder.lin2 = (LinearLayout) convertView.findViewById(R.id.lin2);
            holder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
            holder.title1 = (TextView) convertView.findViewById(R.id.title1);
            holder.title2 = (TextView) convertView.findViewById(R.id.title2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecomBean recomBean = (RecomBean) getItem(positon);

        return convertView;
    }

    static class ViewHolder {
        public CardView cardView1;
        public CardView cardView2;
        public LinearLayout lin1;
        public LinearLayout lin2;
        public ImageView image1;
        public ImageView image2;
        public TextView title1;
        public TextView title2;
    }
}
