package com.example.administrator.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class MyAdapter extends BaseAdapter implements ListAdapter {
    private int select = 0;
    private List<String> data;
    private Context context;

    public MyAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
            holder = new ViewHolder();
//            holder.tv_activite_tip = (TextView) convertView.findViewById(tv_activite_tip);
            holder.text_btn = (TextView) convertView.findViewById(R.id.text_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text_btn.setText(data.get(position));
        if (select == position) {
            holder.text_btn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.text_btn.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
//        holder.tv_bottom_paytype.setText(StringUtil.getString(payWay.getBankname()));

        return convertView;
    }


    class ViewHolder {
        TextView text_btn;
    }

    public void selectOne(int position) {
        select = position;
        notifyDataSetChanged();
    }

    public int getSelect() {
        return select;
    }


}
