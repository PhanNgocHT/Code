package com.zuckerteam.mevabe.thaiky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.BieuDoThaiKyAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;

import java.util.ArrayList;

public class BieuDoThaiKyActivity extends AppCompatActivity implements View.OnClickListener, BieuDoThaiKyAdapter.OnClick {
    public static final String TUAN_THAI_KY = "tuan";
    Toolbar toolbar;
    ImageButton btnTroVe;
    ImageButton btnChuyenHoatLayout;
    TextView tvTieuDe;
    RecyclerView rcvBieuDoThaiKy;
    ArrayList<BieuDoThaiKy> bieuDoThaiKies;
    BieuDoThaiKyAdapter adapter;
    DatabaseClass database;
    private String tableName = "BieuDoThaiKy";
    boolean stateGridLayout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieu_do_thai_ky);
        layDuLieu();
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Biểu đồ thai kỳ");
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        btnChuyenHoatLayout = (ImageButton) findViewById(R.id.btnChuyenHoatLayout);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        rcvBieuDoThaiKy = (RecyclerView) findViewById(R.id.rcvBieuDoThaiKy);
        rcvBieuDoThaiKy.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new BieuDoThaiKyAdapter(this, bieuDoThaiKies,R.layout.item_bieu_do_thai_ky_linearlayout);
        adapter.setOnClick(this);
        rcvBieuDoThaiKy.setAdapter(adapter);
    }

    private void layDuLieu() {
        bieuDoThaiKies = new ArrayList<>();
        database = new DatabaseClass(this);
        bieuDoThaiKies.addAll(database.getData(tableName));
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnChuyenHoatLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTroVe:
                onBackPressed();
                break;
            case R.id.btnChuyenHoatLayout:
                handleChuyenHoatLayout();
            default:
                break;
        }
    }

    private void handleChuyenHoatLayout() {
        if(stateGridLayout==true){
            rcvBieuDoThaiKy.setLayoutManager(new GridLayoutManager(this, 1));
            adapter = new BieuDoThaiKyAdapter(this, bieuDoThaiKies,R.layout.item_bieu_do_thai_ky_linearlayout);
            stateGridLayout=false;
            btnChuyenHoatLayout.setBackgroundResource(R.drawable.ic_linear_layout);
            adapter.setOnClick(this);
            rcvBieuDoThaiKy.setAdapter(adapter);
        }else if(stateGridLayout==false){
            rcvBieuDoThaiKy.setLayoutManager(new GridLayoutManager(this, 3));
            adapter = new BieuDoThaiKyAdapter(this, bieuDoThaiKies,R.layout.item_bieu_do_thai_ky_gridlayout);
            stateGridLayout=true;
            btnChuyenHoatLayout.setBackgroundResource(R.drawable.ic_grid_layout);
            adapter.setOnClick(this);
            rcvBieuDoThaiKy.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(BieuDoThaiKyActivity.this, ChiTietBieuDoThaiKyActivity.class);
        BieuDoThaiKy bieuDoThaiKy = bieuDoThaiKies.get(position);
        intent.putExtra(Constant.KEY_BIEU_DO_THAI_KY, bieuDoThaiKy);
        startActivity(intent);
    }


}
