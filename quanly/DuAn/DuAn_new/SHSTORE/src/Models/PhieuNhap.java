/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAO.CTPhieuNhapDAO;
import java.util.List;

/**
 *
 * @author TIEN SY
 */
public class PhieuNhap {
        private String maPhieuNhap;
        private String ngayNhap;
        private String ghiChu;
        private String maNV;
        private String maNCC;
        private String maKho;
        private String tinhTrang;

    public PhieuNhap(String maPhieuNhap, String ngayNhap, String ghiChu, String maNV, String maNCC, String maKho, String tinhTrang) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.maKho = maKho;
        this.tinhTrang = tinhTrang;
    }

    public PhieuNhap() {
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    public Double getTongTien(){
        CTPhieuNhapDAO CTpn = new CTPhieuNhapDAO();
            Double tongTien = 0.0;
       List<CTPhieuNhap> listCTPN =  CTpn.selectCTPNbyMaPN(maPhieuNhap);
       for(CTPhieuNhap ct: listCTPN){
           tongTien += ct.getTongTien();
       }
       return tongTien;
    }
    
        
}
