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
//
//    SQLiteDatabase database;
//    NhanVienApdapter adapter;
//    ArrayList<NhanVien> NhanVienList;
//    ListView lvNhanVien;

    SQLiteDatabase database;
    NhanVienApdapter adapter;
    List<NhanVien> NhanVienList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
    }
}
