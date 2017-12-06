package com.zuckerteam.model.thaiky;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/10/2017.
 */

public class BieuDoThaiKy implements Serializable {
    private int id;
    private String tamCaNguyet;
    private String tuanThaiKy;
    private String theTrangThaiPhu;
    private String tamLyThaiPhu;
    private String tinhTrangThaiNhi;
    private String loiKhuyenTrongTuan;
    private byte[] anhThaiNhi;

    public BieuDoThaiKy() {
    }

    public BieuDoThaiKy(int id, String tamCaNguyet,
                        String tuanThaiKy,
                        String theTrangThaiPhu,
                        String tamLyThaiPhu,
                        String tinhTrangThaiNhi,
                        String loiKhuyenTrongTuan,
                        byte[] anhThaiNhi) {
        this.id = id;
        this.tamCaNguyet = tamCaNguyet;
        this.tuanThaiKy = tuanThaiKy;
        this.theTrangThaiPhu = theTrangThaiPhu;
        this.tamLyThaiPhu = tamLyThaiPhu;
        this.tinhTrangThaiNhi = tinhTrangThaiNhi;
        this.loiKhuyenTrongTuan = loiKhuyenTrongTuan;
        this.anhThaiNhi = anhThaiNhi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTamCaNguyet() {
        return tamCaNguyet;
    }

    public void setTamCaNguyet(String tamCaNguyet) {
        this.tamCaNguyet = tamCaNguyet;
    }

    public String getTuanThaiKy() {
        return tuanThaiKy;
    }

    public void setTuanThaiKy(String tuanThaiKy) {
        this.tuanThaiKy = tuanThaiKy;
    }

    public String getTheTrangThaiPhu() {
        return theTrangThaiPhu;
    }

    public void setTheTrangThaiPhu(String theTrangThaiPhu) {
        this.theTrangThaiPhu = theTrangThaiPhu;
    }

    public String getTamLyThaiPhu() {
        return tamLyThaiPhu;
    }

    public void setTamLyThaiPhu(String tamLyThaiPhu) {
        this.tamLyThaiPhu = tamLyThaiPhu;
    }

    public String getTinhTrangThaiNhi() {
        return tinhTrangThaiNhi;
    }

    public void setTinhTrangThaiNhi(String tinhTrangThaiNhi) {
        this.tinhTrangThaiNhi = tinhTrangThaiNhi;
    }

    public String getLoiKhuyenTrongTuan() {
        return loiKhuyenTrongTuan;
    }

    public void setLoiKhuyenTrongTuan(String loiKhuyenTrongTuan) {
        this.loiKhuyenTrongTuan = loiKhuyenTrongTuan;
    }

    public byte[] getAnhThaiNhi() {
        return anhThaiNhi;
    }

    public void setAnhThaiNhi(byte[] anhThaiNhi) {
        this.anhThaiNhi = anhThaiNhi;
    }
}
