/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTHDBanLe;
import Models.HDBanLe;
import Utils.Auth;
import Utils.JDBCHelper;
import Utils.MsgBox;
import Utils.XDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class HDBanLeDAO {

    public String insertReturnMaHD() {
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_INSERTHDBANLE ?,?", XDate.getDate(), Auth.user.getMaNV());
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
            MsgBox.alert(null, "Lỗi");
        }
        return null;
    }

    public double getTongTien(String maHD) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT SUM(SL*GIABAN) FROM CTHDBANLE WHERE MAHDBANLE = ?", maHD);
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public Double getTienGG(String maHD, String maGG) {
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_GETTIENGG ?,?", maHD, maGG);
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public void suDungMaGG(String maHD, String maGG) {
        JDBCHelper.update("EXEC SP_SDMAGGHD ?,?", maHD, maGG);
    }

    public void huyMaGG(String maHD, String maGG) {
        JDBCHelper.update("EXEC SP_HUYMAGGHD ?,?", maHD, maGG);
    }

    public void deleteHD(String maHD) {
        JDBCHelper.update("DELETE FROM HDBANLE WHERE MAHDBANLE = ?", maHD);
    }

    public void thanhToan(List<CTHDBanLe> listCT, HDBanLe hd) {
        System.out.println(hd.getMaHD());
        JDBCHelper.update("EXEC SP_THANHTOANHD ?,?,?,?,?", hd.getSdtKH(), hd.getMaGG(), hd.getDiemSD(), hd.getMaHD(), hd.getTienKhuyenMai());
        for (CTHDBanLe ct : listCT) {
            JDBCHelper.update("EXEC SP_INSERTCTHDBANLE ?,?,?,?", ct.getMaHD(), ct.getMaSP(), ct.getGiaBan(), ct.getSoLuong());
        }

    }

    public List<HDBanLe> selectALbyMaAndNgay(String maHD, String ngayBD, String ngayKT,String theoNgay) {
        List<HDBanLe> listHDBanLe = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_SELECTTIMKIEMHDBANLE ?,?,?,?", maHD, ngayBD, ngayKT,theoNgay);
            while (rs.next()) {
                HDBanLe hdBanLe = new HDBanLe();
                hdBanLe.setMaHD(rs.getString(1));
                hdBanLe.setNgayLap(XDate.toString(rs.getDate(2), "dd-MM-yyyy") + " " + XDate.toString(rs.getTime(2), "HH:mm:ss"));
                hdBanLe.setTrangThai(rs.getBoolean(3));
                hdBanLe.setSdtKH(rs.getString(4));
                hdBanLe.setMaGG(rs.getString(5));
                hdBanLe.setMaNV(rs.getString(6));
                hdBanLe.setDiemSD(rs.getInt(7));
                hdBanLe.setTienKhuyenMai(rs.getDouble(8));
                listHDBanLe.add(hdBanLe);
            }
            return listHDBanLe;
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Double getTongTienHD(String maHD) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT SUM(SL*GIABAN) FROM CTHDBANLE WHERE MAHDBANLE LIKE ?", maHD);
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HDBanLe selectByID(String maHD) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM HDBANLE WHERE MAHDBANLE = ?", maHD);
            while (rs.next()) {
                HDBanLe hdBanLe = new HDBanLe();
                hdBanLe.setMaHD(rs.getString(1));
                hdBanLe.setNgayLap(XDate.toString(rs.getDate(2), "dd-MM-yyyy") + " " + XDate.toString(rs.getTime(2), "HH:mm:ss"));
                hdBanLe.setTrangThai(rs.getBoolean(3));
                hdBanLe.setSdtKH(rs.getString(4));
                hdBanLe.setMaGG(rs.getString(5));
                hdBanLe.setMaNV(rs.getString(6));
                hdBanLe.setDiemSD(rs.getInt(7));
                hdBanLe.setTienKhuyenMai(rs.getDouble(8));
                return hdBanLe;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
