package com.zuckerteam.mevabe.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zuckerteam.adapter.LichKhamThaiAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.lich_kham_thai.LichKhamThai;

import java.util.ArrayList;

public class LichKhamThaiActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private RecyclerView rcvLichKhamThai;
    private LichKhamThaiAdapter adapter;
    private ArrayList<LichKhamThai> lichKhamThais;
    private DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_kham_thai);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.lich_kham_thai);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        rcvLichKhamThai = (RecyclerView) findViewById(R.id.rcvLichKhamThai);
        rcvLichKhamThai.setLayoutManager(new LinearLayoutManager(this));
        layDuLieu();
        adapter = new LichKhamThaiAdapter(this,lichKhamThais);
        rcvLichKhamThai.setAdapter(adapter);
    }

    private void layDuLieu() {
        databaseClass = new DatabaseClass(this);
        lichKhamThais = new ArrayList<>();
        lichKhamThais.addAll(databaseClass.getData("LichKhamThai"));
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTroVe:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
