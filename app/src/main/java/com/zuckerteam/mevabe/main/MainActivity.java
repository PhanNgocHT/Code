package com.zuckerteam.mevabe.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.zuckerteam.adapter.DanhMuc_NoiDung_SanPham_TinTucAdapter;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.danhmuc_noidung_sanpham_tintuc.DanhMuc_NoiDung_SanPham_TinTuc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DanhMuc_NoiDung_SanPham_TinTucAdapter.onItemClick {
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayoutNavigation;
    private ActionBarDrawerToggle toggle;
    private ActionBar actionBar;
    private RecyclerView rcvNoiDung, rcvSanPham, rcvDanhMuc;
    private ArrayList<DanhMuc_NoiDung_SanPham_TinTuc> danhMucs, noiDungs, sanPhams;
    private DanhMuc_NoiDung_SanPham_TinTucAdapter danhMucAdapter, noiDungAdapter, sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBar();
        addDrawerLayout();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.close);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.mipmap.ic_app);
    }

    private void addDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        linearLayoutNavigation = (LinearLayout) findViewById(R.id.linearLayoutNavigation);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(R.string.open);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(R.string.close);
            }
        };
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggle.syncState();
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        rcvNoiDung = (RecyclerView) findViewById(R.id.rcvNoiDung);
        rcvNoiDung.setLayoutManager(new LinearLayoutManager(this));
        noiDungs = new ArrayList<DanhMuc_NoiDung_SanPham_TinTuc>();
        addNoiDung();
        noiDungAdapter = new DanhMuc_NoiDung_SanPham_TinTucAdapter(this, noiDungs, R.layout.item_noi_dung);
        rcvNoiDung.setAdapter(noiDungAdapter);

        rcvSanPham = (RecyclerView) findViewById(R.id.rcvSanPham);
        rcvSanPham.setLayoutManager(new LinearLayoutManager(this));
        sanPhams = new ArrayList<>();
        addSanPham();
        sanPhamAdapter = new DanhMuc_NoiDung_SanPham_TinTucAdapter(this, sanPhams, R.layout.item_san_pham);
        rcvSanPham.setAdapter(sanPhamAdapter);

        rcvDanhMuc = (RecyclerView) findViewById(R.id.rcvDanhMuc);
        rcvDanhMuc.setLayoutManager(new GridLayoutManager(this, 2));
        danhMucs = new ArrayList<>();
        addDanhMuc();
        danhMucAdapter = new DanhMuc_NoiDung_SanPham_TinTucAdapter(this, danhMucs, R.layout.item_danh_muc);
        rcvDanhMuc.setAdapter(danhMucAdapter);
    }

    private void addSanPham() {
        //sanPhams.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_danh_gia_ung_dung, "Đánh giá ứng dụng"));
        sanPhams.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_chia_se, "Chia sẻ ứng dụng"));
        sanPhams.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_gui_mail_gop_y, "Gửi mail góp ý"));
        //sanPhams.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_ung_dung_khac, "Ứng dụng khác"));
    }

    private void addNoiDung() {
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_tin_tuc_me_va_be, "Tin tức mẹ và bé"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_thai_ky, "Thai kỳ"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_lich_kham_thai, "Lịch khám thai"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_lich_tiem_phong, "Sổ tay tiêm phòng "));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_mua_sam, "Mua sắm"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_dat_ten_cho_be, "Tên của bé"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_doc_truyen_cho_be, "Kể chuyện cho bé"));
        noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_nhac_cho_ba_bau, "Âm nhạc"));
        //noiDungs.add(new DanhMuc_NoiDung_SanPham_TinTuc(R.drawable.icon_thoi_tiet, "Thời tiết"));
    }

    private void addDanhMuc() {
        danhMucs.addAll(noiDungs);
        danhMucs.addAll(sanPhams);
    }

    private void addEvents() {
        noiDungAdapter.setOnItemClick(this);
        sanPhamAdapter.setOnItemClick(this);
        danhMucAdapter.setOnItemClick(this);
    }

    private boolean checkIsSanPham(int position, DanhMuc_NoiDung_SanPham_TinTuc object) {
        if (object == sanPhams.get(position))
            return true;
        else return false;
    }


    @Override
    public void onItemClicked(int position, DanhMuc_NoiDung_SanPham_TinTuc object) {
        switch (position) {
            case 0:
                if (checkIsSanPham(position, object) == true) {
                    //handleClickItemDanhGiaUngDung();
                    handleClickItemChiaSeUngDung();
                } else {
                    handleClickItemTinTucMeVaBe();
                }
                checkDrawerOpen();
                break;
            case 1:
                if (checkIsSanPham(position, object) == true) {
                    //handleClickItemChiaSeUngDung();
                    handleClickItemGuiMailGopY();
                } else
                    handleClickItemThaiKy();
                checkDrawerOpen();
                break;
            case 2:
                checkDrawerOpen();
                handleClickItemLichKhamThai();
                break;
            case 3:
                handleClickItemSoTayTiemPhong();
                break;
            case 4:
                checkDrawerOpen();
                handleClickItemMuaSam();
                break;
            case 5:
                checkDrawerOpen();
                handleClickItemTenCuaBe();
                break;
            case 6:
                checkDrawerOpen();
                handleClickItemKeChuyenChoBe();
                break;
            case 7:
                checkDrawerOpen();
                handleClickItemNhacChoBaBau();
                break;
            case 8:
                checkDrawerOpen();
                handleClickItemChiaSeUngDung();
                break;
            case 9:
                checkDrawerOpen();
                handleClickItemGuiMailGopY();
                break;
            default:
                break;
        }
    }

    private void handleClickItemTinTucMeVaBe() {
        Intent intent = new Intent(this, TinTucMeVaBeActivity.class);
        startActivity(intent);
    }

    private void handleClickItemThaiKy() {
        Intent intent = new Intent(this, ThaiKyActivity.class);
        startActivity(intent);
    }

    private void handleClickItemLichKhamThai() {
        Intent intent = new Intent(this, LichKhamThaiActivity.class);
        startActivity(intent);
    }

    private void handleClickItemSoTayTiemPhong() {
        Intent intent = new Intent(this, SoTayActivity.class);
        startActivity(intent);
    }

    private void handleClickItemMuaSam() {
        Intent intent = new Intent(this, MuaSamActivity.class);
        startActivity(intent);
    }

    private void handleClickItemTenCuaBe() {
        Intent intent = new Intent(this, TenHayCuaBeActivity.class);
        startActivity(intent);
    }

    private void handleClickItemKeChuyenChoBe() {
        Intent intent = new Intent(this, KeChuyenChoBeActivity.class);
        startActivity(intent);
    }

    private void handleClickItemNhacChoBaBau() {
        Intent intent = new Intent(this, AmNhacActivity.class);
        startActivity(intent);
    }

    private void handleClickItemThoiTiet() {
        Intent intent = new Intent(this, ThoiTietActivity.class);
        startActivity(intent);
    }

    private void handleClickItemDanhGiaUngDung() {
        Intent intent = new Intent(this, DanhGiaUngDungActivity.class);
        startActivity(intent);
    }


    private void handleClickItemChiaSeUngDung() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Ứng dụng Mẹ Và Bé";
        String shareSub = "Send By Dung Zucker";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Chia sẻ với"));
    }

    private void handleClickItemGuiMailGopY() {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setType("plain/text");
        email.putExtra(Intent.EXTRA_SUBJECT, "Góp ý cho ứng dụng : Mẹ Và Bé");
        email.putExtra(Intent.EXTRA_TEXT, "");
        email.setData(Uri.parse("mailto:badung1996@gmail.com"));
        email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(email, "Gửi email với"));
    }

    private void handleClickItemUngDungKhac() {
        Intent intent = new Intent(this, UngDungKhacActivity.class);
        startActivity(intent);
    }

    private void checkDrawerOpen() {
        if (drawerLayout.isDrawerOpen(linearLayoutNavigation))
            drawerLayout.closeDrawer(linearLayoutNavigation);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(linearLayoutNavigation))
            drawerLayout.closeDrawer(linearLayoutNavigation);
        else
            super.onBackPressed();
    }
}
