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

public class ChamSocTreActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private TabLayout tlChamSocTre;
    private ViewPager vpChamSocTre;
    private ChiTietTinTucMeVaBePagerAdapter chamSocTrePagerAdapter;
    private ArrayList<String> arrPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_soc_tre);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.cham_soc_tre);
        setSupportActionBar(toolbar);

    }

    private void addControls() {
        tlChamSocTre = (TabLayout) findViewById(R.id.tlChamSocTre);
        vpChamSocTre = (ViewPager) findViewById(R.id.vpChamSocTre);
        arrPager = new ArrayList<>();
        arrPager.add("Chăm sóc trẻ sơ sinh");
        arrPager.add("Cho con ăn dặm");
        arrPager.add("Tăng chiều cao cho trẻ");
        chamSocTrePagerAdapter = new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(), arrPager, Constant.chuyenMucChamSocTre);
        vpChamSocTre.setAdapter(chamSocTrePagerAdapter);
        tlChamSocTre.setupWithViewPager(vpChamSocTre);
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
