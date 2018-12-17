package com.example.lotuscoffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienApdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NhanVien> list;
    private int layout;
    public NhanVienApdapter(Context context,int layout,ArrayList<NhanVien> list){
        this.context=context;
        this.layout=layout;
        this.list=list;
    }
    @Override
    public int getCount() {
        return 0;
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
        TextView tvMaNV,tvHoTen,tvNgaySinh,tvDiaChi,tvSDT,tvEmail;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        View view = row;
        if (view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.nhanvien_item, null);
        }
        NhanVien nv = list.get(position);
        ((TextView) view.findViewById(R.id.tvMaNV)).setText("");
        ((TextView) view.findViewById(R.id.tvDiaChi)).setText("");
        ((TextView) view.findViewById(R.id.tvEmail)).setText("");
        ((TextView) view.findViewById(R.id.tvHoTen)).setText("");
        ((TextView) view.findViewById(R.id.tvNgaySinh)).setText("");
        ((TextView) view.findViewById(R.id.tvSDT)).setText("");
        return view;
    }
}
