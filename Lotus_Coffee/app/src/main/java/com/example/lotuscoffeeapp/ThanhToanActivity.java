package com.example.lotuscoffeeapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity {

    int MaBan;
    ListView lvHoaDon;
    ThucDonAdapter adapter;
    List<ThucDon> HoaDonList;
    TextView tvban,tvTong;
    Button btnThanhToan,btnHuy;
    SQLiteDatabase database;
    String Ngay;
    TaiKhoan tk;

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

        database=Database.initDatabase(this,MainActivity.DATABASE_NAME);

        HoaDonList=new ArrayList<>();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        MaBan=bundle.getInt("MABAN");
        tvban.setText(MaBan+"");
        HoaDonList= (List<ThucDon>) bundle.getSerializable("HOADON");
        tk= (TaiKhoan) bundle.getSerializable("TAIKHOAN");

        adapter=new ThucDonAdapter(this,R.layout.mon_item,HoaDonList);
        lvHoaDon.setAdapter(adapter);

        Calendar ngay=Calendar.getInstance();
        Ngay=ngay.get(Calendar.DAY_OF_MONTH)+"/"+ngay.get(Calendar.MONTH)+"/"+ngay.get(Calendar.YEAR);
        int tong=0;
        for(int i=0;i<HoaDonList.size();i++){
            tong+=Integer.valueOf(HoaDonList.get(i).getGia());
        }
        tvTong.setText(""+tong+" vnđ");
        final int Tong = tong;
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ThanhToanAtivity","Nhan nut thanh toan");
                AlertDialog.Builder dialog=new AlertDialog.Builder(ThanhToanActivity.this);
                dialog.setTitle("Xác nhận");
                dialog.setMessage("Hóa đơn này đã được thanh toán!!!");
                dialog.setIcon(R.drawable.icons8highpriority48);
                dialog.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("ThanhToanActivity","Xoa du lieu bang TamTinh");
                        database.delete("TamTinh","MaBan=?",new String[]{String.valueOf(MaBan)});
                        Log.d("ThanhToanActivity","Them du lieu bang HoaDon");

                        ContentValues contentValues=new ContentValues();
                        contentValues.put("MaBan",MaBan);
                        contentValues.put("MaNV",tk.getManv());
                        contentValues.put("NgayLap",Ngay);
                        contentValues.put("ThanhTien",Tong);

                        //database.insert("HoaDon",null,contentValues);
                        Toast.makeText(ThanhToanActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();

                        Intent intent1 = new Intent(ThanhToanActivity.this, MainActivity.class);
                        Bundle bundle1=new Bundle();
                        bundle1.putSerializable("TAIKHOAN",tk);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
