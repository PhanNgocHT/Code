package com.zuckerteam.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/24/2017.
 */

public class TinTucYeuThich implements Serializable {
    private Integer id;
    private String tieuDeTinTuc;
    private String anhTinTuc;
    private String moTaTinTuc;
    private String thanTinTucHtml;
    private String duongDanTinTuc;

    public TinTucYeuThich() {
    }

    public TinTucYeuThich(String tieuDeTinTuc, String anhTinTuc, String moTaTinTuc, String thanTinTucHtml, String duongDanTinTuc) {
        this.tieuDeTinTuc = tieuDeTinTuc;
        this.anhTinTuc = anhTinTuc;
        this.moTaTinTuc = moTaTinTuc;
        this.thanTinTucHtml = thanTinTucHtml;
        this.duongDanTinTuc = duongDanTinTuc;
    }

    public TinTucYeuThich(Integer id, String tieuDeTinTuc, String anhTinTuc, String moTaTinTuc, String thanTinTucHtml, String duongDanTinTuc) {
        this.id = id;
        this.tieuDeTinTuc = tieuDeTinTuc;
        this.anhTinTuc = anhTinTuc;
        this.moTaTinTuc = moTaTinTuc;
        this.thanTinTucHtml = thanTinTucHtml;
        this.duongDanTinTuc=duongDanTinTuc;
    }

    public String getDuongDanTinTuc() {
        return duongDanTinTuc;
    }

    public void setDuongDanTinTuc(String duongDanTinTuc) {
        this.duongDanTinTuc = duongDanTinTuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDeTinTuc() {
        return tieuDeTinTuc;
    }

    public void setTieuDeTinTuc(String tieuDeTinTuc) {
        this.tieuDeTinTuc = tieuDeTinTuc;
    }

    public String getAnhTinTuc() {
        return anhTinTuc;
    }

    public void setAnhTinTuc(String anhTinTuc) {
        this.anhTinTuc = anhTinTuc;
    }

    public String getMoTaTinTuc() {
        return moTaTinTuc;
    }

    public void setMoTaTinTuc(String moTaTinTuc) {
        this.moTaTinTuc = moTaTinTuc;
    }

    public String getThanTinTucHtml() {
        return thanTinTucHtml;
    }

    public void setThanTinTucHtml(String thanTinTucHtml) {
        this.thanTinTucHtml = thanTinTucHtml;
    }
}
