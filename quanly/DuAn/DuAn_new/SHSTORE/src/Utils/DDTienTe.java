/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author TIEN SY
 */
public class DDTienTe {

    public static Locale localeVN = new Locale("vi", "VN");
    public static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public static String FormatVND(Double gia) {
        return currencyVN.format(gia);
    }
    
    public static String FormatToMacDinh(Double gia){
      return new DecimalFormat("#").format(gia);
    }
    
    
    public static String catCham(String a){
        return a.substring(0, a.indexOf("."));
    }
}
