package com.meituan.tianlong.yuekaomoni.mvp.view.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meituan.tianlong.yuekaomoni.R;

import java.util.List;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MainAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.mainlist,null);
            viewHolder.text01 = convertView.findViewById(R.id.mainlist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text01.setText(list.get(i));
        return convertView;
    }
    class ViewHolder{
        TextView text01;
    }
}
