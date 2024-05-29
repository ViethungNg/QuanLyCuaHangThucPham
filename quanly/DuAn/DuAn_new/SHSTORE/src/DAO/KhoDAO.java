/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Kho;
import Utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhoDAO extends CuaHangDAO<Kho, String> {

    @Override
    public void insert(Kho entity) {
       JDBCHelper.update("INSERT INTO KHO VALUES (?,?,?)",entity.getMaKho(),entity.getTenKho(),entity.getDiaChi());
    }

    @Override
    public void update(Kho entity) {
        JDBCHelper.update("Update KHO SET TENKHO = ?, DIACHI = ? WHERE MAKHO = ?", entity.getTenKho(),entity.getDiaChi(),entity.getMaKho());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FORM KHO WHERE MAKHO = ?", id);
    }

    @Override
    public Kho selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM KHO WHERE MAKHO = ?",id);
            while (rs.next()) {
                Kho kho = new Kho();
                kho.setMaKho(rs.getString(1));
                kho.setTenKho(rs.getString(2));
                kho.setDiaChi(rs.getString(3));
                return kho;
            }

        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Kho> selectAll() {
        List<Kho> listKho = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM KHO");
            while (rs.next()) {
                Kho kho = new Kho();
                kho.setMaKho(rs.getString(1));
                kho.setTenKho(rs.getString(2));
                kho.setDiaChi(rs.getString(3));
                listKho.add(kho);
            }
            return listKho;
        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Kho> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
