package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {

    ListView lvHoaDon;
    Button btnRedo;
    SQLiteDatabase database;
    List<String> arrayHoaDon;
    TaiKhoan tk;
    String test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        btnRedo=(Button) findViewById(R.id.buttonQuayLai);

        lvHoaDon = (ListView)findViewById(R.id.listViewHoaDon);
        SQLiteDatabase db = Database.initDatabase(HoaDonActivity.this, MainActivity.DATABASE_NAME);
        Cursor cursor = db.rawQuery("select * from  HoaDon ",null);


        arrayHoaDon = new ArrayList<>();
        while (cursor.moveToNext()) {
            test ="bàn:"+cursor.getString(1)+"| Ngày:"+cursor.getString(2)+" | Tiền:"+cursor.getString(3);
            arrayHoaDon.add(test);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaDonActivity.this,android.R.layout.simple_list_item_1,arrayHoaDon);

        lvHoaDon.setAdapter(adapter);

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            Intent intent=new Intent(HoaDonActivity.this,MainAdminActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("TAIKHOAN",tk);
            intent.putExtras(bundle);
            startActivity(intent);

           }
        });

    }

}
