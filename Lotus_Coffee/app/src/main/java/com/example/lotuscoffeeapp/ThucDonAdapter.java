package com.example.lotuscoffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class ThucDonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThucDon> list;

    public ThucDonAdapter(Context context, int layout, List<ThucDon> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tvTenMon,tvGia;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder.tvTenMon=(TextView) view.findViewById(R.id.textViewTenMon);
            holder.tvGia=(TextView) view.findViewById(R.id.textViewGia);
            holder.imgHinh=(ImageView) view.findViewById(R.id.imageMon);

            view.setTag(holder);
        }else {
            holder =(ViewHolder) view.getTag();
        }
        ThucDon mon=list.get(position);
        holder.tvTenMon.setText(mon.getTenMon());
        holder.tvGia.setText("Giá: "+mon.getGia()+"vnđ");
        holder.imgHinh.setImageResource(mon.getHinhAnh());

        return view;
    }
}
