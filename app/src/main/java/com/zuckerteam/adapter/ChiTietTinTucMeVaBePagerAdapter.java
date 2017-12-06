package com.zuckerteam.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zuckerteam.Constant;
import com.zuckerteam.fragment.muasam.FragmentDoDungChoBaBau;
import com.zuckerteam.fragment.muasam.FragmentDoDungSauSinh;
import com.zuckerteam.fragment.sotaytiemphong.FragmentLichTiemPhong;
import com.zuckerteam.fragment.sotaytiemphong.FragmentTheoDoiTre;
import com.zuckerteam.fragment.dattenchobe.FragmentTenBeGai;
import com.zuckerteam.fragment.dattenchobe.FragmentTenBeTrai;
import com.zuckerteam.fragment.thaiky.FragmentDienBienThaiKy;
import com.zuckerteam.fragment.thaiky.FragmentLoiKhuyenTrongTuan;
import com.zuckerteam.fragment.tintucmevabe.chamsoctre.FragmentChamSocTreSoSinh;
import com.zuckerteam.fragment.tintucmevabe.chamsoctre.FragmentChoConAnDam;
import com.zuckerteam.fragment.tintucmevabe.chamsoctre.FragmentTangChieuCaoChoTre;
import com.zuckerteam.fragment.tintucmevabe.daycon.FragmentChiaSeKinhNghiem;
import com.zuckerteam.fragment.tintucmevabe.daycon.FragmentDayConThongMinh;
import com.zuckerteam.fragment.tintucmevabe.daycon.FragmentSaiLamCanTranh;
import com.zuckerteam.fragment.tintucmevabe.dinhduongbabau.FragmentLuuYDinhDuong;
import com.zuckerteam.fragment.tintucmevabe.dinhduongbabau.FragmentThucPhamNenTranh;
import com.zuckerteam.fragment.tintucmevabe.dinhduongbabau.FragmentThucPhamTotNenAn;
import com.zuckerteam.fragment.tintucmevabe.gochaihuoc.FragmentAnhDepCuaBe;
import com.zuckerteam.fragment.tintucmevabe.gochaihuoc.FragmentNgoNghinhTreTho;

import java.util.ArrayList;


public class ChiTietTinTucMeVaBePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> arrPager;
    private String tenChuyenMuc;

    public ChiTietTinTucMeVaBePagerAdapter(FragmentManager fm, ArrayList<String> arrPager, String tenChuyenMuc) {
        super(fm);
        this.arrPager = arrPager;
        this.tenChuyenMuc = tenChuyenMuc;
    }

    @Override
    public Fragment getItem(int position) {
        if (tenChuyenMuc.equals(Constant.chuyenMucDinhDuongBaBau)) {
            return handleChuyenMucDinhDuongBaBau(position);
        } else if (tenChuyenMuc.equals(Constant.chuyenMucChamSocTre)) {
            return handleChuyenMucChamSocTre(position);
        } else if (tenChuyenMuc.equals(Constant.chuyenMucDayCon)) {
            return handleChuyenMucDayCon(position);
        } else if (tenChuyenMuc.equals(Constant.chuyenMucGocHaiHuoc)) {
            return handleChuyenMucGocHaiHuoc(position);
        } else if (tenChuyenMuc.equals(Constant.chuyenMucTenHayCuaBe)) {
            return handleChuyenMucTenHayCuaBe(position);
        }else if(tenChuyenMuc.equals(Constant.chuyenMucBieuDoThaiKy)){
            return handleChuyenMucBieuDoThaiKy(position);
        }else if(tenChuyenMuc.equals(Constant.chuyenMucSoTayTiemPhong)){
            return handleChuyenMucSoTayTiemPhong(position);
        }else if(tenChuyenMuc.equals(Constant.chuyenMucMuaSam)){
            return handleChuyenMucMuaSam(position);
        }
        return null;
    }

    private Fragment handleChuyenMucMuaSam(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentDoDungChoBaBau();
                break;
            case 1:
                fragment = new FragmentDoDungSauSinh();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucSoTayTiemPhong(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentTheoDoiTre();
                break;
            case 1:
                fragment = new FragmentLichTiemPhong();
                break;
            default:
                break;
        }
        return fragment;
    }


    private Fragment handleChuyenMucDinhDuongBaBau(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentThucPhamTotNenAn();
                break;
            case 1:
                fragment = new FragmentThucPhamNenTranh();
                break;
            case 2:
                fragment = new FragmentLuuYDinhDuong();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucChamSocTre(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentChamSocTreSoSinh();
                break;
            case 1:
                fragment = new FragmentChoConAnDam();
                break;
            case 2:
                fragment = new FragmentTangChieuCaoChoTre();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucDayCon(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentDayConThongMinh();
                break;
            case 1:
                fragment = new FragmentChiaSeKinhNghiem();
                break;
            case 2:
                fragment = new FragmentSaiLamCanTranh();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucGocHaiHuoc(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentAnhDepCuaBe();
                break;
            case 1:
                fragment = new FragmentNgoNghinhTreTho();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucTenHayCuaBe(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentTenBeTrai();
                break;
            case 1:
                fragment = new FragmentTenBeGai();
                break;
            default:
                break;
        }
        return fragment;
    }

    private Fragment handleChuyenMucBieuDoThaiKy(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentDienBienThaiKy();
                break;
            case 1:
                fragment = new FragmentLoiKhuyenTrongTuan();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return arrPager.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrPager.get(position);
    }
}
