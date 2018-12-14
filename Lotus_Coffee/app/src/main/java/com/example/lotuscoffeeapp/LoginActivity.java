package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtTenDangNhap,edtMatKhau,edtEmail;
    TextView tvQuenMatKhau;
    Button btnDangNhap;
    SQLiteDatabase database;
    TaiKhoan tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTenDangNhap=(EditText) findViewById(R.id.editTextTenDangNhap);
        edtMatKhau=(EditText) findViewById(R.id.editTextMatKhau);
        btnDangNhap=(Button) findViewById(R.id.buttonLogin);
        tvQuenMatKhau=(TextView) findViewById(R.id.textViewQuenMK);

        database=Database.initDatabase(this,MainActivity.DATABASE_NAME);

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(edtTenDangNhap.getText().toString().equals("") || edtMatKhau.getText().toString().equals("")){
                if(false){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
                } else{
                    String tendangnhap=edtTenDangNhap.getText().toString();
                    String matkhau=edtMatKhau.getText().toString();
                    /*//Phần chính
                    int kt=KiemTraDangNhap(tendangnhap,matkhau);
                    //Kết thúc*/

                    //Xoa khi release
                    int kt=0;
                    tk=new TaiKhoan();
                    tk.setTendangnhap("NV_1");
                    tk.setMatkhau("hahahha");
                    tk.setManv(4);
                    tk.setMachucvu("NVPV");
                    //Kết thúc
                    Log.d("LoginActivity","KET QUA DANG NHAP: "+kt);
                    switch (kt){
                        case 0:
                            Intent intent=new Intent(LoginActivity.this,MainAdminActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("TAIKHOAN",tk);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                            Bundle bundle1=new Bundle();
                            bundle1.putSerializable("TAIKHOAN",tk);
                            intent1.putExtras(bundle1);
                            startActivity(intent1);
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
                            edtTenDangNhap.setText("");
                            edtMatKhau.setText("");
                            edtTenDangNhap.forceLayout();
                            break;
                    }
                }
            }
        });
    }

    private int KiemTraDangNhap(String tendangnhap, String matkhau) {
        tk=new TaiKhoan();
        Cursor cursor=database.rawQuery("SELECT * FROM TaiKhoan WHERE TK='"+tendangnhap+"' AND MK='"+matkhau+"'",null);
        while (cursor.moveToNext()){
            tk.setTendangnhap(cursor.getString(0));
            tk.setMatkhau(cursor.getString(1));
            tk.setMachucvu(cursor.getString(2));
            tk.setManv(cursor.getInt(3));
        }
        if(tk.getManv()!=0){
            if (tk.getMachucvu().equals("NVQL")) {
                return 0;
            } else return 1;
        } else {
            return 2;
        }

    }
}
