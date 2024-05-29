/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.CTHDDatHang;
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
public class CTHDDatHangDAO {

    public void insert(String maHD, List<CTHDDatHang> ct) {
        for (CTHDDatHang ctHD : ct) {
            JDBCHelper.update("EXEC SP_CTHDDATHANG  ?,?,?,?", maHD, ctHD.getMaSP(), ctHD.getSL(), ctHD.getGiaBan());
        }
    }

    public List<CTHDDatHang> selectByMaHD(String maHD) {
        List<CTHDDatHang> listCT = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("SELECT * FROM CTHDDATHANG where MAHDDATHANG = ?", maHD);
            while (rs.next()) {
                CTHDDatHang ctHD = new CTHDDatHang();
                ctHD.setMaHD(rs.getString(1));
                ctHD.setMaSP(rs.getString(2));
                ctHD.setSL(rs.getInt(3));
                ctHD.setGiaBan(rs.getDouble(4));
                listCT.add(ctHD);
            }
            return listCT;
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDatHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
