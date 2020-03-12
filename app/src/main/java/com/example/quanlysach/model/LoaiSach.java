package com.example.quanlysach.model;

import androidx.annotation.NonNull;

public class LoaiSach {
    private String maLoai;
    private String mota;

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LoaiSach() {
    }

    public LoaiSach(String maLoai, String mota) {
        this.maLoai = maLoai;
        this.mota = mota;
    }

    @NonNull
    @Override
    public String toString() {
        return maLoai;
    }
}
