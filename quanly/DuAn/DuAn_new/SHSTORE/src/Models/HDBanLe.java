/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Utils.JDBCHelper;

/**
 *
 * @author TIEN SY
 */
public class HDBanLe {
    private String maHD;
    private String ngayLap;
    private boolean trangThai;
    private String sdtKH;
    private String maGG;
    private String maNV;
    private int diemSD;
    private double tienKhuyenMai;

    public Double getTienKhuyenMai() {
        return tienKhuyenMai;
    }

    public void setTienKhuyenMai(Double tienKhuyenMai) {
        this.tienKhuyenMai = tienKhuyenMai;
    }

    public int getDiemSD() {
        return diemSD;
    }

    public void setDiemSD(int diemSD) {
        this.diemSD = diemSD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
 
}
