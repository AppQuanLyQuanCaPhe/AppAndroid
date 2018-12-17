package com.example.lotuscoffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienApdapter extends ArrayAdapter<NhanVien> {


    public NhanVienApdapter(Context context, int resource, List<NhanVien> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View row, ViewGroup parent) {
        View view = row;
        if (view==null) {
            LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.nhanvien_item, null);
        }
        NhanVien nv = super.getItem(position);
        ((TextView) view.findViewById(R.id.tvMaNV)).setText(nv.getMaNV()+"");
        ((TextView) view.findViewById(R.id.tvDiaChi)).setText(nv.getDiaChi());
        ((TextView) view.findViewById(R.id.tvEmail)).setText(nv.getEmail());
        ((TextView) view.findViewById(R.id.tvHoTen)).setText(nv.getHoTen());
        ((TextView) view.findViewById(R.id.tvNgaySinh)).setText(nv.getNgaySinh());
        ((TextView) view.findViewById(R.id.tvSDT)).setText(nv.getSDT());
        return view;
    }
}
