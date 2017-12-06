package com.zuckerteam.model.sotaytiemphong;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/29/2017.
 */

public class TreTiemPhong implements Serializable {
    int id;
    String tenTre;
    int anhDaiDienTre;
    int ngaySinhTre;
    int thangSinhTre;
    int namSinhTre;
    String gioiTinhTre;

    public TreTiemPhong() {
    }

    public TreTiemPhong(int id,String tenTre, int anhDaiDienTre, int ngaySinhTre, int thangSinhTre, int namSinhTre, String gioiTinhTre) {
        this.id=id;
        this.tenTre = tenTre;
        this.anhDaiDienTre = anhDaiDienTre;
        this.ngaySinhTre = ngaySinhTre;
        this.thangSinhTre = thangSinhTre;
        this.namSinhTre = namSinhTre;
        this.gioiTinhTre = gioiTinhTre;
    }

    public TreTiemPhong(String tenTre, int ngaySinhTre, int thangSinhTre, int namSinhTre, String gioiTinhTre) {
        this.tenTre = tenTre;
        this.ngaySinhTre = ngaySinhTre;
        this.thangSinhTre = thangSinhTre;
        this.namSinhTre = namSinhTre;
        this.gioiTinhTre = gioiTinhTre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTre() {
        return tenTre;
    }

    public void setTenTre(String ten) {
        this.tenTre = ten;
    }

    public int getAnhDaiDienTre() {
        return anhDaiDienTre;
    }

    public void setAnhDaiDienTre(int anhDaiDienTre) {
        this.anhDaiDienTre = anhDaiDienTre;
    }

    public int getNgaySinhTre() {
        return ngaySinhTre;
    }

    public void setNgaySinhTre(int ngaySinhTre) {
        this.ngaySinhTre = ngaySinhTre;
    }

    public int getThangSinhTre() {
        return thangSinhTre;
    }

    public void setThangSinhTre(int thangSinhTre) {
        this.thangSinhTre = thangSinhTre;
    }

    public int getNamSinhTre() {
        return namSinhTre;
    }

    public void setNamSinhTre(int namSinhTre) {
        this.namSinhTre = namSinhTre;
    }

    public String getGioiTinhTre() {
        return gioiTinhTre;
    }

    public void setGioiTinhTre(String gioiTinhTre) {
        this.gioiTinhTre = gioiTinhTre;
    }
}
