package com.example.lotuscoffeeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNVActivity extends Activity {

    TextView tvID,tvHoTen;
    EditText edtSDT,edtAdrress,edtEmail,edtNamSinh;
    Button btnLuu;
    SQLiteDatabase database;
    NhanVien nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nv);

        tvID=(TextView) findViewById(R.id.textViewIdNV);
        tvHoTen=(TextView) findViewById(R.id.textViewTenNV);
        edtSDT=(EditText) findViewById(R.id.editSDT);
        edtNamSinh=(EditText) findViewById(R.id.editNS);
        edtEmail=(EditText) findViewById(R.id.editEmail);
        edtAdrress=(EditText) findViewById(R.id.editAddress);
        btnLuu=(Button) findViewById(R.id.buttonLuu);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        nv=new NhanVien();
        nv= (NhanVien) bundle.getSerializable("NHANVIEN");

        tvID.setText(nv.getMaNV()+"");
        tvHoTen.setText(nv.getHoTen());
        edtSDT.setText(nv.getSDT());
        edtNamSinh.setText(nv.getNgaySinh());
        edtEmail.setText(nv.getEmail());
        edtAdrress.setText(nv.getDiaChi());

        database=Database.initDatabase(UpdateNVActivity.this,MainActivity.DATABASE_NAME);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSDT.getText().toString().equals("")){
                    Toast.makeText(UpdateNVActivity.this, "Vui lòng nhập sô điện thoại", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("TenNV", nv.getHoTen());
                    values.put("SDT", edtSDT.getText().toString());
                    values.put("NamSinh", edtNamSinh.getText().toString());
                    values.put("Email", edtEmail.getText().toString());
                    values.put("DiaChi", edtAdrress.getText().toString());

                    database.update("NhanVien",values,"MaNV=?",new String[]{nv.getMaNV()+""});
                    Log.d("UpdateNVActivity","=====Cap nhat Database");
                    startActivity(new Intent(UpdateNVActivity.this,NhanvienActivity.class));
                }
            }
        });
    }
}
