/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.JDBCHelper;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author TIEN SY
 */
public class ThongKeDAO {
    public List<Object[]>thongKeDT(int index, String ngayBD, String ngayKT){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_THONGKE ?,?,?",index,ngayBD,ngayKT);
            while (rs.next()) {                
                Object[] a ={rs.getString(1),rs.getDouble(2)}; 
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static double DTHomNay(){
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_THONGKEHOMNAY");
            while (rs.next()) {                
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }   
    
    public List<Object[]>thongKeSPBanChay(){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query("EXEC SP_THONGKESPBANCHAY");
            while (rs.next()) {                
                Object[] a ={rs.getInt(1),rs.getString(2),rs.getString(3)}; 
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public long LoiNhuan(String ngayBD, String ngayKT){
        try {
          ResultSet rs=   JDBCHelper.query("EXEC SP_THONGKELOINHUAN ?,?",ngayBD,ngayKT);
            while (rs.next()) {                
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
  
}
