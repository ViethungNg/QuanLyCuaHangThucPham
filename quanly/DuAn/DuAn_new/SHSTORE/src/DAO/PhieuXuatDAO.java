/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTPhieuXuat;
import Models.PhieuXuat;
import Utils.JDBCHelper;
import Utils.XDate;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class PhieuXuatDAO extends CuaHangDAO<PhieuXuat, String> {
    
    public static List<PhieuXuat> listPX;
    @Override
    public void insert(PhieuXuat entity) {

    }

    @Override
    public void update(PhieuXuat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FROM PHIEUXUAT WHERE MAPX = ?", id);
    }

    @Override
    public PhieuXuat selectbyID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PhieuXuat> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PhieuXuat> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insertPXRretunMaPX(PhieuXuat entity) {
        try {
            ResultSet rs = JDBCHelper.query("SP_INSERTPHIEUXUAT ?,?,?,?",XDate.getDate(), entity.getGhiChu(), entity.getMaKe(), entity.getMaNV());
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void hoanTatPhieu(String ghiChu, String maPX, List<CTPhieuXuat> listct) {
        for (CTPhieuXuat ct : listct) {
            JDBCHelper.update("EXEC SP_HOANTATPHIEUXUAT ?,?,?,?", ct.getMaPX(), ct.getMaSP(), ct.getSl(),"KH1");
        }
        JDBCHelper.update("UPDATE PHIEUXUAT SET GHICHU = ? WHERE MAPX = ?", ghiChu, maPX);

    }
    
    public List<PhieuXuat> selectLSPhieuXuat(String ngayBD, String ngayKT, String TheoNgay){
        List<PhieuXuat> listPX = new ArrayList<>();
             try {
            ResultSet rs = JDBCHelper.query("EXEC SP_LICHSULENKE ?,?,?",ngayBD,ngayKT,TheoNgay);
            while (rs.next()) {
                PhieuXuat px = new PhieuXuat();
               px.setMaPX(rs.getString(1));
               px.setNgayXuat(XDate.toString(rs.getDate(2), "dd-MM-yyyy")+" "+rs.getTime(2));
               px.setGhiChu(rs.getString(3));
               px.setMaKe(rs.getString(4));
               px.setMaNV(rs.getString(5));
               listPX.add(px);
            }
            return listPX;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<PhieuXuat> getList(){
        return listPX;
    }

    public static void setListPX(List<PhieuXuat> list) {
        listPX = list;
    }
    
   

}
