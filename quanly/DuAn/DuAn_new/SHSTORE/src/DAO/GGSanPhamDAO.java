/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.GGSanPham;
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
public class GGSanPhamDAO extends CuaHangDAO<GGSanPham, String> {

    public static List<GGSanPham> ListGGSP = new ArrayList<>();

    public static List<GGSanPham> getListGGSP() {
        return ListGGSP;
    }

    public static void setListGGSP(List<GGSanPham> ListGGSP) {
        GGSanPhamDAO.ListGGSP = ListGGSP;
    }

    @Override
    public void insert(GGSanPham entity) {
        JDBCHelper.update("INSERT INTO GGSANPHAM VALUES (?,?,?,?,?)", entity.getMaSP(), entity.getNgayBD(), entity.getNgayKT(), entity.getMaNV(), entity.getGiaGiam());
    }

    @Override
    public void update(GGSanPham entity) {
        JDBCHelper.update("UPDATE GGSANPHAM SET NGAYBD = ?, NGAYKT = ?, GIAGIAM = ? WHERE MASP = ?", entity.getNgayBD(), entity.getNgayKT(), entity.getGiaGiam(), entity.getMaSP());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update("DELETE FROM GGSANPHAM WHERE MASP = ?", id);
    }

    @Override
    public GGSanPham selectbyID(String id) {
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGSANPHAM where MASP = ?", id);
            while (rs.next()) {
                GGSanPham ggSP = new GGSanPham();
                ggSP.setMaSP(rs.getString(1));
                ggSP.setNgayBD(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                ggSP.setNgayKT(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggSP.setMaNV(rs.getString(4));
                ggSP.setGiaGiam(rs.getDouble(5));
                return ggSP;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GGSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<GGSanPham> selectAll() {
        List<GGSanPham> listGGSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGSANPHAM");
            while (rs.next()) {
                GGSanPham ggSP = new GGSanPham();
                ggSP.setMaSP(rs.getString(1));
                ggSP.setNgayBD(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                ggSP.setNgayKT(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggSP.setMaNV(rs.getString(4));
                ggSP.setGiaGiam(rs.getDouble(5));
                listGGSP.add(ggSP);
            }
            return listGGSP;
        } catch (SQLException ex) {
            Logger.getLogger(GGSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<GGSanPham> selectSQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<GGSanPham> selectByMaSP(String maSP) {
        List<GGSanPham> listGGSP = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM GGSANPHAM where masp LIKE ?", "%" + maSP + "%");
            while (rs.next()) {
                GGSanPham ggSP = new GGSanPham();
                ggSP.setMaSP(rs.getString(1));
                ggSP.setNgayBD(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                ggSP.setNgayKT(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggSP.setMaNV(rs.getString(4));
                ggSP.setGiaGiam(rs.getDouble(5));
                listGGSP.add(ggSP);
            }
            return listGGSP;
        } catch (SQLException ex) {
            Logger.getLogger(GGSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public GGSanPham selectByCheckHieuLuc(String maSP) {

        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_CHECKMAGG ?", "%" + maSP + "%");
            while (rs.next()) {
                GGSanPham ggSP = new GGSanPham();
                ggSP.setMaSP(rs.getString(1));
                ggSP.setNgayBD(XDate.toString(rs.getDate(2), "dd-MM-yyyy"));
                ggSP.setNgayKT(XDate.toString(rs.getDate(3), "dd-MM-yyyy"));
                ggSP.setMaNV(rs.getString(4));
                ggSP.setGiaGiam(rs.getDouble(5));
                return ggSP;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GGSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
