package com.example.lotuscoffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<HoaDon> arrayhoadon;

    public HoaDonAdapter(Context context, int layout, List<HoaDon> hoadonlist){
        this.myContext = context;
        this.myLayout = layout;
        this.arrayhoadon = hoadonlist;
    }

    @Override
    public int getCount() {
        return arrayhoadon.size();
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

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout,null);

        TextView txtSoBan = (TextView) convertView.findViewById(R.id.textSoBan);
        txtSoBan.setText(String.valueOf(arrayhoadon.get(position).getMaBan()));

        TextView txtNgay = (TextView) convertView.findViewById(R.id.textNgay);
        txtSoBan.setText(String.valueOf(arrayhoadon.get(position).getNgayLap()));

        TextView txtTien = (TextView) convertView.findViewById(R.id.textTien);
        txtSoBan.setText(String.valueOf(arrayhoadon.get(position).getTien()));

        return null;
    }
}
