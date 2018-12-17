package com.example.lotuscoffeeapp;

import java.io.Serializable;

public class ThucDon implements Serializable {
    private int MaMon;
    private String TenMon;
    private String Gia;
    private int HinhAnh;
    private int MaLoai;

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaMon() {

        return MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public String getGia() {
        return Gia;
    }

    public int getHinhAnh() {
        return HinhAnh;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {

        MaLoai = maLoai;
    }

    @Override
    public String toString() {
        return TenMon;
    }
}
