package com.zuckerteam.model;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/1/2017.
 */

/**
 * Lớp dùng chung cho các item chuyên mục
 * Dinh dưỡng bà bầu ,
 * Kinh nghiệm đi đẻ ,
 * Dạy con ,
 * Chăm sóc trẻ
 * Góc hài hước
 */
public class ChiTietTinTucMeVaBe implements Serializable {
    private String duongDanTinTuc;
    private String anhTinTuc;
    private String tieuDeTinTuc;
    private String moTaTinTuc;

    public ChiTietTinTucMeVaBe() {
    }

    public ChiTietTinTucMeVaBe(String duongDanTinTuc, String anhTinTuc, String tieuDeTinTuc, String moTaTinTuc) {
        this.duongDanTinTuc = duongDanTinTuc;
        this.anhTinTuc = anhTinTuc;
        this.tieuDeTinTuc = tieuDeTinTuc;
        this.moTaTinTuc = moTaTinTuc;
    }

    public String getDuongDanTinTuc() {
        return duongDanTinTuc;
    }
    public void setDuongDanTinTuc(String duongDanTinTuc) {
        this.duongDanTinTuc = duongDanTinTuc;
    }

    public String getAnhTinTuc() {
        return anhTinTuc;
    }

    public void setAnhTinTuc(String anhTinTuc) {
        this.anhTinTuc = anhTinTuc;
    }

    public String getTieuDeTinTuc() {
        return tieuDeTinTuc;
    }

    public void setTieuDeTinTuc(String tieuDeTinTuc) {
        this.tieuDeTinTuc = tieuDeTinTuc;
    }

    public String getMoTaTinTuc() {
        return moTaTinTuc;
    }

    public void setMoTaTinTuc(String moTaTinTuc) {
        this.moTaTinTuc = moTaTinTuc;
    }

    @Override
    public String toString() {
        return tieuDeTinTuc;
    }
}
