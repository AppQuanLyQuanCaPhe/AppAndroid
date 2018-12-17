package com.example.lotuscoffeeapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {

    public class HoaDonAdapter extends ArrayAdapter<HoaDon>{

        public HoaDonAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }
        public HoaDonAdapter(){
            super(HoaDonActivity.this,android.R.layout.simple_list_item_1,hoadonlist);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if(row==null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.dong_hd,null);
            }
            HoaDon r = hoadonlist.get(position);
            ((TextView)row.findViewById(R.id.textSoBan)).setText("Số bàn: "+r.getMaBan());
            ((TextView)row.findViewById(R.id.textNgay)).setText("Ngày: "+r.getNgayLap());
            ((TextView)row.findViewById(R.id.textTien)).setText("Tiền: "+r.getTien());

            return  row;
        }
    }

    ListView lvHoaDon;
    Button btnRedo;

    TaiKhoan tk;
    List<HoaDon> hoadonlist = new ArrayList<HoaDon>();
    HoaDonAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        btnRedo=(Button) findViewById(R.id.buttonQuayLai);

        lvHoaDon = (ListView)findViewById(R.id.listViewHoaDon);
        SQLiteDatabase db = Database.initDatabase(HoaDonActivity.this, MainActivity.DATABASE_NAME);
        Cursor cursor = db.rawQuery("select * from  HoaDon ",null);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tk=new TaiKhoan();
        tk= (TaiKhoan) bundle.getSerializable("TAIKHOAN");

        while (cursor.moveToNext()) {
            HoaDon hd = new HoaDon();
            hd.setMaBan(cursor.getString(1));
            hd.setNgayLap(cursor.getString(3));
            hd.setTien(cursor.getString(4));
            hoadonlist.add(hd);

        }
        adapter = new HoaDonAdapter();

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
