package com.zuckerteam.model.sotaytiemphong;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/10/2017.
 */

public class BenhTiemPhong implements Serializable {
    int id;
    String tenBenhTiemPhong;
    String moTaBenhTiemPhong;

    public BenhTiemPhong() {
    }

    public BenhTiemPhong(int id, String tenBenhTiemPhong, String moTaBenhTiemPhong) {
        this.id = id;
        this.tenBenhTiemPhong = tenBenhTiemPhong;
        this.moTaBenhTiemPhong = moTaBenhTiemPhong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBenhTiemPhong() {
        return tenBenhTiemPhong;
    }

    public void setTenBenhTiemPhong(String tenBenhTiemPhong) {
        this.tenBenhTiemPhong = tenBenhTiemPhong;
    }

    public String getMoTaBenhTiemPhong() {
        return moTaBenhTiemPhong;
    }

    public void setMoTaBenhTiemPhong(String moTaBenhTiemPhong) {
        this.moTaBenhTiemPhong = moTaBenhTiemPhong;
    }
}
