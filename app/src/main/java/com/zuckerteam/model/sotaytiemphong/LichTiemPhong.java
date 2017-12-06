package com.zuckerteam.model.sotaytiemphong;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/8/2017.
 */

public class LichTiemPhong implements Serializable {

    int id;
    String thoiGianTiemPhong;
    String tenBenhTiemPhong;

    public LichTiemPhong() {
    }

    public LichTiemPhong(int id, String thoiGianTiemPhong, String tenBenhTiemPhong) {
        this.id = id;
        this.thoiGianTiemPhong = thoiGianTiemPhong;
        this.tenBenhTiemPhong = tenBenhTiemPhong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThoiGianTiemPhong() {
        return thoiGianTiemPhong;
    }

    public void setThoiGianTiemPhong(String thoiGianTiemPhong) {
        this.thoiGianTiemPhong = thoiGianTiemPhong;
    }

    public String getTenBenhTiemPhong() {
        return tenBenhTiemPhong;
    }

    public void setTenBenhTiemPhong(String tenBenhTiemPhong) {
        this.tenBenhTiemPhong = tenBenhTiemPhong;
    }
}
