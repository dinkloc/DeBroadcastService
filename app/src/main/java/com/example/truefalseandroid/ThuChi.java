package com.example.truefalseandroid;

import java.io.Serializable;

public class ThuChi implements Comparable<ThuChi>, Serializable {
    private int id;
    private String tenKhoan;
    private String ngayThang;
    private String soTien;
    private int thuChi;


    public ThuChi() {
    }

    public ThuChi(int id, String tenKhoan, String ngayThang, String soTien, int thuChi) {
        this.id = id;
        this.tenKhoan = tenKhoan;
        this.ngayThang = ngayThang;
        this.soTien = soTien;
        this.thuChi = thuChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public int getThuChi() {
        return thuChi;
    }

    public void setThuChi(int thuChi) {
        this.thuChi = thuChi;
    }

    @Override
    public int compareTo(ThuChi o) {
        return this.soTien.compareTo(o.getSoTien());
    }

}
