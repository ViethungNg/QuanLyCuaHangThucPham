/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.NhaCCDAO;
import Models.NhaCC;
import Utils.JDBCHelper;
import Utils.MsgBox;
import Utils.Validation;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huuho
 */
public class PanelNCC extends javax.swing.JPanel {

    /**
     * Creates new form PanelNCC
     */
    public PanelNCC() {
        initComponents();
        fillTable();
        btnCapNhat.setEnabled(false);
        
    }
    NhaCCDAO nhaccDAO = new NhaCCDAO();
    
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblListNCC.getModel();
        model.setRowCount(0);
        List<NhaCC> listNCC = nhaccDAO.selectAll();
        for (NhaCC ncc : listNCC) {
            Object rows[] = {ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSdt(), ncc.getDiaChi(), ncc.isTrangThai() ? "Đang hợp tác" : "Ngừng hợp tác"};
            model.addRow(rows);
        }
        rdoDangHT.setSelected(true);
    }
    
    public NhaCC getForm() {
        NhaCC ncc = new NhaCC();
        ncc.setMaNCC(txtMaNCC.getText());
        ncc.setTenNCC(txtTenNCC.getText());
        ncc.setDiaChi(txtDiachi.getText());
        ncc.setSdt(txtSDT.getText());
        ncc.setTrangThai(rdoDangHT.isSelected() ? true : false);
        return ncc;
    }
    
    public void setForm(NhaCC ncc) {
        txtMaNCC.setText(ncc.getMaNCC());
        txtTenNCC.setText(ncc.getTenNCC());
        txtSDT.setText(ncc.getSdt());
        txtDiachi.setText(ncc.getDiaChi());
        if (ncc.isTrangThai()) {
            rdoDangHT.setSelected(true);
        } else {
            rdoNgungHT.setSelected(true);
        }
        btnCapNhat.setEnabled(true);
        btnThem.setEnabled(false);
    }
    
    public void insert() {
        try {
            nhaccDAO.insert(this.getForm());
            MsgBox.alert(this, "Thêm Thành Công");
            fillTable();
            clearForm();
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại !");
        }
    }
    
    public void update() {
        try {
            nhaccDAO.update(getForm());
            MsgBox.alert(this, "Cập nhật Thành Công");
            fillTable();
            clearForm();
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại ?");
        }
    }
    
    public void delete() {
        NhaCC ncc = nhaccDAO.selectbyID(txtMaNCC.getText());
        if (ncc == null) {
            MsgBox.alert(this, "Nhà cung cấp không tồn tại");
        } else {
            try {
                nhaccDAO.delete(txtMaNCC.getText());
                fillTable();
                clearForm();
                MsgBox.alert(this, "Delete Thành Công");
            } catch (Exception e) {
                MsgBox.alert(this, "Delete Thất bại !");
            }
        }
        
    }
    
    public void clearForm() {
        NhaCC ncc = new NhaCC();
        setForm(ncc);
        btnThem.setEnabled(true);
        btnCapNhat.setEnabled(false);
    }
    
    public boolean ckvalidate() {
        if (Validation.isEmpty(txtMaNCC, "Mã nhà cung cấp không được bỏ trống")) {
            return false;
        }
        
        if (Validation.isEmpty(txtTenNCC, "Tên nhà cung cấp không được bỏ trống")) {
            return false;
        }
        
        if (Validation.isEmpty(txtSDT, "Số điện thoại không được bỏ trống")) {
            return false;
        }
        
        if (Validation.isNumber(txtSDT, "Sai định dạng số điện thoại")) {
            return false;
        }
        
        if (txtSDT.getText().length() != 10) {
            MsgBox.alert(this, "Sai định dạng số điện thoại");
            return false;
        }
        
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtMaNCC = new SWING_OTHER.MyTextField();
        lblMaNCC = new javax.swing.JLabel();
        lblTenNCC = new javax.swing.JLabel();
        txtTenNCC = new SWING_OTHER.MyTextField();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new SWING_OTHER.MyTextField();
        lblSDT1 = new javax.swing.JLabel();
        rdoDangHT = new javax.swing.JRadioButton();
        rdoNgungHT = new javax.swing.JRadioButton();
        lblDiachi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiachi = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListNCC = new HELP.Table();

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMaNCC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaNCC.setForeground(new java.awt.Color(0, 102, 102));
        lblMaNCC.setText("Mã Nhà Cung Cấp");

        lblTenNCC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenNCC.setForeground(new java.awt.Color(0, 102, 102));
        lblTenNCC.setText("Tên Nhà Cung Cấp");

        lblSDT.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(0, 102, 102));
        lblSDT.setText("Số Điện Thoại");

        lblSDT1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSDT1.setForeground(new java.awt.Color(0, 102, 102));
        lblSDT1.setText("Trạng Thái");

        bgrTrangThai.add(rdoDangHT);
        rdoDangHT.setText("Đang hợp tác");

        bgrTrangThai.add(rdoNgungHT);
        rdoNgungHT.setText("Ngừng hợp tác");

        lblDiachi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDiachi.setForeground(new java.awt.Color(0, 102, 102));
        lblDiachi.setText("Địa Chỉ");

        txtDiachi.setColumns(20);
        txtDiachi.setRows(5);
        jScrollPane1.setViewportView(txtDiachi);

        jPanel2.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm Mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem);

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel2.add(btnCapNhat);

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Làm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSDT1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDiachi, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoDangHT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNgungHT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaNCC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTenNCC, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNCC)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTenNCC)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT1)
                    .addComponent(rdoDangHT)
                    .addComponent(rdoNgungHT))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDiachi)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);

        tblListNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NCC", "Tên NCC", "Số Điện Thoại", "Địa chỉ", "Trạng Thái"
            }
        ));
        tblListNCC.setRowHeight(30);
        tblListNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListNCCMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListNCC);

        jPanel1.add(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListNCCMouseClicked
        int index = tblListNCC.getSelectedRow();
        NhaCC ncc = nhaccDAO.selectbyID((String) tblListNCC.getValueAt(index, 0));
        setForm(ncc);
    }//GEN-LAST:event_tblListNCCMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(ckvalidate()){
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
      if(ckvalidate()){
          update();
      }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTrangThai;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiachi;
    private javax.swing.JLabel lblMaNCC;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDT1;
    private javax.swing.JLabel lblTenNCC;
    private javax.swing.JRadioButton rdoDangHT;
    private javax.swing.JRadioButton rdoNgungHT;
    private HELP.Table tblListNCC;
    private javax.swing.JTextArea txtDiachi;
    private SWING_OTHER.MyTextField txtMaNCC;
    private SWING_OTHER.MyTextField txtSDT;
    private SWING_OTHER.MyTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
