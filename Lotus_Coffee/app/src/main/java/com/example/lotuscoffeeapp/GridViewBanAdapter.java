package com.example.lotuscoffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class GridViewBanAdapter extends BaseAdapter {
    private Context context;
    private List<BAN> ban;

    public GridViewBanAdapter(Context context, List ban) {
        this.context = context;
        this.ban = ban;
    }

    @Override
    public int getCount() {
        return ban.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.gridview_item,null);
        TextView tvBan=convertView.findViewById(R.id.textViewBan);
        ImageView img=convertView.findViewById(R.id.imageView);

        tvBan.setText(ban.get(position).getTenBan());
        if(ban.get(position).getTrangThai()==0){
            img.setImageResource(R.drawable.ban);
        } else img.setImageResource(R.drawable.ban2);
        return convertView;
    }
}
