
package Utils;

import Models.NhanVien;


public class Auth {
    public static NhanVien user = null;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    
    public static String getChucVu(){
                return user.getMaCV();
        }
    
}
