package com.example.lotuscoffeeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {
    EditText edtTenDangNhap,edtMatKhau,edtEmail,edtTenNv,edtSDT,edtReMK,edtNS;
    Button btnDangky;
    SQLiteDatabase database;
    TaiKhoan tk;
    RadioButton rdNV,rdQL;
    RadioGroup rdgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);


        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTenDangNhap=(EditText) findViewById(R.id.editTextTenDangNhap);
        edtMatKhau=(EditText) findViewById(R.id.editTextMatKhau);
        edtReMK=(EditText) findViewById(R.id.editTextNhapLaiMatKhau);
        btnDangky=(Button) findViewById(R.id.buttonDangKy);
        edtTenNv = (EditText) findViewById(R.id.editTenNV);
        edtSDT = (EditText) findViewById(R.id.editSDT);
        rdNV = (RadioButton)findViewById(R.id.rdNhanVien);
        rdQL = (RadioButton)findViewById(R.id.rdQuanLy);
        rdgr = (RadioGroup)findViewById(R.id.rdgr);
        edtNS = (EditText)findViewById(R.id.editNS);

        database=Database.initDatabase(this,MainActivity.DATABASE_NAME);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenNV = edtTenNv.getText().toString();
                String SDT = edtSDT.getText().toString();
                String email = edtEmail.getText().toString();
                String TK = edtTenDangNhap.getText().toString();
                String MK = edtMatKhau.getText().toString();
                String ReMK = edtReMK.getText().toString();
                String NS = edtNS.getText().toString();
                String CV = "";
                String test;
                if(rdNV.isChecked())
                {
                    CV = "NVPV";
                }
                if (rdQL.isChecked()) {
                    CV = "NVQL";
                }

                if(MK.equals(ReMK) && MK != "" && ReMK != "") {
                    SQLiteDatabase db = Database.initDatabase(DangKyActivity.this, MainActivity.DATABASE_NAME);
                    ContentValues valu = new ContentValues();
                    valu.put("TenNV", TenNV);
                    valu.put("SDT", SDT);
                    valu.put("NamSinh", NS);
                    db.insert("NhanVien", null, valu);

                    int ma = -1 ;
                    Cursor cursor = db.rawQuery("select * from  NhanVien where SDT="+SDT,null);


                    while (cursor.moveToNext()) {
                        test = cursor.getString(0);
                        ma = Integer.parseInt(test);
                    }


                    ContentValues va = new ContentValues();
                    va.put("TK",TK);
                    va.put("MK",MK);
                    va.put("MaCV",CV);
                    va.put("MaNV",ma);

                    db.insert("TaiKhoan", null, va);

                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    // chuyển sang trang xem Nhân viên (ghi sau )

                }
                else
                {
                    Toast.makeText(DangKyActivity.this, "Xin hãy nhập lại xác nhận mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
