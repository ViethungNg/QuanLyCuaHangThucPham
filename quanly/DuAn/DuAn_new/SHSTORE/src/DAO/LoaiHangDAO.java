/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.LoaiHang;
import Utils.JDBCHelper;
import Utils.MsgBox;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author TIEN SY
 */
public class LoaiHangDAO extends CuaHangDAO<LoaiHang, String> {

    public static List<LoaiHang> listLH = new ArrayList<>();

    String insertLH = "INSERT INTO LOAIHANG VALUES(?,?)";
    String updateLH = "UPDATE LOAIHANG SET TENLOAI = ? WHERE MALOAI = ?";
    String deleteLH = "DELETE FROM LOAIHANG WHERE MALOAI = ?";
    String selectAllLH = "SELECT * FROM LOAIHANG";
    String selectById = "SELECT * FROM LOAIHANG WHERE MALOAI = ?";

    public static List<LoaiHang> getListLH() {
        return listLH;
    }

    public static void setListLH(List<LoaiHang> listLH) {
        LoaiHangDAO.listLH = listLH;
    }

    @Override
    public void insert(LoaiHang entity) {
        try {
            JDBCHelper.update(insertLH, entity.getMaLoai(), entity.getTenLoai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LoaiHang entity) {
        try {
            JDBCHelper.update(updateLH, entity.getTenLoai(), entity.getMaLoai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            if (JDBCHelper.update(deleteLH, id) > 0) {
                MsgBox.alert(null, "Xóa Thành Công");
            } else {
                MsgBox.alert(null, "Không Tìm Thấy " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoaiHang selectbyID(String id) {
        List<LoaiHang> list = this.selectSQL(selectById, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<LoaiHang> selectAll() {
        return this.selectSQL(selectAllLH);
    }

    @Override
    public List<LoaiHang> selectSQL(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    LoaiHang lh = new LoaiHang(rs.getString(1), rs.getString(2));
                    list.add(lh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
