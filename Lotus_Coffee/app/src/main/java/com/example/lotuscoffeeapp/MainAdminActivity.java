package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainAdminActivity extends Activity {
    TaiKhoan tk;
    GridLayout grlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Intent intentLogin=getIntent();
        Bundle bundleLogin=intentLogin.getExtras();
        tk=new TaiKhoan();
        tk= (TaiKhoan) bundleLogin.getSerializable("TAIKHOAN");
        Toast.makeText(this, ""+tk.getTendangnhap()+" "+tk.getMachucvu()+" "+tk.getManv(), Toast.LENGTH_SHORT).show();
        grlMain=(GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(grlMain);

    }

    private void setSingleEvent(GridLayout grlMain) {
        //Loop all child item of Main Grid
        for (int i = 0; i < grlMain.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) grlMain.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI){
                        case 0:
                            Intent intent0=new Intent(MainAdminActivity.this,NhanvienActivity.class);
                            startActivity(intent0);
                            break;
                        case 1:
                            Intent intent1=new Intent(MainAdminActivity.this,HoaDonActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("TAIKHOAN",tk);
                            intent1.putExtras(bundle);
                            startActivity(intent1);
                            break;
                        case 2:
                            Toast.makeText(MainAdminActivity.this, "Tính năng đang phát triển", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainAdminActivity.this,Update_TD_Activity.class));
                            break;
                        case 3:
                            startActivity(new Intent(MainAdminActivity.this,LoginActivity.class));
                            break;
                    }
                }
            });
        }
    }

}
