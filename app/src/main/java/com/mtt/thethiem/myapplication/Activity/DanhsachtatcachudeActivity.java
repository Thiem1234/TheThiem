package com.mtt.thethiem.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mtt.thethiem.myapplication.Adapter.DanhsachtatcachudeAdapter;
import com.mtt.thethiem.myapplication.Model.ChuDe;
import com.mtt.thethiem.myapplication.R;
import com.mtt.thethiem.myapplication.Service.APIService;
import com.mtt.thethiem.myapplication.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllChuDe;
    Toolbar toolbarAllChuDe;
    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangChuDe = (ArrayList<ChuDe>) response.body();
                danhsachtatcachudeAdapter = new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this,mangChuDe);
                recyclerViewAllChuDe.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1));
                recyclerViewAllChuDe.setAdapter(danhsachtatcachudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllChuDe = findViewById(R.id.recyclerviewAllChuDe);
        toolbarAllChuDe = findViewById(R.id.toolbarAllChuDe);
        setSupportActionBar(toolbarAllChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarAllChuDe.setTitleTextColor(getResources().getColor(R.color.mauden));
        toolbarAllChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
