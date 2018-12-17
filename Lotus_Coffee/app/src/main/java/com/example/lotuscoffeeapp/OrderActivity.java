package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.content.ContentValues;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderActivity extends AppCompatActivity {

    int MaBan,TongHoaDonDau;
    ListView lvHoaDon;
    Button btnThem,btnThanhToan,btnThemOrder,btnHuy;
    Spinner spinnerLoai,spinnerHuong,spinnerTopping,spinnerDoAn;
    ThucDonAdapter adapter;
    TextView tvBan;
    SQLiteDatabase database;
    ArrayList<String> LoaiList,MonList,ToppingList,DoAnList;
    List<ThucDon> ThucdonList,HoaDonList;
    TaiKhoan tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database=Database.initDatabase(OrderActivity.this,MainActivity.DATABASE_NAME);

        lvHoaDon=(ListView) findViewById(R.id.listViewHoaDon);
        btnThanhToan=(Button) findViewById(R.id.buttonThanhToan);
        btnThem=(Button) findViewById(R.id.buttonThem);
        tvBan=(TextView) findViewById(R.id.textViewBan);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        MaBan=bundle.getInt("MABAN");
        tk= (TaiKhoan) bundle.getSerializable("TAIKHOAN");
        tvBan.setText(MaBan+"");

        ThucdonList=new ArrayList<>();
        HoaDonList=new ArrayList<>();
        //getFullHoaDon();
        getDataHoaDon(MaBan);
        TongHoaDonDau=HoaDonList.size();
        Log.d("OrderActivity","TongHoaDon:"+TongHoaDonDau);
        adapter=new ThucDonAdapter(OrderActivity.this,R.layout.mon_item,HoaDonList);
        lvHoaDon.setAdapter(adapter);

        if (HoaDonList.size()==0){
            btnThanhToan.setEnabled(false);
        }

        Log.d("OrderActivity","===ONCREATE===");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThanhToan=new Intent(OrderActivity.this,ThanhToanActivity.class);
                Bundle bundlett=new Bundle();
                bundlett.putInt("MABAN",MaBan);
                bundlett.putSerializable("HOADON", (Serializable) HoaDonList);
                bundlett.putSerializable("TAIKHOAN",tk);
                intentThanhToan.putExtras(bundlett);
                startActivity(intentThanhToan);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OrderActivity","TongHoaDon: "+TongHoaDonDau);
        Log.d("OrderActivity","HoaDonList: "+HoaDonList.size());
        if(HoaDonList.size()!=TongHoaDonDau){
            for(int i=TongHoaDonDau;i<HoaDonList.size();i++){
                ContentValues values=new ContentValues();
                values.put("MaMon",HoaDonList.get(i).getMaMon());
                values.put("MaBan",MaBan);
                values.put("SoLuong","1");

                database.insert("TamTinh",null,values);
                Log.d("OrderActivity","Them du lieu");
            }
        } else {
            Log.d("OrderActivity","===ONPAUSE===");
        }

    }

    private void getDataHoaDon(int i) {
        Random r=new Random();
        int[] hinhanh={R.drawable.hinh1,R.drawable.hinh2,R.drawable.hinh3,R.drawable.hinh4};
        Cursor cursor=database.rawQuery("SELECT t.MaMon,t.TenMon,t.Gia,t.AnhMon FROM ThucDon t,TamTinh tt Where t.MaMon=tt.MaMon AND tt.MaBan="+i,null);
        while (cursor.moveToNext()){
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            int hinh=r.nextInt(4);
            td.setHinhAnh(hinhanh[hinh]);
            HoaDonList.add(td);
        }
    }

    private void getFullHoaDon() {
        Cursor cursor=database.rawQuery("SELECT MaMon,TenMon,Gia,AnhMon FROM ThucDon",null);
        while (cursor.moveToNext()){
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            td.setHinhAnh(Integer.valueOf(cursor.getString(3)));
            HoaDonList.add(td);
        }
    }

    private void DialogThem() {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_mon);
        dialog.setCanceledOnTouchOutside(false);

        spinnerLoai=(Spinner) dialog.findViewById(R.id.spinnerLoai);
        spinnerHuong=(Spinner) dialog.findViewById(R.id.spinnerHuong);
        spinnerTopping=(Spinner) dialog.findViewById(R.id.spinnerThem);
        spinnerDoAn=(Spinner) dialog.findViewById(R.id.spinnerDoAn);
        btnThemOrder=(Button) dialog.findViewById(R.id.buttonThemOrder);
        btnHuy=(Button) dialog.findViewById(R.id.buttonHuyOrder);

        LoaiList=new ArrayList<>();
        ArrayAdapter spinnerLoaiAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,LoaiList);
        spinnerLoaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getdataLoai();
        spinnerLoai.setAdapter(spinnerLoaiAdapter);

        ToppingList =new ArrayList<>();
        ArrayAdapter spinnerToppingAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,ToppingList);
        getdataTopping();
        spinnerTopping.setAdapter(spinnerToppingAdapter);

        DoAnList=new ArrayList<>();
        final ArrayAdapter spinnerDoAnAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,DoAnList);
        getdataDoAn();
        spinnerDoAn.setAdapter(spinnerDoAnAdapter);

        MonList=new ArrayList<>();
        MonList.add("Chọn Loại....");

        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    MonList.clear();
                    ArrayAdapter spinnerMonAdapter=new ArrayAdapter(OrderActivity.this,android.R.layout.simple_spinner_item,MonList);
                    getdataMon(position);
                    spinnerHuong.setAdapter(spinnerMonAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThemOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerTopping.getSelectedItemPosition()!=0||spinnerDoAn.getSelectedItemPosition()!=0||spinnerLoai.getSelectedItemPosition()!=0){
                    if(spinnerLoai.getSelectedItemPosition()!=0){
                        for(int i=0;i<ThucdonList.size();i++){
                            if(spinnerHuong.getSelectedItem().equals(ThucdonList.get(i).getTenMon())){
                                HoaDonList.add(ThucdonList.get(i));
                                break;
                            }
                        }
                    }
                    if(spinnerTopping.getSelectedItemPosition()!=0){
                        for(int i=0;i<ThucdonList.size();i++){
                            if(spinnerTopping.getSelectedItem().equals(ThucdonList.get(i).getTenMon())){
                                HoaDonList.add(ThucdonList.get(i));
                                break;
                            }
                        }
                    }
                    if(spinnerDoAn.getSelectedItemPosition()!=0){
                        for(int i=0;i<ThucdonList.size();i++){
                            if(spinnerDoAn.getSelectedItem().toString().equals(ThucdonList.get(i).getTenMon())){
                                HoaDonList.add(ThucdonList.get(i));
                                break;
                            }
                        }
                    }
                    btnThanhToan.setEnabled(true);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(OrderActivity.this, "Vui lòng trọn đồ ăn hoặc nước uống", Toast.LENGTH_SHORT).show();
                    spinnerLoai.forceLayout();
                }
            }
        });
        dialog.show();
    }

    private void getdataMon(int position) {
        Cursor cursor=database.rawQuery("SELECT MaMon,TenMon,Gia,AnhMon FROM ThucDon WHERE MaLoai="+position,null);
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            td.setHinhAnh(cursor.getInt(3));
            ThucdonList.add(td);
            MonList.add(cursor.getString(1));
        }
        cursor.close();
    }

    private void getdataDoAn() {
        DoAnList.add("Chọn đồ ăn...");
        Cursor cursor=database.rawQuery("SELECT MaMon,TenMon,Gia,AnhMon FROM ThucDon WHERE MaLoai=5",null);
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            td.setHinhAnh(cursor.getInt(3));
            ThucdonList.add(td);
            DoAnList.add(cursor.getString(1));
        }
        cursor.close();
    }

    private void getdataTopping() {
        ToppingList.add("Thêm...");
        Cursor cursor=database.rawQuery("SELECT MaMon,TenMon,Gia FROM ThucDon WHERE MaLoai=4",null);
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            ThucdonList.add(td);
            ToppingList.add(cursor.getString(1));
        }
        cursor.close();
    }

    private void getdataLoai() {
        LoaiList.add("Chọn loại đồ uống...");
        Cursor cursor=database.rawQuery("SELECT TenLoai FROM Loai",null);
        for (int i=0;i<3;i++){
            cursor.moveToPosition(i);
            LoaiList.add(cursor.getString(0));
        }
        cursor.close();
    }

}
