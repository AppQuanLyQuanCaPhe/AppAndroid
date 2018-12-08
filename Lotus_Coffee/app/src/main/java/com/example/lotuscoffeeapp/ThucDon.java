package com.example.lotuscoffeeapp;

public class ThucDon {
    private String TenMon;
    private String Gia;
    private int HinhAnh;

    public ThucDon() {

    }

    public ThucDon(String tenMon, String gia, int hinhAnh) {
        TenMon = tenMon;
        Gia = gia;
        HinhAnh = hinhAnh;
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

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
