package com.zuckerteam.model.muasam;

import java.io.Serializable;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class DoDungSauSinh implements Serializable {
    int id;
    String tenDoDung;
    String soLuongDeNghi;
    String congDung;

    public DoDungSauSinh() {
    }

    public DoDungSauSinh(int id, String tenDoDung, String soLuongDeNghi, String congDung) {
        this.id = id;
        this.tenDoDung = tenDoDung;
        this.soLuongDeNghi = soLuongDeNghi;
        this.congDung = congDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDoDung() {
        return tenDoDung;
    }

    public void setTenDoDung(String tenDoDung) {
        this.tenDoDung = tenDoDung;
    }

    public String getSoLuongDeNghi() {
        return soLuongDeNghi;
    }

    public void setSoLuongDeNghi(String soLuongDeNghi) {
        this.soLuongDeNghi = soLuongDeNghi;
    }

    public String getCongDung() {
        return congDung;
    }

    public void setCongDung(String congDung) {
        this.congDung = congDung;
    }
}
