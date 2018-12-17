package com.example.lotuscoffeeapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NhanvienActivity extends AppCompatActivity {

    SQLiteDatabase database;
    NhanVienApdapter adapter;
    List<NhanVien> NhanVienList;
    ListView lvNhanVien;

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
