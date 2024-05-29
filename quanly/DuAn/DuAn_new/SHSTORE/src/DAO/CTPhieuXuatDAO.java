/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTPhieuXuat;
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
public class CTPhieuXuatDAO {

    public void insert(List<CTPhieuXuat> listSP, String maPX) {
        for (CTPhieuXuat ctPX : listSP) {
            JDBCHelper.update("SP_XUATSPLENKE ?,?,?", maPX, ctPX.getMaSP(), ctPX.getSl());
        }
    }

    public void xoaSP(CTPhieuXuat ctPX) {
        JDBCHelper.update("SP_XOASPPHIEUXUAT ?,?,?", ctPX.getMaSP(), ctPX.getSl(), ctPX.getMaPX());
    }

    public void themSP(CTPhieuXuat ctPX) {
        JDBCHelper.update("SP_THEMSPPHIEUXUAT ?,?,?,?", ctPX.getMaSP(), ctPX.getSl(), ctPX.getMaPX(), "KH1");
    }

    public List<CTPhieuXuat> selectbyMaPX(String maPX) {
        List<CTPhieuXuat> listCTPX = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SP_SELECTCTPHIEUXUAT ?", maPX);
            while (rs.next()) {
                CTPhieuXuat ctPX = new CTPhieuXuat();
                ctPX.setMaSP(rs.getString(1));
                ctPX.setSl(rs.getInt(2));
                listCTPX.add(ctPX);
            }
            return listCTPX;
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CTPhieuXuat selectByMaPXmaSP(String maPX, String maSP) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTPHIEUXUAT WHERE MASP = ? AND MAPX = ?", maSP, maPX);
            while (rs.next()) {
                CTPhieuXuat ct = new CTPhieuXuat();
                ct.setMaPX(rs.getString(2));
                ct.setMaSP(rs.getString(1));
                ct.setSl(rs.getInt(3));
                return ct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CTPhieuXuat> selectLichSuLenKe(String ngayBD, String ngayKT, String chonNgay) {
        List<CTPhieuXuat> listCTPX = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC  SP_LICHSULENKE ?,?,?",ngayBD,ngayKT,chonNgay);
            while (rs.next()) {
                CTPhieuXuat ctPX = new CTPhieuXuat();
                ctPX.setMaSP(rs.getString(1));
                ctPX.setSl(rs.getInt(2));
                listCTPX.add(ctPX);
            }
            return listCTPX;
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
