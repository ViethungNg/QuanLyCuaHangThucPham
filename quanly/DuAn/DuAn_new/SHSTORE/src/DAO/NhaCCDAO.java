/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.NhaCC;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class NhaCCDAO extends CuaHangDAO<NhaCC, String> {

    @Override
    public void insert(NhaCC entity) {
        JDBCHelper.update("INSERT INTO NHACC VALUES (?,?,?,?,?)", entity.getMaNCC(), entity.getTenNCC(), entity.getSdt(), entity.getDiaChi(), entity.isTrangThai());
    }

    @Override
    public void update(NhaCC entity) {
        JDBCHelper.update("UPDATE NHACC SET TENNCC = ?, SDT =?, DIACHI = ?,TRANGTHAI = ? WHERE MANCC = ?", entity.getTenNCC(), entity.getSdt(), entity.getDiaChi(), entity.isTrangThai(), entity.getMaNCC());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FROM NHACC WHERE MANCC = ?", id);
    }

    @Override
    public NhaCC selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM NHACC where mancc = ?", id);
            while (rs.next()) {
                NhaCC ncc = new NhaCC();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSdt(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getBoolean(5));
                return ncc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<NhaCC> selectAll() {
        List<NhaCC> listNCC = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM NHACC");
            while (rs.next()) {
                NhaCC ncc = new NhaCC();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSdt(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getBoolean(5));
                listNCC.add(ncc);
            }
            return listNCC;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<NhaCC> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public List<NhaCC> selectAllbyTinhTrang() {
        List<NhaCC> listNCC = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM NHACC where trangthai = 1");
            while (rs.next()) {
                NhaCC ncc = new NhaCC();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSdt(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getBoolean(5));
                listNCC.add(ncc);
            }
            return listNCC;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
