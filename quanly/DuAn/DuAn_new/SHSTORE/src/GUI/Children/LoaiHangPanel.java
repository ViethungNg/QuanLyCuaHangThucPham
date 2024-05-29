/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.LoaiHangDAO;
import Models.LoaiHang;
import Utils.MsgBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class LoaiHangPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoaiHangPanel
     */
    public LoaiHangPanel() {
        initComponents();
        fillTableLH();

    }
    LoaiHangDAO loaiHangDAO = new LoaiHangDAO();

    private void addLH() {
        if (loaiHangDAO.selectbyID(txtMaloai.getText()) == null) {
            if (tblListLH.getRowCount() > 0) {
                for (int i = 0; i < tblListLH.getRowCount(); i++) {
                    if (txtTenloai.getText().equalsIgnoreCase((String) tblListLH.getValueAt(i, 1))) {
                        MsgBox.alert(this, "Tên Loại Hàng Này Đã Tồn Tại Trên Hệ Thống");
                        return;
                    }
                }
            }
            LoaiHang loaiHang = new LoaiHang(txtMaloai.getText(), txtTenloai.getText());
            loaiHangDAO.insert(loaiHang);
            clearFormLH();
            MsgBox.alert(this, "Thêm Loại Hàng Thành Công");
            fillTableLH();
        } else {
            MsgBox.alert(this, "Mã Loại Hàng Này Đã Tồn Tại Trên Hệ Thống");
        }
    }

    private void deleteLH() {
        if (MsgBox.confrim(this, "Bạn Thật Sự Muốn Xóa ?")) {
            try {
                loaiHangDAO.delete(txtMaloai.getText());
                MsgBox.alert(this, "Xóa Thành Công");
                fillTableLH();
                clearFormLH();
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa Thất Bại");
            }
        }

    }

    private void updateLH() {
        if (MsgBox.confrim(this, "Bạn Thật Sự Muốn Cập Nhật ?")) {
            try {
                loaiHangDAO.update(new LoaiHang(txtMaloai.getText(), txtTenloai.getText()));
                MsgBox.alert(this, "Cập Nhật Thành Công");
                fillTableLH();
            } catch (Exception e) {
                MsgBox.alert(this, "Cập Nhật Thất Bại !");
            }
        }

    }

    private boolean validateLH() {
        if (txtMaloai.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Mã Loại Hàng");
            txtMaloai.requestFocus();
            return false;
        }
        if (txtTenloai.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Tên Loại Hàng");
            txtTenloai.requestFocus();
            return false;
        }
        return true;
    }

    private void clearFormLH() {
        txtMaloai.setText(null);
        txtTenloai.setText(null);
//        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnThem.setEnabled(true);
        txtMaloai.setEnabled(true);
        txtMaloai.requestFocus();
//        indexSelected = -1;
    }

    private void fillTableLH() {
        DefaultTableModel model = (DefaultTableModel) tblListLH.getModel();
        model.setRowCount(0);
        for (LoaiHang x : loaiHangDAO.selectAll()) {
            Object row[] = {x.getMaLoai(), x.getTenLoai()};
            model.addRow(row);
        }
    }

    private void showDetailLH(int slr) {
        LoaiHang loaiHang = loaiHangDAO.selectbyID((String) tblListLH.getValueAt(slr, 0));
        txtMaloai.setText(loaiHang.getMaLoai());
        txtTenloai.setText(loaiHang.getTenLoai());
//        btnXoa.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaloai.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtMaloai = new SWING_OTHER.MyTextField();
        lblTenkho = new javax.swing.JLabel();
        lblMakho = new javax.swing.JLabel();
        txtTenloai = new SWING_OTHER.MyTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new SWING_OTHER.Button();
        btnNew = new SWING_OTHER.Button();
        btnCapNhat = new SWING_OTHER.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListLH = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaloai.setForeground(new java.awt.Color(0, 102, 102));
        txtMaloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaloaiActionPerformed(evt);
            }
        });

        lblTenkho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTenkho.setForeground(new java.awt.Color(0, 102, 102));
        lblTenkho.setText("Tên Loại");

        lblMakho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMakho.setForeground(new java.awt.Color(0, 102, 102));
        lblMakho.setText("Mã Loại");

        txtTenloai.setForeground(new java.awt.Color(0, 102, 102));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem);

        btnNew.setBackground(new java.awt.Color(0, 102, 102));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnNew);

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel2.add(btnCapNhat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTenkho)
                            .addComponent(lblMakho))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaloai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenloai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMakho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaloai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenkho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenloai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        add(jPanel1);

        tblListLH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Loại", "Tên Loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListLH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListLHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListLH);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaloaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaloaiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (validateLH()) {
            updateLH();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearFormLH();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblListLHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListLHMouseClicked
        showDetailLH(tblListLH.getSelectedRow());
    }//GEN-LAST:event_tblListLHMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (validateLH()) {
            addLH();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SWING_OTHER.Button btnCapNhat;
    private SWING_OTHER.Button btnNew;
    private SWING_OTHER.Button btnThem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMakho;
    private javax.swing.JLabel lblTenkho;
    private javax.swing.JTable tblListLH;
    private SWING_OTHER.MyTextField txtMaloai;
    private SWING_OTHER.MyTextField txtTenloai;
    // End of variables declaration//GEN-END:variables
}
