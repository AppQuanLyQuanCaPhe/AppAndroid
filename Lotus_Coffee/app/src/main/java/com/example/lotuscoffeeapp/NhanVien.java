package com.example.lotuscoffeeapp;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int MaNV;
    private String HoTen;
    private String SDT;
    private String NgaySinh;
    private String DiaChi;
    private String Email;

    public int getMaNV() {
        return MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }
}
