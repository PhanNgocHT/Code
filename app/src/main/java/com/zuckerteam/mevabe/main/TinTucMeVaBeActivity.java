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
import com.zuckerteam.mevabe.tintucmavabe.ChamSocTreActivity;
import com.zuckerteam.mevabe.tintucmavabe.DayConActivity;
import com.zuckerteam.mevabe.tintucmavabe.DinhDuongBaBauActivity;
import com.zuckerteam.mevabe.tintucmavabe.GocHaiHuocActivity;
import com.zuckerteam.mevabe.tintucmavabe.KinhNghiemDiDeActivity;
import com.zuckerteam.mevabe.tintucmavabe.YeuThichActivity;
import com.zuckerteam.model.danhmuc_noidung_sanpham_tintuc.DanhMuc_NoiDung_SanPham_TinTuc;

import java.util.ArrayList;

public class TinTucMeVaBeActivity extends AppCompatActivity implements DanhMuc_NoiDung_SanPham_TinTucAdapter.onItemClick, View.OnClickListener {
    private ArrayList<DanhMuc_NoiDung_SanPham_TinTuc> tinTucs;
    private DanhMuc_NoiDung_SanPham_TinTucAdapter tinTucAdapter;
    private RecyclerView rcvTinTucMeVaBe;
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_me_va_be);
        setAppActionBar();
        addControls();
        addEvent();
    }

    private void setAppActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.tin_tuc_me_va_be);
        setSupportActionBar(toolbar);

    }

    private void addControls() {
        rcvTinTucMeVaBe = (RecyclerView) findViewById(R.id.rcvTinTucMeVabe);
        rcvTinTucMeVaBe.setLayoutManager(new GridLayoutManager(this, 2));
        tinTucs = new ArrayList<>();
        addTinTuc();
        tinTucAdapter = new DanhMuc_NoiDung_SanPham_TinTucAdapter(this, tinTucs, R.layout.item_danh_muc_tin_tuc_me_va_be_grid_layout);
        rcvTinTucMeVaBe.setAdapter(tinTucAdapter);

    }

    private void addTinTuc() {
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_dinh_duong_ba_bau, "Dinh dưỡng bà bầu"));
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_cham_soc_tre, "Chăm sóc trẻ"));
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_kinh_nghiem_di_de, "Kinh nghiệm sinh con"));
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_day_con, "Dạy con"));
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_goc_hai_huoc, "Góc hài hước"));
        tinTucs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_yeu_thich, "Yêu thích"));
    }

    private void addEvent() {
        btnTroVe.setOnClickListener(this);
        tinTucAdapter.setOnItemClick(this);
    }

    @Override
    public void onItemClicked(int position, DanhMuc_NoiDung_SanPham_TinTuc object) {
        switch (position) {
            case 0:
                handleClickItemDinhDuongBaBau();
                break;
            case 1:
                handleClickItemChamSocTre();
                break;
            case 2:
                handleClickItemKinhNghiemDiDe();
                break;
            case 3:
                handleClickItemDayTreBiet();
                break;
            case 4:
                handleClickItemGocHaiHuoc();
                break;
            case 5:
                handleClickItemYeuThich();
                break;
            default:
                break;
        }
    }

    private void handleClickItemDinhDuongBaBau() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, DinhDuongBaBauActivity.class);
        startActivity(intent);
    }

    private void handleClickItemKinhNghiemDiDe() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, KinhNghiemDiDeActivity.class);
        startActivity(intent);
    }

    private void handleClickItemChamSocTre() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, ChamSocTreActivity.class);
        startActivity(intent);
    }

    private void handleClickItemDayTreBiet() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, DayConActivity.class);
        startActivity(intent);
    }

    private void handleClickItemGocHaiHuoc() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, GocHaiHuocActivity.class);
        startActivity(intent);
    }

    private void handleClickItemYeuThich() {
        Intent intent = new Intent(TinTucMeVaBeActivity.this, YeuThichActivity.class);
        startActivity(intent);
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
