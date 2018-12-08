package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    ListView lvHoaDon;
    Button btnThem,btnThanhToan;
    ThucDonAdapter adapter;
    List<ThucDon> hoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvHoaDon=(ListView) findViewById(R.id.listViewHoaDon);
        btnThanhToan=(Button) findViewById(R.id.buttonThanhToan);
        btnThem=(Button) findViewById(R.id.buttonThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });

    }

    private void DialogThem() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.them_mon);
        dialog.show();
    }

}
