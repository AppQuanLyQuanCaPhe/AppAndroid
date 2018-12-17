package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NhanvienActivity extends AppCompatActivity {

    SQLiteDatabase database;
    NhanVienApdapter adapter;
    List<NhanVien> NhanVienList;
    ListView lvNhanVien;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        database=Database.initDatabase(NhanvienActivity.this,MainActivity.DATABASE_NAME);
        lvNhanVien=(ListView) findViewById(R.id.ListViewNhanVien);
        NhanVienList=new ArrayList<>();
        GetDataNhanVien();
        adapter=new NhanVienApdapter(NhanvienActivity.this,android.R.layout.simple_list_item_1,NhanVienList);
        lvNhanVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(NhanvienActivity.this,UpdateNVActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("NHANVIEN",NhanVienList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnThem=(Button) findViewById(R.id.Btn_ThemNV);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhanvienActivity.this,DangKyActivity.class));
            }
        });

    }
    private void GetDataNhanVien(){
        Cursor cursor=database.rawQuery("SELECT * FROM NhanVien",null);

        while (cursor.moveToNext()){
            NhanVien td=new NhanVien();
            td.setMaNV(cursor.getInt(0));
            td.setHoTen(cursor.getString(1));
            td.setSDT(cursor.getString(2));
            td.setNgaySinh(cursor.getString(3));
            td.setEmail(cursor.getString(4));
            td.setDiaChi(cursor.getString(5));
            NhanVienList.add(td);
        }
    }
}
