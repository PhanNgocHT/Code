package com.zuckerteam.model;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/5/2017.
 */

public class TenHayCuaBe implements Serializable {
    int id;
    String tenDaiDien;
    String danhSachten;
    String yNghiaTen;

    public TenHayCuaBe() {
    }

    public TenHayCuaBe(int id, String tenDaiDien, String danhSachten, String yNghiaTen) {
        this.id=id;
        this.tenDaiDien = tenDaiDien;
        this.danhSachten = danhSachten;
        this.yNghiaTen = yNghiaTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDaiDien() {
        return tenDaiDien;
    }

    public void setTenDaiDien(String tenDaiDien) {
        this.tenDaiDien = tenDaiDien;
    }

    public String getDanhSachten() {
        return danhSachten;
    }

    public void setDanhSachten(String danhSachten) {
        this.danhSachten = danhSachten;
    }

    public String getyNghiaTen() {
        return yNghiaTen;
    }

    public void setyNghiaTen(String yNghiaTen) {
        this.yNghiaTen = yNghiaTen;
    }

    @Override
    public String toString() {
        return id + "\t"+tenDaiDien+"\t"+danhSachten+"\t"+yNghiaTen+"\n";
    }
}
