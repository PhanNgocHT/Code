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

public class MuaSamActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private TabLayout tlMuaSam;
    private ViewPager vpMuaSam;
    private ArrayList<String> arrPager;
    private ChiTietTinTucMeVaBePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_sam);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.mua_sam);
        setSupportActionBar(toolbar);
    }


    private void addControls() {
        tlMuaSam = (TabLayout) findViewById(R.id.tlMuaSam);
        vpMuaSam = (ViewPager) findViewById(R.id.vpMuaSam);
        arrPager = new ArrayList<>();
        arrPager.add("Đồ dùng bà bầu");
        arrPager.add("Đồ dùng sau sinh");
        pagerAdapter = new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(), arrPager, Constant.chuyenMucMuaSam);
        vpMuaSam.setAdapter(pagerAdapter);
        tlMuaSam.setupWithViewPager(vpMuaSam);

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
