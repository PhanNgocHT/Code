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

public class DinhDuongBaBauActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private TabLayout tlDinhDuongBaBau;
    private ViewPager vpDinhDuongBaBau;
    private ArrayList<String> arrPager;
    private ChiTietTinTucMeVaBePagerAdapter pagerDinhDuongBaBauAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinh_duong_ba_bau);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe.setText(R.string.dinh_duong_ba_bau);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlDinhDuongBaBau = (TabLayout) findViewById(R.id.tlDinhDuongBaBau);
        vpDinhDuongBaBau = (ViewPager) findViewById(R.id.vpDinhDuongBaBau);
        arrPager = new ArrayList<>();
        arrPager.add("Thực phẩm nên ăn");
        arrPager.add("Thực phẩm nên tránh");
        arrPager.add("Lưu ý dinh dưỡng");
        pagerDinhDuongBaBauAdapter = new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(), arrPager, Constant.chuyenMucDinhDuongBaBau);
        vpDinhDuongBaBau.setAdapter(pagerDinhDuongBaBauAdapter);
        tlDinhDuongBaBau.setupWithViewPager(vpDinhDuongBaBau);
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
