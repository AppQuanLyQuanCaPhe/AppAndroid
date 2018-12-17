package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.content.ContentValues;
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
    List<ThucDon> ThucDonList;
    ArrayList<Integer> ImageID;

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
        //Toast.makeText(this, ""+tk.getTendangnhap()+" "+tk.getMachucvu()+" "+tk.getManv(), Toast.LENGTH_SHORT).show();

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
        CapNhatHinhAnh();
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

    private void CapNhatHinhAnh() {
        GetImageID();
        GetThucDonList();
        for(int i=0;i<ThucDonList.size();i++){
            ContentValues values=new ContentValues();
            values.put("MaMon",ThucDonList.get(i).getMaMon());
            values.put("TenMon",ThucDonList.get(i).getTenMon());
            values.put("Gia",ThucDonList.get(i).getGia());
            values.put("AnhMon",ImageID.get(i));
            values.put("MaLoai",ThucDonList.get(i).getMaLoai());
            database.update("ThucDon",values,"MaMon=?",new String[]{i+1+""});
        }
    }

    private void GetImageID() {
        ImageID=new ArrayList<>();
        ImageID.add(R.drawable.trasua_traxanh);
        ImageID.add(R.drawable.trasua_kiwi);
        ImageID.add(R.drawable.trasua_dau);
        ImageID.add(R.drawable.trasua_truyenthong);
        ImageID.add(R.drawable.matcha_traxanh);
        ImageID.add(R.drawable.matcha_socola);
        ImageID.add(R.drawable.smoothie_daumangcau);
        ImageID.add(R.drawable.smoothie_banana);
        ImageID.add(R.drawable.smoothie_xoai);
        ImageID.add(R.drawable.smoothie_mangcaucaixoan);
        ImageID.add(R.drawable.topping_thachtraicay);
        ImageID.add(R.drawable.topping_tranchau);
        ImageID.add(R.drawable.topping_puddingflan);
        ImageID.add(R.drawable.topping_khucbach);
        ImageID.add(R.drawable.topping_hatthuytinh);
        ImageID.add(R.drawable.topping_thachphomai);
        ImageID.add(R.drawable.khoaitaychien);
        ImageID.add(R.drawable.xucxichchien);
        ImageID.add(R.drawable.cavienchien);
        ImageID.add(R.drawable.xoichienphong);
    }

    private void GetThucDonList(){
        ThucDonList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM ThucDon",null);
        while (cursor.moveToNext()){
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            td.setMaLoai(cursor.getInt(4));
            ThucDonList.add(td);
        }
    }
}
