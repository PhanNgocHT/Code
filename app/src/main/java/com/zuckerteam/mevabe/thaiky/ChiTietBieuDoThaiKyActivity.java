package com.zuckerteam.mevabe.thaiky;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.ChiTietTinTucMeVaBeAdapter;
import com.zuckerteam.adapter.ChiTietTinTucMeVaBePagerAdapter;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;

import java.util.ArrayList;

public class ChiTietBieuDoThaiKyActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private TabLayout tlChiTietBieuDoThaiKy;
    private ViewPager vpChiTietBieuDoThaiKy;
    private ArrayList<String> arrPager;
    ChiTietTinTucMeVaBePagerAdapter adapter;
    private String tieuDe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bieu_do_thai_ky);
        setUpToolbar();
        addControls();
        addEvents();
    }

    private void setUpToolbar() {
        Intent intent = getIntent();
        BieuDoThaiKy bieuDoThaiKy = (BieuDoThaiKy) intent.getSerializableExtra(Constant.KEY_BIEU_DO_THAI_KY);
        tieuDe = bieuDoThaiKy.getTuanThaiKy();
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(tieuDe);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        tlChiTietBieuDoThaiKy = (TabLayout) findViewById(R.id.tlChiTietBieuDoThaiKy);
        arrPager = new ArrayList<>();
        arrPager.add("Diễn biến thai kỳ");
        arrPager.add("Lời khuyên");
        adapter = new ChiTietTinTucMeVaBePagerAdapter(getSupportFragmentManager(),arrPager,Constant.chuyenMucBieuDoThaiKy);
        vpChiTietBieuDoThaiKy = (ViewPager) findViewById(R.id.vpChiTietBieuDoThaiKy);
        vpChiTietBieuDoThaiKy.setAdapter(adapter);
        tlChiTietBieuDoThaiKy.setupWithViewPager(vpChiTietBieuDoThaiKy);
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
