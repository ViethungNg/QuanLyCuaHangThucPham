/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.SanPham;
import Utils.JDBCHelper;
import Utils.XDate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author TIEN SY
 */
public class SanPhamDAO extends CuaHangDAO<SanPham, String> {

    public static List<SanPham> listSP;

    @Override
    public void insert(SanPham entity) {
        try {

            JDBCHelper.update("EXEC SP_THEMSP ?,?,?,?,?,?,?,?,?", entity.getMaSP(), entity.getTenSP(), entity.getHinh(), entity.getGiaNhap(), entity.getGiaBan(), entity.getQrCode(), entity.getDonVi(), entity.getMaLoai(), entity.getMaNCC());
        } catch (Exception ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(SanPham entity) {

        JDBCHelper.update("UPDATE  SANPHAM SET TENSP = ?, HINH = ?,GIABAN = ?, GIANHAP = ?,SLTRENKE = ?,"
                + "SLTONKHO = ?,QRCODE = ?, DONVI = ?,MALOAI = ?, TRANGTHAI = ?, MANCC = ? where masp = ?",
                entity.getTenSP(), entity.getHinh(), entity.getGiaBan(), entity.getGiaNhap(), entity.getSlTrenKe(), entity.getSLTonKho(), entity.getQrCode(), entity.getDonVi(), entity.getMaLoai(), entity.isTrangThai(), entity.getMaNCC(), entity.getMaSP());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FROM SANPHAM WHERE MASP = ?", id);
    }

    @Override
    public SanPham selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM where Masp   = ?", id);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));
                return sp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SanPham> selectAll() {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM");
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));
                listSP.add(sp);

            }
            return listSP;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<SanPham> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<SanPham> selectSPbyNCC(String maNCC) {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM WHERE MANCC = ?", maNCC);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));
                listSP.add(sp);

            }
            return listSP;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // dùng để cập nhật lại sản phẩm khi thêm lên kệ

    public List<SanPham> selectSPbyLoaiSP(String maLoai) {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM WHERE MALOAI = ?", maLoai);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                listSP.add(sp);

            }
            return listSP;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SanPham> getListSP() {
        return listSP;
    }

    public void setListSP(List<SanPham> list) {
        listSP = list;
    }

    public List<SanPham> getSanPhamDangKinhDoanh(int i) {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM where TRANGTHAI = ?", i);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));
                listSP.add(sp);

            }
            return listSP;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SanPham selectbyBarcode(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM where QRCODE   = ?", id);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinh(rs.getBytes(3));
                sp.setGiaBan(rs.getDouble(4));
                sp.setGiaNhap(rs.getDouble(5));
                sp.setSlTrenKe(rs.getInt(6));
                sp.setSLTonKho(rs.getInt(7));
                sp.setQrCode(rs.getString(8));
                sp.setDonVi(rs.getString(9));
                sp.setMaKe(rs.getString(10));
                sp.setMaLoai(rs.getString(11));
                sp.setMaNCC(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));
                return sp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
