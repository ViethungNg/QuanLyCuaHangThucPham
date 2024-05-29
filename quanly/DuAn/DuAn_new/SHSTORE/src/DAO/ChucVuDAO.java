/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ChucVu;
import Models.LoaiHang;
import Utils.JDBCHelper;
import Utils.MsgBox;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class ChucVuDAO extends CuaHangDAO<ChucVu, String> {

    String insertCV = "INSERT INTO CHUCVU VALUES(?,?)";
    String updateCV = "UPDATE CHUCVU SET TENCV = ? WHERE MACV = ?";
    String deleteCV = "DELETE FROM CHUCVU WHERE MACV = ?";
    String selectAllCV = "SELECT * FROM CHUCVU";
    String selectById = "SELECT * FROM CHUCVU WHERE MACV = ?";

    @Override
    public void insert(ChucVu entity) {
        try {
            JDBCHelper.update(insertCV, entity.getMaCV(), entity.getTenCV());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ChucVu entity) {
        try {
            JDBCHelper.update(updateCV, entity.getTenCV(), entity.getMaCV());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            if (JDBCHelper.update(deleteCV, id) > 0) {
                MsgBox.alert(null, "Xóa Thành Công");
            } else {
                MsgBox.alert(null, "Không Tìm Thấy " + id);
            }
        } catch (Exception e) {
            MsgBox.alert(null, "Chức Vụ Này Hiện Đang Có Nhân Viên Đảm Nhiệm!!!");
        }
    }

    @Override
    public ChucVu selectbyID(String id) {
        List<ChucVu> list = this.selectSQL(selectById, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ChucVu> selectAll() {
        return this.selectSQL(selectAllCV);
    }

    @Override
    public List<ChucVu> selectSQL(String sql, Object... args) {
        List<ChucVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    ChucVu cv = new ChucVu(rs.getString(1), rs.getString(2));
                    list.add(cv);
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
