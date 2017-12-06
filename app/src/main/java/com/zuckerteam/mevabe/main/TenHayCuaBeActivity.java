package com.zuckerteam.mevabe.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.ChiTietTinTucMeVaBePagerAdapter;
import com.zuckerteam.mevabe.R;

import java.util.ArrayList;

public class TenHayCuaBeActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private TabLayout tlTenHayCuaBe;
    private ViewPager vpTenHayCuaBe;
    private ChiTietTinTucMeVaBePagerAdapter tenHayCuaBePagerAdapter;
    private ArrayList<String> arrPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_hay_cua_be);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.ten_hay_cua_be);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlTenHayCuaBe = (TabLayout) findViewById(R.id.tlTenHayCuaBe);
        vpTenHayCuaBe = (ViewPager) findViewById(R.id.vpTenHayCuaBe);
        arrPager = new ArrayList<>();
        arrPager.add("Tên bé trai");
        arrPager.add("Tên bé gái");
        tenHayCuaBePagerAdapter =
                new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(),
                        arrPager, Constant.chuyenMucTenHayCuaBe);
        vpTenHayCuaBe.setAdapter(tenHayCuaBePagerAdapter);
        tlTenHayCuaBe.setupWithViewPager(vpTenHayCuaBe);
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
