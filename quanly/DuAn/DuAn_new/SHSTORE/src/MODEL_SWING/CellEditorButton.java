/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL_SWING;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huuho
 */
public class CellEditorButton extends DefaultCellEditor {

    JButton button;

    public CellEditorButton() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column==5) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(row);
        }
        
        return null;
    }

}
