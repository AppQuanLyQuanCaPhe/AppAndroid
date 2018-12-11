package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity {

    int MaBan;
    ListView lvHoaDon;
    ThucDonAdapter adapter;
    List<ThucDon> HoaDonList;
    TextView tvban,tvTong;
    Button btnThanhToan,btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvHoaDon=(ListView) findViewById(R.id.listViewThanhToan);
        tvban=(TextView) findViewById(R.id.textViewBanThanhToan);
        tvTong=(TextView) findViewById(R.id.textViewTong);
        btnHuy=(Button) findViewById(R.id.buttonHuy);
        btnThanhToan=(Button) findViewById(R.id.buttonThanhToan);

        HoaDonList=new ArrayList<>();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        MaBan=bundle.getInt("MABAN");
        tvban.setText(MaBan+"");
        HoaDonList= (List<ThucDon>) bundle.getSerializable("HOADON");

        adapter=new ThucDonAdapter(this,R.layout.mon_item,HoaDonList);
        lvHoaDon.setAdapter(adapter);

        int tong=0;
        for(int i=0;i<HoaDonList.size();i++){
            tong+=Integer.valueOf(HoaDonList.get(i).getGia());
        }
        tvTong.setText(""+tong+" vnđ");
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
