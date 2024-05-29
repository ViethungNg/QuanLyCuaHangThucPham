/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAO.KhachHangDAO;
import Models.KhachHang;
import GUI.Children.BanHangPanel;

/**
 *
 * @author TIEN SY
 */
public class Auth_KH {

    public static KhachHang user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static String getChucVu() {
        return user.getSDT();
    }
}
