/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.KhachHangDAO;
import Models.KhachHang;
import UI.Children.DaiLog.TaoSPMoiDialog;
import Utils.MsgBox;
import Utils.Validation;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class panelKhachHang extends javax.swing.JPanel {

    /**
     * Creates new form panelKhachHang
     */
    public panelKhachHang() {
        initComponents();
        fillTableKH();
    }
    KhachHangDAO khDAO = new KhachHangDAO();

    private void fillTableKH() {
        DefaultTableModel model = (DefaultTableModel) tblListKH.getModel();
        model.setRowCount(0);
        for (KhachHang x : khDAO.selectAll()) {
            Object row[] = {x.getSDT(), x.getTenKH(), x.getGmail(), x.getMatkhau(), x.getDiem()};
            model.addRow(row);
        }
    }

    public void addKH() {
        if (khDAO.selectbyID(txtSDT.getText()) == null) {
            KhachHang kh = getForm();
            khDAO.insert(kh);
            clearFormKH();
            fillTableKH();
            MsgBox.alert(this, "Thêm Khách Hàng Thành Công");
        } else {
            MsgBox.alert(this, "Số Điện Thoại Này Đã Tồn Tại Trên Hệ Thống");
        }
    }

    public KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMatkhau(txtMatkhau.getText());
        kh.setSDT(txtSDT.getText());
        kh.setGmail(txtGmail.getText());
        kh.setTenKH(txtTenKH.getText());
        return kh;
    }

    public void updateKH() {
        khDAO.update(new KhachHang(txtSDT.getText(), txtTenKH.getText(), Integer.valueOf(lblDiem.getText()), txtGmail.getText(), txtMatkhau.getText()));
        MsgBox.alert(null, "Cập Nhật Thành Công");
        fillTableKH();
    }

    public boolean validateKH() {
        if (txtSDT.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Số Điện Thoại");
            txtSDT.requestFocus();
            return false;
        }
        if (txtTenKH.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Tên Khách Hàng");
            txtTenKH.requestFocus();
            return false;
        }
        if (txtGmail.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Gmail");
            txtGmail.requestFocus();
            return false;
        }
        if (Validation.isEmail(txtGmail, "Email sai định dạng")) {
            return false;
        }
        if (txtMatkhau.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập Mật Khẩu");
            txtMatkhau.requestFocus();
            return false;
        }
        return true;
    }

    public void clearFormKH() {
        txtSDT.setText(null);
        txtSDT.requestFocus();
        txtTenKH.setText(null);
        txtGmail.setText(null);
        txtMatkhau.setText(null);
        lblDiem.setText("0");
        btnCapnhat.setEnabled(false);

        btnThem.setEnabled(true);
        txtSDT.setEnabled(true);
    }

    public void showDetailKH(int slr) {
        KhachHang khachHang = khDAO.selectbyID((String) tblListKH.getValueAt(slr, 0));
        txtSDT.setText(khachHang.getSDT());
        txtSDT.setEnabled(false);
        txtTenKH.setText(khachHang.getTenKH());
        txtGmail.setText(khachHang.getGmail());
        txtMatkhau.setText(khachHang.getMatkhau());
        lblDiem.setText(String.valueOf(khachHang.getDiem()));
//        btnXoa.setEnabled(true);
        btnCapnhat.setEnabled(true);
        btnThem.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblSDT = new javax.swing.JLabel();
        lblSodiem = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapnhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        lblDiem = new javax.swing.JLabel();
        lblGmail = new javax.swing.JLabel();
        txtGmail = new javax.swing.JTextField();
        lblMatkhau = new javax.swing.JLabel();
        txtMatkhau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListKH = new HELP.Table();

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Quản Lý Khách Hàng");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(0, 102, 102));
        lblSDT.setText("Số điện thoại");

        lblSodiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSodiem.setForeground(new java.awt.Color(0, 102, 102));
        lblSodiem.setText("Số Điểm");

        lblTenKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(0, 102, 102));
        lblTenKH.setText("Tên Khách Hàng");

        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem);

        btnCapnhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapnhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhat.setText("Cập Nhật");
        btnCapnhat.setEnabled(false);
        btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapnhat);

        btnMoi.setBackground(new java.awt.Color(0, 102, 102));
        btnMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoi);

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        lblDiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDiem.setForeground(new java.awt.Color(255, 0, 51));
        lblDiem.setText("0");

        lblGmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGmail.setForeground(new java.awt.Color(0, 102, 102));
        lblGmail.setText("Gmail");

        lblMatkhau.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMatkhau.setForeground(new java.awt.Color(0, 102, 102));
        lblMatkhau.setText("Mật Khẩu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSodiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMatkhau, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblDiem)
                        .addComponent(txtMatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(txtGmail, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKH))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGmail))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatkhau))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSodiem))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Danh Sách Khách Hàng");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jLabel6.setOpaque(true);

        jTextField8.setText("Mã KM");

        jLabel30.setBackground(new java.awt.Color(0, 102, 102));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Tìm Kiếm");
        jLabel30.setOpaque(true);

        tblListKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SĐT", "Họ Tên", "Email", "Mật Khẩu", "Điểm Tích"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListKH.setRowHeight(25);
        tblListKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListKHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListKH);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1003, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (validateKH()) {
            addKH();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed
        if (validateKH()) {
            updateKH();
        }

    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearFormKH();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void tblListKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListKHMouseClicked
        showDetailKH(tblListKH.getSelectedRow());
    }//GEN-LAST:event_tblListKHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblDiem;
    private javax.swing.JLabel lblGmail;
    private javax.swing.JLabel lblMatkhau;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSodiem;
    private javax.swing.JLabel lblTenKH;
    private HELP.Table tblListKH;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtMatkhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
