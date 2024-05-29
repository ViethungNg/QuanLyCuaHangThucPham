/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author TIEN SY
 */
public class GGHoaDon {
    private String maGG;
    private Integer soLuong;
    private String ngayBD;
    private String ngayKT;
    private Double mucGG;
    private boolean hinhThuc;
    private Double hoaDonAD;
    private String maNV;

    public GGHoaDon() {
    }

    public GGHoaDon(String maGG, int soLuong, String ngayBD, String ngayKT, Double mucGG, boolean hinhThuc, Double hoaDonAD, String maNV) {
        this.maGG = maGG;
        this.soLuong = soLuong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.mucGG = mucGG;
        this.hinhThuc = hinhThuc;
        this.hoaDonAD = hoaDonAD;
        this.maNV = maNV;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public Double getMucGG() {
        return mucGG;
    }

    public void setMucGG(Double mucGG) {
        this.mucGG = mucGG;
    }

    public boolean isHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(boolean hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public Double getHoaDonAD() {
        return hoaDonAD;
    }

    public void setHoaDonAD(Double hoaDonAD) {
        this.hoaDonAD = hoaDonAD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
    
}
