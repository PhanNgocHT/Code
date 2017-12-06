package com.zuckerteam.mevabe.tintucmavabe;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.adapter.ChiTietTinTucMeVaBePagerAdapter;
import com.zuckerteam.Constant;
import com.zuckerteam.mevabe.R;

import java.util.ArrayList;

public class GocHaiHuocActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private TabLayout tlGocHaiHuoc;
    private ViewPager vpGocHaiHuoc;
    private ChiTietTinTucMeVaBePagerAdapter chiTietTinTucMevaBePagerAdapter;
    private ArrayList<String> arrPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goc_hai_huoc);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe.setText(R.string.goc_hai_huoc);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlGocHaiHuoc = (TabLayout) findViewById(R.id.tlGochaiHuoc);
        vpGocHaiHuoc = (ViewPager) findViewById(R.id.vpGocHaiHuoc);
        arrPager = new ArrayList<>();
        arrPager.add("Ảnh đẹp của bé");
        arrPager.add("Ngộ nghĩnh trẻ thơ");
        chiTietTinTucMevaBePagerAdapter =
                new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(),
                        arrPager,
                        Constant.chuyenMucGocHaiHuoc);
        vpGocHaiHuoc.setAdapter(chiTietTinTucMevaBePagerAdapter);
        tlGocHaiHuoc.setupWithViewPager(vpGocHaiHuoc);
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
