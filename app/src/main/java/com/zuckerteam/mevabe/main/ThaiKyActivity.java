package com.zuckerteam.mevabe.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.adapter.DanhMuc_NoiDung_SanPham_TinTucAdapter;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.mevabe.thaiky.BienChungThaiKyActivity;
import com.zuckerteam.mevabe.thaiky.BieuDoThaiKyActivity;
import com.zuckerteam.mevabe.thaiky.ViecCanLamActivity;
import com.zuckerteam.model.danhmuc_noidung_sanpham_tintuc.DanhMuc_NoiDung_SanPham_TinTuc;

import java.util.ArrayList;

public class ThaiKyActivity extends AppCompatActivity implements View.OnClickListener,DanhMuc_NoiDung_SanPham_TinTucAdapter.onItemClick {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private RecyclerView rcvThaiKy;
    private ArrayList<DanhMuc_NoiDung_SanPham_TinTuc> thaikys;
    private DanhMuc_NoiDung_SanPham_TinTucAdapter thaiKyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_ky);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.thai_ky);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        rcvThaiKy = (RecyclerView) findViewById(R.id.rcvThaiKy);
        rcvThaiKy.setLayoutManager(new GridLayoutManager(this,2));
        thaikys = new ArrayList<DanhMuc_NoiDung_SanPham_TinTuc>();
        thaikys.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_bieu_do_thai_ky,"Biểu đồ thai kỳ"));
        thaikys.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_viec_can_lam,"Việc cần làm"));
        thaikys.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_bien_chung_thai_ky,"Biến chứng thai kỳ"));

        thaiKyAdapter = new DanhMuc_NoiDung_SanPham_TinTucAdapter(this,thaikys,R.layout.item_danh_muc);
        thaiKyAdapter.setOnItemClick(this);
        rcvThaiKy.setAdapter(thaiKyAdapter);
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

    @Override
    public void onItemClicked(int position, DanhMuc_NoiDung_SanPham_TinTuc object) {
        switch (position){
            case 0:
                handleClickBieuDoThaiKy();
                break;
            case 1:
                handleClickViecCanLam();
                break;
            case 2:
                handleClickBienChungThaiKy();
                break;
        }
    }

    private void handleClickBieuDoThaiKy() {
        Intent intent = new Intent(ThaiKyActivity.this, BieuDoThaiKyActivity.class);
        startActivity(intent);
    }

    private void handleClickViecCanLam() {
        Intent intent = new Intent(ThaiKyActivity.this, ViecCanLamActivity.class);
        startActivity(intent);
    }

    private void handleClickBienChungThaiKy() {
        Intent intent = new Intent(ThaiKyActivity.this, BienChungThaiKyActivity.class);
        startActivity(intent);
    }
}
