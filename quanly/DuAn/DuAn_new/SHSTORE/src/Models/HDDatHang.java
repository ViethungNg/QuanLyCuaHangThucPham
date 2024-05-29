/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAO.CTHDDatHangDAO;
import java.util.List;

/**
 *
 * @author TIEN SY
 */
public class HDDatHang {

    private String maHD;
    private int trangThai;
    private String ngayDat;
    private String diaChiGH;
    private String maGG;
    private String sdtKH;
    private String maNV;
    private boolean hinhThucTT;
    private Double tienKM;
    private int diemSD;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getDiaChiGH() {
        return diaChiGH;
    }

    public void setDiaChiGH(String diaChiGH) {
        this.diaChiGH = diaChiGH;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public boolean isHinhThucTT() {
        return hinhThucTT;
    }

    public void setHinhThucTT(boolean hinhThucTT) {
        this.hinhThucTT = hinhThucTT;
    }

    public Double getTienKM() {
        return tienKM;
    }

    public void setTienKM(Double tienKM) {
        this.tienKM = tienKM;
    }

    public int getDiemSD() {
        return diemSD;
    }

    public void setDiemSD(int diemSD) {
        this.diemSD = diemSD;
    }

    public Double getTongTien() {
        CTHDDatHangDAO ctDAO = new CTHDDatHangDAO();
        Double TongTien = 0.0;
        List<CTHDDatHang> listCT = ctDAO.selectByMaHD(maHD);
        for (CTHDDatHang ct : listCT) {
            TongTien += ct.getTongTien();
        }

        return TongTien;

    }

}
