/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTHDBanLe;
import Utils.JDBCHelper;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class CTHDBanLeDAO {

    public void insert(CTHDBanLe ct) {
        JDBCHelper.update("EXEC  SP_INSERTCTHDBANLE ?,?,?,?", ct.getMaHD(), ct.getMaSP(), ct.getGiaBan(), ct.getSoLuong());
    }

    public List<CTHDBanLe> selectCTHDbyMAHD(String maHD) {
        List<CTHDBanLe> listCT = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTHDBANLE WHERE MAHDBANLE = ?", maHD);
            while (rs.next()) {
                CTHDBanLe ct = new CTHDBanLe();
                ct.setMaHD(rs.getString(1));
                ct.setMaSP(rs.getString(2));
                ct.setGiaBan(rs.getDouble(3));
                ct.setSoLuong(rs.getInt(4));
                listCT.add(ct);
            }
            return listCT;
        } catch (SQLException ex) {
            Logger.getLogger(CTHDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void xoaSPCTHDBanLe(CTHDBanLe ct) {
        JDBCHelper.update("SP_DELETECTHDBANLE ?,?,?", ct.getMaHD(), ct.getMaSP(), ct.getSoLuong());
    }

    public CTHDBanLe selectbymaSPandMaHD(String maSP, String maHD) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTHDBANLE WHERE MAHDBANLE = ? AND MASP = ?", maHD,maSP);
            while (rs.next()) {
                CTHDBanLe ct = new CTHDBanLe();
                ct.setMaHD(rs.getString(1));
                ct.setMaSP(rs.getString(2));
                ct.setGiaBan(rs.getDouble(3));
                ct.setSoLuong(rs.getInt(4));
                return ct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDBanLeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
  
}
