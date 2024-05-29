/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HELP;

import MODEL_SWING.TableHeader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.color.ColorSpace;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author huuho
 */
public class Table extends JTable {

        JButton jbutton = new JButton("X");
    public Table() {
        setGridColor(new Color(230, 230, 230));
        setShowHorizontalLines(true);
        setRowHeight(100);
        setGridColor(new Color(230, 230, 230));
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(String.valueOf(value));
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                c.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                setHorizontalAlignment(SwingConstants.CENTER);
                if (isSelected) {
                    setForeground(Color.decode("#FC466B"));
                } else {
                    setForeground(new Color(0,59,70));
                }
                return c;
            }

        });
    }
}
