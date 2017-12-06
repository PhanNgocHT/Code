package com.zuckerteam.model.thaiky;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/11/2017.
 */

public class GiaiDoanBienChungTrongThaiKy implements Serializable {
    int id;
    String tenGiaiDoan;
    String tenBienChung;

    public GiaiDoanBienChungTrongThaiKy() {
    }

    public GiaiDoanBienChungTrongThaiKy(int id, String tenGiaiDoan, String tenBienChung) {
        this.id = id;
        this.tenGiaiDoan = tenGiaiDoan;
        this.tenBienChung = tenBienChung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGiaiDoan() {
        return tenGiaiDoan;
    }

    public void setTenGiaiDoan(String tenGiaiDoan) {
        this.tenGiaiDoan = tenGiaiDoan;
    }

    public String getTenBienChung() {
        return tenBienChung;
    }

    public void setTenBienChung(String tenBienChung) {
        this.tenBienChung = tenBienChung;
    }
}
