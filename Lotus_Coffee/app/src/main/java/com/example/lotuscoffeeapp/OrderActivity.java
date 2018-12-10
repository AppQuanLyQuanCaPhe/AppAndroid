package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    ListView lvHoaDon;
    Button btnThem,btnThanhToan,btnThemOrder,btnHuy;
    Spinner spinnerLoai,spinnerHuong,spinnerTopping,spinnerDoAn;
    ThucDonAdapter adapter;
    List<ThucDon> hoadon;
    TextView tvBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvHoaDon=(ListView) findViewById(R.id.listViewHoaDon);
        btnThanhToan=(Button) findViewById(R.id.buttonThanhToan);
        btnThem=(Button) findViewById(R.id.buttonThem);
        tvBan=(TextView) findViewById(R.id.textViewBan);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        tvBan.setText(bundle.getString("BAN"));

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

        spinnerLoai=(Spinner) dialog.findViewById(R.id.spinnerLoai);
        spinnerHuong=(Spinner) dialog.findViewById(R.id.spinnerHuong);
        spinnerTopping=(Spinner) dialog.findViewById(R.id.spinnerThem);
        spinnerDoAn=(Spinner) dialog.findViewById(R.id.spinnerDoAn);
        btnThemOrder=(Button) dialog.findViewById(R.id.buttonThemOrder);
        btnHuy=(Button) dialog.findViewById(R.id.buttonHuyOrder);



        dialog.show();
    }

}
