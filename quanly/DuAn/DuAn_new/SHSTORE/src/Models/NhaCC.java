/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


public class NhaCC {
    private String maNCC;
    private String tenNCC;
    private String sdt;
    private String diaChi;
    private boolean trangThai;

    public NhaCC(String maNCC, String tenNCC, String sdt, String diaChi, boolean trangThai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public NhaCC() {
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenNCC;
    }
    
    
}
