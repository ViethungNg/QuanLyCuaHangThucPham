/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Validation {

    public static boolean isEmpty(JTextField textField, String msg) {
        String txtValue = textField.getText().trim();
        if (txtValue.length() == 0) {
            MsgBox.alert(null, msg);
            textField.setBorder(new LineBorder(Color.red));
            textField.requestFocus();
            return true;
        } else {
            textField.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }

    public static boolean isDateEmpty(JDateChooser jDateChooser, String msg) {
        Date txtValue = jDateChooser.getDate();
        if (txtValue == null) {
            MsgBox.alert(null, msg);
            jDateChooser.setBorder(new LineBorder(Color.red));
            jDateChooser.requestFocus();
            return true;
        } else {
            jDateChooser.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }

    public static boolean isNumber(JTextField textField, String msg) {
        try {
            double kt = Double.parseDouble(textField.getText());
            textField.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        } catch (Exception e) {
            MsgBox.alert(null, msg);
            textField.setBorder(new LineBorder(Color.red));
            textField.requestFocus();
            return true;
        }
    }

    public static boolean isEmail(JTextField textField, String msg) {
        String txtValue = textField.getText().trim();
        String patern = "[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+(.[a-zA-Z0-9]+)*";
        if (!txtValue.matches(patern)) {
            MsgBox.alert(null, msg);
            textField.setBorder(new LineBorder(Color.red));
            textField.requestFocus();
            return true;
        } else {
            textField.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }

    public static boolean isMaNH(JTextField textField, String msg) {
        String txtValue = textField.getText().trim();
        String patern = "[A-Z]+[0-9]{5}";
        if (!txtValue.matches(patern)) {
            MsgBox.alert(null, msg);
            textField.setBorder(new LineBorder(Color.red));
            textField.requestFocus();
            return true;
        } else {
            textField.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }

    public static boolean ktNgay(JDateChooser jDateChooser, String msg) {
        Date now = new Date();
        if (jDateChooser.getDate().after(now)) {
            MsgBox.alert(null, msg);
            jDateChooser.setBorder(new LineBorder(Color.red));
            jDateChooser.requestFocus();
            return true;
        } else {
            jDateChooser.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }
    
    
        public static boolean ktNgaySau(JDateChooser jDateChooser, String msg) {
        Date now = new Date();
        if (jDateChooser.getDate().before(now)) {
            MsgBox.alert(null, msg);
            jDateChooser.setBorder(new LineBorder(Color.red));
            jDateChooser.requestFocus();
            return true;
        } else {
            jDateChooser.setBorder(new LineBorder(new Color(153, 153, 153)));
            return false;
        }
    }
}
