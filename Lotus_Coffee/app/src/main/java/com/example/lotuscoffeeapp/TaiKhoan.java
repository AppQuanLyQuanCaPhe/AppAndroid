package com.example.lotuscoffeeapp;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String tendangnhap;
    private String matkhau;
    private String machucvu;
    private int manv;

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setMachucvu(String machucvu) {
        this.machucvu = machucvu;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getMachucvu() {
        return machucvu;
    }

    public int getManv() {
        return manv;
    }
}
