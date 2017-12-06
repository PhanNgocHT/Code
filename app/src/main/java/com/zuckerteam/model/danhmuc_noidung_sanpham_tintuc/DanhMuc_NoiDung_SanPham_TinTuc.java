package com.zuckerteam.model.danhmuc_noidung_sanpham_tintuc;

import java.io.Serializable;

/**
 * Created by Dung Ali on 8/1/2017.
 */
/** Lớp dùng chung cho các item chuyên mục Danh mục , nội dung , sản phẩm và tin tức*/
public class DanhMuc_NoiDung_SanPham_TinTuc implements Serializable {

    private int anhDanhMuc;
    private String tenDanhMuc;

    public DanhMuc_NoiDung_SanPham_TinTuc() {
    }

    public DanhMuc_NoiDung_SanPham_TinTuc(int anhDanhMuc, String tenDanhMuc) {
        this.anhDanhMuc = anhDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getAnhDanhMuc() {
        return anhDanhMuc;
    }

    public void setAnhDanhMuc(int anhDanhMuc) {
        this.anhDanhMuc = anhDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public String toString() {
        return tenDanhMuc;
    }
}
