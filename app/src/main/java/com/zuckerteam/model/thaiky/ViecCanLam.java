package com.zuckerteam.model.thaiky;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/10/2017.
 */

public class ViecCanLam implements Serializable {
    private int id;
    private String tenViecCanLam;
    private String noiDungViecCanLam;

    public ViecCanLam() {
    }

    public ViecCanLam(int id, String tenViecCanLam, String noiDungViecCanLam) {
        this.id = id;
        this.tenViecCanLam = tenViecCanLam;
        this.noiDungViecCanLam = noiDungViecCanLam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenViecCanLam() {
        return tenViecCanLam;
    }

    public void setTenViecCanLam(String tenViecCanLam) {
        this.tenViecCanLam = tenViecCanLam;
    }

    public String getNoiDungViecCanLam() {
        return noiDungViecCanLam;
    }

    public void setNoiDungViecCanLam(String noiDungViecCanLam) {
        this.noiDungViecCanLam = noiDungViecCanLam;
    }
}
