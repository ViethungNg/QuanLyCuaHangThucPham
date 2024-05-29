/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.KhachHang;
import Utils.Auth_KH;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author huuho
 */
public class KhachHangDAO extends CuaHangDAO<KhachHang, String> {

    String insertKH = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?,?)";
    String updateKH = "UPDATE KHACHHANG SET TENKH = ?, SODIEM = ?, gmail = ?, matkhau = ? WHERE SDT = ?";
    String deleteKH = "DELETE FROM KHACHHANG WHERE SDT = ?";
    String selectAllKH = "SELECT * FROM KHACHHANG";
    String selectById = "SELECT * FROM KHACHHANG WHERE SDT = ?";

    @Override
    public void insert(KhachHang entity) {
        try {
            JDBCHelper.update(insertKH, entity.getSDT(), entity.getTenKH(), entity.getDiem(), entity.getGmail(), entity.getMatkhau(), entity.getDiaChi());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(KhachHang entity) {
        try {
            JDBCHelper.update(updateKH, entity.getTenKH(), entity.getDiem(), entity.getGmail(), entity.getMatkhau(), entity.getSDT());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(deleteKH, id);
    }

    @Override
    public KhachHang selectbyID(String id) {
        List<KhachHang> list = this.selectSQL(selectById, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectSQL(selectAllKH);
    }

    @Override
    public List<KhachHang> selectSQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                    list.add(kh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void tichDiem(Double thanhTien, String maKH) {
        JDBCHelper.update("exec TICHDIEM ?,?", thanhTien, maKH);
    }
    
    public void doiMatKhau(String matKhauMoi){
        JDBCHelper.update("UPDATE KHACHHANG SET MATKHAU = ? WHERE SDT = ?",matKhauMoi,Auth_KH.user.getSDT());
    }

}
