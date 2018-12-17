package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

public class MainAdminActivity extends Activity {
    TaiKhoan tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Intent intentLogin=getIntent();
        Bundle bundleLogin=intentLogin.getExtras();
        tk=new TaiKhoan();
        tk= (TaiKhoan) bundleLogin.getSerializable("TAIKHOAN");
        Toast.makeText(this, ""+tk.getTendangnhap()+" "+tk.getMachucvu()+" "+tk.getManv(), Toast.LENGTH_SHORT).show();


    }

}
