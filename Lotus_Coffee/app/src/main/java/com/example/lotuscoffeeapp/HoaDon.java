package com.example.lotuscoffeeapp;

public class HoaDon {
    private String MaBan;
    private String NgayLap;
    private String Tien;

    public HoaDon() {
    }

    public HoaDon(String maBan, String ngayLap, String tien) {

        MaBan = maBan;
        NgayLap = ngayLap;
        Tien = tien;
    }


    public String getMaBan() {
        return MaBan;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public String getTien() {
        return Tien;
    }


    public void setMaBan(String maBan) {
        MaBan = maBan;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public void setTien(String tien) {
        Tien = tien;
    }
    public String toString ()
    {
        return ( getMaBan() + getNgayLap() +getTien() );
    }

}
