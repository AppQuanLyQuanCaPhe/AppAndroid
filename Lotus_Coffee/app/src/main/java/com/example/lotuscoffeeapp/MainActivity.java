package com.example.lotuscoffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView grdvBan;
    List<String> ban;
    GridViewBanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        grdvBan=(GridView) findViewById(R.id.gridViewBan);
        ban=new ArrayList<>();
        getdataBan();
        adapter=new GridViewBanAdapter(this,ban);
        grdvBan.setAdapter(adapter);

        grdvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("BAN",ban.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void getdataBan() {
        for (int i=0;i<10;i++){
            ban.add("Bàn "+(i+1));
        }
    }
}
