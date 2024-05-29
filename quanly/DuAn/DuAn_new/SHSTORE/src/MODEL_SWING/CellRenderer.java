/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HELP;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author huuho
 */
public class CellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    JLabel label;
    Image image[];

    public CellRenderer(BufferedImage... bi) {
        image = new Image[bi.length];
        for (int i = 0; i < bi.length; i++) {
            image[i] = bi[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH);
        }
    }
    

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label = new JLabel(new ImageIcon(image[row]));
        return label;

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return label;
    }

}
