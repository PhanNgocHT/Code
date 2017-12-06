package com.zuckerteam.mevabe.tintucmavabe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.TinTucOfflineAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.TinTucYeuThich;
import com.zuckerteam.wepview.WepViewOfflineActivity;

import java.util.ArrayList;

public class YeuThichActivity extends AppCompatActivity implements TinTucOfflineAdapter.OnItemClick, View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageView imgBackGroundYeuThich;
    private ImageButton btnTroVe;
    private ImageButton btnCaiDat;
    private RecyclerView rcvYeuThich;
    private ArrayList<TinTucYeuThich> yeuThichs;
    private TinTucOfflineAdapter adapter;
    DatabaseClass databaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);
        setUpActionBar();
        addControls();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        yeuThichs.clear();
        yeuThichs.addAll(databaseClass.getData("TinTucYeuThich"));
        adapter.notifyDataSetChanged();
        if(yeuThichs.isEmpty()){
            imgBackGroundYeuThich.setVisibility(View.VISIBLE);
            rcvYeuThich.setVisibility(View.INVISIBLE);
        }
    }

    private void addControls() {
        imgBackGroundYeuThich = (ImageView) findViewById(R.id.imgBackGroundYeuThich);
        rcvYeuThich = (RecyclerView) findViewById(R.id.rcvYeuThich);
        rcvYeuThich.setLayoutManager(new LinearLayoutManager(this));
        layDuLieu();
        adapter = new TinTucOfflineAdapter(this,yeuThichs);
        adapter.setOnItemClick(this);
        rcvYeuThich.setAdapter(adapter);

        if(yeuThichs.isEmpty()){
            imgBackGroundYeuThich.setVisibility(View.VISIBLE);
            rcvYeuThich.setVisibility(View.INVISIBLE);
        }else if(!yeuThichs.isEmpty()){
            imgBackGroundYeuThich.setVisibility(View.INVISIBLE);
            rcvYeuThich.setVisibility(View.VISIBLE);
        }
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnCaiDat.setOnClickListener(this);
    }

    private void layDuLieu() {
        yeuThichs = new ArrayList<>();
        databaseClass = new DatabaseClass(this);
        yeuThichs.addAll(databaseClass.getData("TinTucYeuThich"));
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        btnCaiDat  = (ImageButton) findViewById(R.id.btnCaiDat);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Yêu thích");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(YeuThichActivity.this, WepViewOfflineActivity.class);
        intent.putExtra(Constant.KEY_TIN_TUC_OFFLINE,yeuThichs.get(position));
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTroVe:
                onBackPressed();
                break;
            case R.id.btnCaiDat:
                handleCaiDat();
                break;
            default:
                break;
        }
    }

    private void handleCaiDat() {
        PopupMenu popupMenu = new PopupMenu(YeuThichActivity.this,btnCaiDat);
        popupMenu.setGravity(Gravity.RIGHT);
        popupMenu.getMenuInflater().inflate(R.menu.menu_yeu_thich_activity,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.mnuXoaTatCa){
                    DatabaseClass databaseClass = new DatabaseClass(YeuThichActivity.this);
                    for (int i =0 ;i<yeuThichs.size();i++){
                        databaseClass.deleteData(yeuThichs.get(i),"TinTucYeuThich");
                    }
                    yeuThichs.clear();
                    adapter.notifyDataSetChanged();
                    imgBackGroundYeuThich.setVisibility(View.VISIBLE);
                    rcvYeuThich.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        popupMenu.show();

    }
}
