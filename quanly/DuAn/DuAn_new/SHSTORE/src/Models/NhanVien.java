package Models;

import java.io.File;

public class NhanVien {

    private String maNV;
    private String hoTen;
    private boolean gioiTinh;
    private String ngaySinh;
    private String sdt;
    private String email;
    private byte[] hinh;
    private String maCV;
    private String diaChi;
    private String ngayVaoLam;
    private String matKhau;
    private boolean TTLamViec;

    public NhanVien() {
    }

    public boolean getTTLamViec() {
        return TTLamViec;
    }

    public void setTTLamViec(boolean TTLamViec) {
        this.TTLamViec = TTLamViec;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenCV() {
        if (maCV.equals("1")) {
            return "Chủ cửa hàng";
        } else if (maCV.equals("2")) {
            return "Quản lý";
        } else if (maCV.equals("3")) {
            return "Nhân viên kho";
        } else if (maCV.equals("4")) {
            return "Nhân viên bán hàng";
        } else if (maCV.equals("5")) {
            return "Kế toán";
        }
        return null;
    }

    @Override
    public String toString() {
        return getTenCV();
    }

}
