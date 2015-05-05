package org.fireking.xiukemeitu.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.fireking.xiukemeitu.R;
import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.support.utils.CommAdapter;

/**
 * Created by Administrator on 2015/5/5.
 */
public class HotListAdapter extends CommAdapter<ImageBean>{

    protected HotListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View handlerView(int positon, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.hot_list_item, null);
            holder.images = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ImageBean bean = (ImageBean) getItem(positon);
        Log.d("info", bean.getImage());
        Picasso.with(mContext).load(bean.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.images);
        holder.title.setText(bean.getTitle());
        return convertView;
    }

    static class ViewHolder{
        public ImageView images;
        public TextView title;
    }
}
