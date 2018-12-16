package com.example.lotuscoffeeapp;

public class HoaDon {
    private int MaHD;
    private int MaBan;
    private String NgayLap;
    private int Tien;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maBan, String ngayLap, int tien) {
        MaHD = maHD;
        MaBan = maBan;
        NgayLap = ngayLap;
        Tien = tien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public int getMaBan() {
        return MaBan;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public int getTien() {
        return Tien;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public void setTien(int tien) {
        Tien = tien;
    }

    @Override
    public String toString() {
        return "Tien=" + Tien;
    }
}
