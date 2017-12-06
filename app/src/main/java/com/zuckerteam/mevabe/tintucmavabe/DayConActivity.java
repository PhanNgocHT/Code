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

public class DayConActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private TabLayout tlDayCon;
    private ViewPager vpDayCon;
    private ChiTietTinTucMeVaBePagerAdapter chiTietTinTucMevaBePagerAdapter;
    private ArrayList<String> arrPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_con);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.day_con);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlDayCon = (TabLayout) findViewById(R.id.tlDayCon);
        vpDayCon = (ViewPager) findViewById(R.id.vpDayCon);
        arrPager = new ArrayList<>();
        arrPager.add("Dạy con thông minh");
        arrPager.add("Chia sẻ kinh nghiệm");
        arrPager.add("Sai lầm cần tránh");
        chiTietTinTucMevaBePagerAdapter =
                new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(),
                        arrPager,
                        Constant.chuyenMucDayCon);
        vpDayCon.setAdapter(chiTietTinTucMevaBePagerAdapter);
        tlDayCon.setupWithViewPager(vpDayCon);
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
