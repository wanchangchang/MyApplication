package com.example.administrator.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterList extends BaseAdapter {
    private Context context;
    private String pam;

    public AdapterList(Context context,String pam) {
        this.context = context;
        this.pam = pam;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.think_type.setText(pam);
        return convertView;
    }

    class ViewHolder {
        private TextView think_type;

        public ViewHolder(View view) {
            think_type=(TextView)view.findViewById(R.id.think_type);
        }
    }
}
