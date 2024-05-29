/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAO.GGSanPhamDAO;
import java.io.File;

/**
 *
 * @author TIEN SY
 */
public class SanPham {

    private String maSP;
    private String tenSP;
    private String maKe;
    private byte[] Hinh;
    private Double giaBan;
    private Double giaNhap;
    private int slTrenKe;
    private String ngaySX;
    private String ngayHH;
    private String donVi;
    private int SLTonKho;
    private String qrCode;
    private String maNCC;
    private String maLoai;
    private boolean trangThai;

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public SanPham() {
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] Hinh) {
        this.Hinh = Hinh;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSlTrenKe() {
        return slTrenKe;
    }

    public void setSlTrenKe(int slTrenKe) {
        this.slTrenKe = slTrenKe;
    }

    public String getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(String ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(String ngayHH) {
        this.ngayHH = ngayHH;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSLTonKho() {
        return SLTonKho;
    }

    public void setSLTonKho(int SLTonKho) {
        this.SLTonKho = SLTonKho;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "( "+maSP +" )"+tenSP;
    }

    public Double getGiaGiam() {
        GGSanPhamDAO ggSP = new GGSanPhamDAO();
        GGSanPham gg = ggSP.selectByCheckHieuLuc(this.maSP);
        if (gg == null) {
            return this.giaBan;
        } else {
            return this.giaBan - gg.getGiaGiam();
        }

    }

}
