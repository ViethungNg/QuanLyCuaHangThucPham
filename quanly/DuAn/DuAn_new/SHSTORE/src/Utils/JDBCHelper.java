/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Models.NhanVien;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHelper {

    static String url = "jdbc:sqlserver://localhost:1433;databaseName=DU_AN_1;encrypt=true;trustServerCertificate=true";
    static String username = "sa";
    static String password = "Viethung1609";    

    public static PreparedStatement getStmt(String sql, Object... agrs) throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        ps = con.prepareStatement(sql);
        for (int i = 0; i < agrs.length; i++) {
            ps.setObject(i + 1, agrs[i]);
        }
        return ps;
    }

    public static int update(String sql, Object... agrs) {
        try {
            PreparedStatement ps = JDBCHelper.getStmt(sql, agrs);
            try {
                return ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet query(String sql, Object... agrs) throws SQLException {
        PreparedStatement ps = JDBCHelper.getStmt(sql, agrs);
        return ps.executeQuery();
    }

    public static Object value(String sql, Object... agrs) {
        try {
            ResultSet rs = JDBCHelper.query(sql, agrs);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
