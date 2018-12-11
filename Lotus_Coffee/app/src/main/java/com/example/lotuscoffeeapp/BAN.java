package com.example.lotuscoffeeapp;

public class BAN {
    private int MaBan;
    private String TenBan;
    private int TrangThai=0;

    public int getMaBan() {
        return MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return TenBan;
    }
}
