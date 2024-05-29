/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author TIEN SY
 */
public class PhieuNhapKho {
    private String maPN;
    private String maNCC;
    private String maKho;
    private String maNV;
    private String ngayNhap;
    private String ghiChu;
    private String maSP;
    private Double giaNhap;
    private int SL;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String maPN, String maNCC, String maKho, String maNV, String ngayNhap, String ghiChu, String maSP, Double giaNhap, int SL) {
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.maKho = maKho;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
        this.maSP = maSP;
        this.giaNhap = giaNhap;
        this.SL = SL;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }
    
}
