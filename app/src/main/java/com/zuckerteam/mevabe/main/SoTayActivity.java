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

public class SoTayActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private TabLayout tlSoTayTiemPhong;
    private ViewPager vpSoTayTiemPhong;
    private ChiTietTinTucMeVaBePagerAdapter soTayTiemPhongPagerAdapter;
    private ArrayList<String> arrPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_tay);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Sổ tay tiêm phòng");
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlSoTayTiemPhong = (TabLayout) findViewById(R.id.tlSoTayTiemPhong);
        vpSoTayTiemPhong = (ViewPager) findViewById(R.id.vpSoTayTiemPhong);
        arrPager = new ArrayList<>();
        arrPager.add("Theo dõi trẻ");
        arrPager.add("Lịch tiêm phòng");
        soTayTiemPhongPagerAdapter = new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(),arrPager, Constant.chuyenMucSoTayTiemPhong);
        vpSoTayTiemPhong.setAdapter(soTayTiemPhongPagerAdapter);
        tlSoTayTiemPhong.setupWithViewPager(vpSoTayTiemPhong);

    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTroVe:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
