package com.zuckerteam.model;

import java.io.Serializable;

/**
 * Created by Dung Ali on 9/16/2017.
 */

public class KeChuyenChoBe implements Serializable {
    private int id;
    private String tenChuyen;
    private String noiDungChuyen;
    private String baiHocCuaChuyen;

    public KeChuyenChoBe() {
    }

    public KeChuyenChoBe(int id, String tenChuyen, String noiDungChuyen, String baiHocCuaChuyen) {
        this.id = id;
        this.tenChuyen = tenChuyen;
        this.noiDungChuyen = noiDungChuyen;
        this.baiHocCuaChuyen = baiHocCuaChuyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public String getNoiDungChuyen() {
        return noiDungChuyen;
    }

    public void setNoiDungChuyen(String noiDungChuyen) {
        this.noiDungChuyen = noiDungChuyen;
    }

    public String getBaiHocCuaChuyen() {
        return baiHocCuaChuyen;
    }

    public void setBaiHocCuaChuyen(String baiHocCuaChuyen) {
        this.baiHocCuaChuyen = baiHocCuaChuyen;
    }
}
