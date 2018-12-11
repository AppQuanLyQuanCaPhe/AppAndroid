package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME="QuanLyQuanCafe.sqlite";
    GridView grdvBan;
    List<BAN> BanList;
    GridViewBanAdapter adapter;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database=Database.initDatabase(this,DATABASE_NAME);

        grdvBan=(GridView) findViewById(R.id.gridViewBan);
        BanList=new ArrayList<>();
        getdataBan();
        getTrangThai();
        adapter=new GridViewBanAdapter(this,BanList);
        grdvBan.setAdapter(adapter);

        grdvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("MABAN",position+1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Log.d("MainActivity","===ONCREATE===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getTrangThai();
        adapter.notifyDataSetChanged();
        Log.d("MainActivity","===ONRESTART===");
    }

    private void getTrangThai() {
        Cursor cursorTT = database.rawQuery("select MaBan from TamTinh group by MaBan", null);
        while (cursorTT.moveToNext()) {
            for (int i=0;i<BanList.size();i++) {
                if (cursorTT.getInt(0) == BanList.get(i).getMaBan()) {
                    BanList.get(i).setTrangThai(1);
                }
            }
        }
        cursorTT.close();
    }

    private void getdataBan() {
        Cursor cursor=database.rawQuery("Select * from Ban",null);
        while (cursor.moveToNext()){
            BAN ban=new BAN();
            ban.setMaBan(cursor.getInt(0));
            ban.setTenBan("Bàn "+ban.getMaBan());
            BanList.add(ban);
        }
        cursor.close();
    }

}
