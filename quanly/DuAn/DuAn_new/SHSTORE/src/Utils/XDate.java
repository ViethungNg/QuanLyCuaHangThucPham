/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TIEN SY
 */
public class XDate {
 static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//đổi String thành giờ

    public static Date toDate(String date, String pattern) {
        try {
            simpleDateFormat.applyPattern(pattern);
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String toString(Date date, String pattern) {
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date getDate() {
        return new Date();
    }
    
    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

}
