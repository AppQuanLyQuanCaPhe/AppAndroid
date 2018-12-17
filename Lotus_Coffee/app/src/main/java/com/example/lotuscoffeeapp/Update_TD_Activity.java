package com.example.lotuscoffeeapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Update_TD_Activity extends AppCompatActivity {
    List<ThucDon> ThucDonList,FullList;
    ListView listView;
    SQLiteDatabase database;
    ArrayList<Integer> ImageID;
    TextView mon;
    Button btnLuu,btnHuy;
    EditText edtGia;
    ThucDonAdapter thucdonadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__td_);

        listView =(ListView) findViewById(R.id.lv_updateThucDon);

        database=Database.initDatabase(Update_TD_Activity.this,MainActivity.DATABASE_NAME);

        ThucDonList=new ArrayList<>();
        getThucDon();
        CapNhatHinhAnh();
        FullList=new ArrayList<>();
        getFullList();
        thucdonadapter = new ThucDonAdapter (Update_TD_Activity.this,R.layout.mon_item,FullList);
        listView.setAdapter(thucdonadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUpdate(FullList.get(position));
            }

        });
    }
    private void DialogUpdate(final ThucDon thucdondau) {

        final Dialog dialog=new Dialog(Update_TD_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_thucdon);
        dialog.setCanceledOnTouchOutside(false);

        mon = (TextView) dialog.findViewById(R.id.txt_Mon);
        btnLuu = (Button) dialog.findViewById(R.id.btn_Luu);
        btnHuy = (Button) dialog.findViewById(R.id.btn_Huy);
        edtGia = (EditText) dialog.findViewById(R.id.edt_GiaTien);

        edtGia.setText(thucdondau.getGia());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gia = edtGia.getText().toString().trim();

                if(TextUtils.isEmpty(gia))
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập giá tiền cần thay đổi!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    thucdondau.setGia(gia);
                }
                ContentValues values=new ContentValues();
                values.put("TenMon",thucdondau.getTenMon());
                values.put("Gia",thucdondau.getGia());
                values.put("AnhMon",thucdondau.getHinhAnh());
                values.put("MaLoai",thucdondau.getMaLoai());

                database.update("ThucDon",values,"MaMon=?",new String[]{thucdondau.getMaMon()+""});
                thucdonadapter.notifyDataSetChanged();
                getFullList();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getFullList() {
        FullList.clear();
        Cursor cursor=database.rawQuery("SELECT MaMon,TenMon,Gia,AnhMon,MaLoai FROM ThucDon",null);
        while (cursor.moveToNext()){
            ThucDon td=new ThucDon();
            td.setMaMon(cursor.getInt(0));
            td.setTenMon(cursor.getString(1));
            td.setGia(cursor.getString(2));
            td.setHinhAnh(Integer.valueOf(cursor.getString(3)));
            td.setMaLoai(Integer.valueOf(cursor.getString(4)));
            FullList.add(td);
        }
    }

    private void getThucDon() {
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

    private void CapNhatHinhAnh() {
        GetImageID();
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
}
