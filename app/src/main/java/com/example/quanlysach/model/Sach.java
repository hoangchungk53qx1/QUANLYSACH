package com.example.quanlysach.model;

import java.io.Serializable;
import java.util.Date;

public class Sach implements Serializable {
    private String maSach;
    private String tenSach;
    private Date    ngayxb;
    private String maLS;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayxb() {
        return ngayxb;
    }

    public void setNgayxb(Date ngayxb) {
        this.ngayxb = ngayxb;
    }

    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    public Sach() {
    }

    public Sach(String maSach, String tenSach, Date ngayxb, String maLS) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayxb = ngayxb;
        this.maLS = maLS;
    }
}
