/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.GGHoaDon;
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
public class GGHoaDonDAO extends CuaHangDAO<GGHoaDon, String> {

    public static List<GGHoaDon> listGG;

    public void setList(List<GGHoaDon> list) {
        listGG = list;
    }

    public List<GGHoaDon> getList() {
        return listGG;
    }

    @Override
    public void insert(GGHoaDon entity) {
        JDBCHelper.update("INSERT INTO GGHOADON VALUES (?,?,?,?,?,?,?,?)", entity.getMaGG(), entity.getSoLuong(), entity.getNgayBD(), entity.getNgayKT(), entity.getMucGG(), entity.isHinhThuc(), entity.getHoaDonAD(), entity.getMaNV());
    }

    @Override
    public void update(GGHoaDon entity) {
        JDBCHelper.update("UPDATE GGHOADON SET SL = ?,NGAYBD = ?,NGAYKT = ?,MUCGG = ?,HINHTHUC = ?,GIATIENAPDUNG = ? WHERE MAGG = ?", entity.getSoLuong(), entity.getNgayBD(), entity.getNgayKT(), entity.getMucGG(), entity.isHinhThuc(), entity.getHoaDonAD(), entity.getMaGG());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FROM GGHOADON WHERE MAGG = ?", id);
    }

    @Override
    public GGHoaDon selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGHOADON WHERE MAGG = ?", id);
            while (rs.next()) {
                GGHoaDon ggHoaDon = new GGHoaDon();
                ggHoaDon.setMaGG(rs.getString(1));
                ggHoaDon.setSoLuong(rs.getInt(2));
                ggHoaDon.setNgayBD(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggHoaDon.setNgayKT(XDate.toString(rs.getDate(4), "dd-MM-yyyy"));
                ggHoaDon.setMucGG(rs.getDouble(5));
                ggHoaDon.setHinhThuc(rs.getBoolean(6));
                ggHoaDon.setHoaDonAD(rs.getDouble(7));
                ggHoaDon.setMaNV(rs.getString(8));
                return ggHoaDon;

            }

        } catch (SQLException ex) {
            Logger.getLogger(GGHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<GGHoaDon> selectAll() {
        List<GGHoaDon> listGGHD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGHOADON");
            while (rs.next()) {
                GGHoaDon ggHoaDon = new GGHoaDon();
                ggHoaDon.setMaGG(rs.getString(1));
                ggHoaDon.setSoLuong(rs.getInt(2));
                ggHoaDon.setNgayBD(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggHoaDon.setNgayKT(XDate.toString(rs.getDate(4), "dd-MM-yyyy"));
                ggHoaDon.setMucGG(rs.getDouble(5));
                ggHoaDon.setHinhThuc(rs.getBoolean(6));
                ggHoaDon.setHoaDonAD(rs.getDouble(7));
                ggHoaDon.setMaNV(rs.getString(8));
                listGGHD.add(ggHoaDon);

            }
            return listGGHD;
        } catch (SQLException ex) {
            Logger.getLogger(GGHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<GGHoaDon> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GGHoaDon selectbyIDandNgayAPDung(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGHOADON WHERE MAGG = ? AND ? >= NGAYBD AND ? <= NGAYKT", id, XDate.getDate(),XDate.getDate());
            while (rs.next()) {
                GGHoaDon ggHoaDon = new GGHoaDon();
                ggHoaDon.setMaGG(rs.getString(1));
                ggHoaDon.setSoLuong(rs.getInt(2));
                ggHoaDon.setNgayBD(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggHoaDon.setNgayKT(XDate.toString(rs.getDate(4), "dd-MM-yyyy"));
                ggHoaDon.setMucGG(rs.getDouble(5));
                ggHoaDon.setHinhThuc(rs.getBoolean(6));
                ggHoaDon.setHoaDonAD(rs.getDouble(7));
                ggHoaDon.setMaNV(rs.getString(8));
                return ggHoaDon;

            }

        } catch (SQLException ex) {
            Logger.getLogger(GGHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void importDuLieuVeDATABASE() {
        for (GGHoaDon gg : listGG) {
            JDBCHelper.update("EXEC  SP_IMPROTDUGGHD ?,?,?,?,?,?,?,?", gg.getMaGG(), gg.getSoLuong(), XDate.toDate(gg.getNgayBD(), "dd-MM-yyyy"),
                    XDate.toDate(gg.getNgayKT(), "dd-MM-yyyy"), gg.getMucGG(), gg.isHinhThuc(), gg.getHoaDonAD(), gg.getMaNV());
        }

    }

}
