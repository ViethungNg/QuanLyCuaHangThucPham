/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTHDDatHang;
import Models.HDDatHang;
import Utils.Auth;
import Utils.Auth_KH;
import Utils.JDBCHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author TIEN SY
 */
public class HDDatHangDAO {

    public String insertHDreturnMaHD(HDDatHang hd) {
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_THANHTOAN_HDDATHANG_RETURNMAHD ?,?,?,?,?,?,?,?", hd.getTrangThai(), hd.getNgayDat(), hd.getDiaChiGH(), hd.getMaGG(), hd.getSdtKH(), hd.isHinhThucTT(), hd.getTienKM(), hd.getDiemSD());
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDDatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<HDDatHang> selectBySearch(String maHD, String ngayBD, String ngayKT, String theoNgay, String tinhTrang, String sdt) {
        List<HDDatHang> listHDDH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_SELECTHDDATHANG ?,?,?,?,?,?", maHD, ngayBD, ngayKT, theoNgay, tinhTrang, sdt);
            while (rs.next()) {
                HDDatHang hdDH = new HDDatHang();
                hdDH.setMaHD(rs.getString(1));
                hdDH.setTrangThai(rs.getInt(2));
                hdDH.setNgayDat(rs.getDate(3) + " " + rs.getTime(3));
                hdDH.setDiaChiGH(rs.getString(4));
                hdDH.setMaGG(rs.getString(5));
                hdDH.setSdtKH(rs.getString(6));
                hdDH.setMaNV(rs.getString(7));
                hdDH.setHinhThucTT(rs.getBoolean(8));
                hdDH.setTienKM(rs.getDouble(9));
                hdDH.setDiemSD(rs.getInt(10));
                listHDDH.add(hdDH);
            }
            return listHDDH;
        } catch (SQLException ex) {
            Logger.getLogger(HDDatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HDDatHang selectByID(String maHD) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM HDDATHANG WHERE MAHDDATHANG = ?", maHD);
            while (rs.next()) {
                HDDatHang hdDH = new HDDatHang();
                hdDH.setMaHD(rs.getString(1));
                hdDH.setTrangThai(rs.getInt(2));
                hdDH.setNgayDat(rs.getDate(3) + " " + rs.getTime(3));
                hdDH.setDiaChiGH(rs.getString(4));
                hdDH.setMaGG(rs.getString(5));
                hdDH.setSdtKH(rs.getString(6));
                hdDH.setMaNV(rs.getString(7));
                hdDH.setHinhThucTT(rs.getBoolean(8));
                hdDH.setTienKM(rs.getDouble(9));
                hdDH.setDiemSD(rs.getInt(10));
                return hdDH;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HDDatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void giaoHang(String maHD, String maNV) {
        JDBCHelper.update("UPDATE HDDATHANG SET TRANGTHAI = ?,MANV =? WHERE MAHDDATHANG = ?", "2", maNV, maHD);
    }
    CTHDDatHangDAO ctDAO = new CTHDDatHangDAO();

    public void HuyHD(String maHD, String maNV) {
        JDBCHelper.update("UPDATE HDDATHANG SET TRANGTHAI = ?,MANV =? WHERE MAHDDATHANG = ?", "0", maNV, maHD);
        List<CTHDDatHang> listHCT = ctDAO.selectByMaHD(maHD);
        for (CTHDDatHang a : listHCT) {
            JDBCHelper.update("UPDATE SANPHAM SET SLTRENKE = SLTRENKE + ? WHERE MASP = ?", a.getSL(), a.getMaSP());
        }
        
         HDDatHang hd = this.selectByID(maHD);
         JDBCHelper.update("UPDATE KHACHHANG SET SODIEM = SODIEM  + ?",hd.getDiemSD());
    }

}
