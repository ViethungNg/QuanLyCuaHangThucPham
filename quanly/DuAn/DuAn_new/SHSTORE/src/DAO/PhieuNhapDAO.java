/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTPhieuNhap;
import Models.PhieuNhap;
import Models.SanPham;
import Utils.JDBCHelper;
import Utils.XDate;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author TIEN SY
 */
public class PhieuNhapDAO extends CuaHangDAO<PhieuNhap, String> {

    @Override
    public void insert(PhieuNhap entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PhieuNhap entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        try {
            JDBCHelper.update("EXEC SP_XOAPHIEUNHAP ?", id);
        } catch (Exception ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PhieuNhap selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM PHIEUNHAP where MAPN = ?",id);
            while (rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getString(1));
                phieuNhap.setNgayNhap(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                phieuNhap.setGhiChu(rs.getString(3));
                phieuNhap.setMaNV(rs.getString(4));
                phieuNhap.setMaKho(rs.getString(5));
                phieuNhap.setMaNCC(rs.getString(6));
                phieuNhap.setTinhTrang(rs.getString(7));
          return phieuNhap;
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PhieuNhap> selectAll() {
        List<PhieuNhap> listPN = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM PHIEUNHAP");
            while (rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getString(1));
                phieuNhap.setNgayNhap(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                phieuNhap.setGhiChu(rs.getString(3));
                phieuNhap.setMaNV(rs.getString(4));
                phieuNhap.setMaKho(rs.getString(5));
                phieuNhap.setMaNCC(rs.getString(6));
                phieuNhap.setTinhTrang(rs.getString(7));
                listPN.add(phieuNhap);
            }
            return listPN;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<PhieuNhap> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PhieuNhap insertPhieuNhap(PhieuNhap pn) {
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_TAOPHIEUNHAP ?,?,?", pn.getNgayNhap(), pn.getMaNCC(), pn.getMaNV());
            while (rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getString(1));
                phieuNhap.setNgayNhap(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                return phieuNhap;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<PhieuNhap> selectPhieuNhapAndTimKiem(String MaPN, String ngayBD, String ngayKT, String theoNgay, String maNCC) {
        List<PhieuNhap> listPN = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC  SP_SELECTPHIEUNHAPVATK ?,?,?,?,?", MaPN, ngayBD, ngayKT, theoNgay, maNCC);
            while (rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getString(1));
                phieuNhap.setNgayNhap(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                phieuNhap.setGhiChu(rs.getString(3));
                phieuNhap.setMaNV(rs.getString(4));
                phieuNhap.setMaKho(rs.getString(5));
                phieuNhap.setMaNCC(rs.getString(6));
                phieuNhap.setTinhTrang(rs.getString(7));
                listPN.add(phieuNhap);
            }
            return listPN;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
