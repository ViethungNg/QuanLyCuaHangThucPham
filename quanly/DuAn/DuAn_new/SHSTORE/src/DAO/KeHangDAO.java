/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.SanPham;
import GUI.MainJF;
import Utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class KeHangDAO {

    public static List<SanPham> listSPKe;

    public List<SanPham> selectAll() {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM where MAKE IN (SELECT MAKE FROM KEHANG) AND TRANGTHAI = 1");
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

    public List<SanPham> selectBySeach(String maSP, String tenSP, Double giaThapNhat, Double giaCaoNhat, String maLoai, String qrCode) {
        List<SanPham> listSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_SELECTSPBYSEARCH ?,?,?,?,?,?", maSP, tenSP, giaThapNhat, giaCaoNhat, maLoai, qrCode);
            while (rs.next()) {
                SanPham sp1 = new SanPham();
                sp1.setMaSP(rs.getString(1));
                sp1.setTenSP(rs.getString(2));
                sp1.setHinh(rs.getBytes(3));
                sp1.setGiaBan(rs.getDouble(4));
                sp1.setGiaNhap(rs.getDouble(5));
                sp1.setSlTrenKe(rs.getInt(6));
                sp1.setSLTonKho(rs.getInt(7));
                sp1.setQrCode(rs.getString(8));
                sp1.setDonVi(rs.getString(9));
                sp1.setMaKe(rs.getString(10));
                sp1.setMaLoai(rs.getString(11));
                sp1.setMaNCC(rs.getString(12));
                sp1.setTrangThai(rs.getBoolean(13));
                listSP.add(sp1);

            }
            return listSP;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SanPham selectBySeachmMaSPorQR(String maSP, String BarCode) {

        try {
            if (maSP == null) {
                ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM WHERE QRCODE = ? AND MAKE IN(SELECT MAKE FROM KEHANG)", BarCode);
                while (rs.next()) {
                    SanPham sp1 = new SanPham();
                    sp1.setMaSP(rs.getString(1));
                    sp1.setTenSP(rs.getString(2));
                    sp1.setHinh(rs.getBytes(3));
                    sp1.setGiaBan(rs.getDouble(4));
                    sp1.setGiaNhap(rs.getDouble(5));
                    sp1.setSlTrenKe(rs.getInt(6));
                    sp1.setSLTonKho(rs.getInt(7));
                    sp1.setQrCode(rs.getString(8));
                    sp1.setDonVi(rs.getString(9));
                    sp1.setMaKe(rs.getString(10));
                    sp1.setMaLoai(rs.getString(11));
                    sp1.setMaNCC(rs.getString(12));
                    sp1.setTrangThai(rs.getBoolean(13));
                    return sp1;
                }
            } else if (BarCode == null) {
                ResultSet rs = JDBCHelper.query("SELECT * FROM SANPHAM WHERE MASP = ? AND MAKE IN(SELECT MAKE FROM KEHANG)", maSP);
                while (rs.next()) {
                    SanPham sp1 = new SanPham();
                    sp1.setMaSP(rs.getString(1));
                    sp1.setTenSP(rs.getString(2));
                    sp1.setHinh(rs.getBytes(3));
                    sp1.setGiaBan(rs.getDouble(4));
                    sp1.setGiaNhap(rs.getDouble(5));
                    sp1.setSlTrenKe(rs.getInt(6));
                    sp1.setSLTonKho(rs.getInt(7));
                    sp1.setQrCode(rs.getString(8));
                    sp1.setDonVi(rs.getString(9));
                    sp1.setMaKe(rs.getString(10));
                    sp1.setMaLoai(rs.getString(11));
                    sp1.setMaNCC(rs.getString(12));
                    sp1.setTrangThai(rs.getBoolean(13));
                    return sp1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SanPham> getlistSPTrenKe() {
        return listSPKe;
    }

    public void setListSPTrenKe(List<SanPham> list) {
        listSPKe = list;
    }

}
