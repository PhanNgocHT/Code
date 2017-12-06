package com.zuckerteam.model.thaiky;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/10/2017.
 */

public class BienChungTrongThaiKy implements Serializable {

    private int id;
    private String tenBienChung;
    private String noiDungBienChung;

    public BienChungTrongThaiKy() {
    }

    public BienChungTrongThaiKy(int id, String tenBienChung, String noiDungBienChung) {
        this.id = id;
        this.tenBienChung = tenBienChung;
        this.noiDungBienChung = noiDungBienChung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBienChung() {
        return tenBienChung;
    }

    public void setTenBienChung(String tenBienChung) {
        this.tenBienChung = tenBienChung;
    }

    public String getNoiDungBienChung() {
        return noiDungBienChung;
    }

    public void setNoiDungBienChung(String noiDungBienChung) {
        this.noiDungBienChung = noiDungBienChung;
    }
}
