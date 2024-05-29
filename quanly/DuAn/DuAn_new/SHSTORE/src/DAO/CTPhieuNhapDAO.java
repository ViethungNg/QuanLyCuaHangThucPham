/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTPhieuNhap;
import Utils.JDBCHelper;
import Utils.XDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class CTPhieuNhapDAO extends CuaHangDAO<CTPhieuNhap, String> {

    @Override
    public void insert(CTPhieuNhap entity) {
        JDBCHelper.update("EXEC SP_INSERTCTPHIEUNHAP ?,?,?,?,?,?", entity.getMaPN(), entity.getSl(), entity.getMaSP(), entity.getGiaNhap(), entity.getNgaySX(), entity.getNgayHH());
    }

    @Override
    public void update(CTPhieuNhap entity) {

    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CTPhieuNhap selectbyID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CTPhieuNhap> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CTPhieuNhap> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CTPhieuNhap> selectCTPNbyMaPN(String maPN) {
        List<CTPhieuNhap> listCT = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTPHIEUNHAP WHERE MAPN = ?", maPN);
            while (rs.next()) {
                CTPhieuNhap ct = new CTPhieuNhap();
                ct.setMaSP(rs.getString(1));
                ct.setMaPN(rs.getString(2));
                ct.setSl(rs.getInt(3));
                ct.setGiaNhap(rs.getDouble(4));
                ct.setNgaySX(XDate.toString(rs.getDate(5), "dd-MM-yyyy"));
                ct.setNgayHH(XDate.toString(rs.getDate(6), "dd-MM-yyyy"));
                listCT.add(ct);
            }
            return listCT;
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //XÓA SẢN PHẨM TRONG CT PHIẾU NHẬP ĐỒNG THỜI CẬP NHẬT LẠI SL SẢN PHẨM
    public void xoaSPCTPhieu(CTPhieuNhap ctPN) {
        try {
            JDBCHelper.update("EXEC SP_XOASPCHITIET ?,?,?", ctPN.getMaSP(), ctPN.getSl(), ctPN.getMaPN());
        } catch (Exception ex) {
            Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CTPhieuNhap SelectBymaPNmaSP(String maPN, String maSP) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTPHIEUNHAP WHERE  masp =? and mapn = ?", maPN, maSP);
            while (rs.next()) {
                CTPhieuNhap ct = new CTPhieuNhap();
                ct.setMaSP(rs.getString(1));
                ct.setMaPN(rs.getString(2));
                ct.setSl(rs.getInt(3));
                ct.setGiaNhap(rs.getDouble(4));
                ct.setNgaySX(XDate.toString(rs.getDate(5), "dd-MM-yyyy"));
                ct.setNgayHH(XDate.toString(rs.getDate(6), "dd-MM-yyyy"));
                return ct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Object[]> selectCTbyMaPN(String maPN) {
        List<Object[]> listHDCT = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_SELECTCTPHIEUNHAP ?", maPN);
            while (rs.next()) {
                Object row[] = {rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), XDate.toString(rs.getDate(6), "dd-MM-yyyy"), XDate.toString(rs.getDate(7), "dd-MM-yyyy"), rs.getDouble(8)};
                listHDCT.add(row);
            }
            return listHDCT;
        } catch (Exception e) {
        }
        return null;
    }
}
