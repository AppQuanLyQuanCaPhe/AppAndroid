package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
    TaiKhoan tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database=Database.initDatabase(this,DATABASE_NAME);

        Intent intentLogin=getIntent();
        Bundle bundleLogin=intentLogin.getExtras();
        tk=new TaiKhoan();
        tk= (TaiKhoan) bundleLogin.getSerializable("TAIKHOAN");
        Toast.makeText(this, ""+tk.getTendangnhap()+" "+tk.getMachucvu()+" "+tk.getManv(), Toast.LENGTH_SHORT).show();

        grdvBan=(GridView) findViewById(R.id.gridViewBan);

        grdvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("MABAN",position+1);
                bundle.putSerializable("TAIKHOAN",tk);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Log.d("MainActivity","===ONCREATE===");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BanList=new ArrayList<>();
        getdataBan();
        getTrangThai();
        adapter=new GridViewBanAdapter(this,BanList);
        grdvBan.setAdapter(adapter);
        Log.d("MainActivity","===ONSTART===");
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.actions_logout:
                finish();
                break;
            case R.id.actions_DoiMatKhau:
                Toast.makeText(this, "Tính năng trong thời gian phát triển!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actions_ThongTin:
                DialogThongTin();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThongTin() {
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.thong_tin_layout);
        dialog.show();
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
