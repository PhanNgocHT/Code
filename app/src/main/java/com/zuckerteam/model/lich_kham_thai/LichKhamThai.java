package com.zuckerteam.model.lich_kham_thai;

import java.io.Serializable;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class LichKhamThai implements Serializable {
    int id;
    String lanKham;
    String tuanKham;
    String noiDungKham;

    public LichKhamThai() {

    }

    public LichKhamThai(int id, String lanKham, String tuanKham, String noiDungKham) {
        this.id = id;
        this.lanKham = lanKham;
        this.tuanKham = tuanKham;
        this.noiDungKham = noiDungKham;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanKham() {
        return lanKham;
    }

    public void setLanKham(String lanKham) {
        this.lanKham = lanKham;
    }

    public String getTuanKham() {
        return tuanKham;
    }

    public void setTuanKham(String tuanKham) {
        this.tuanKham = tuanKham;
    }

    public String getNoiDungKham() {
        return noiDungKham;
    }

    public void setNoiDungKham(String noiDungKham) {
        this.noiDungKham = noiDungKham;
    }
}
