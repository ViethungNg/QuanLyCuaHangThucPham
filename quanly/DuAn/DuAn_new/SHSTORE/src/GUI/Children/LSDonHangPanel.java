/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.CTHDDatHangDAO;
import DAO.HDDatHangDAO;
import DAO.SanPhamDAO;
import Models.CTHDDatHang;
import Models.HDDatHang;
import Models.SanPham;
import UI.Children.DaiLog.gifLoadDiaLog;
import Utils.Auth;
import Utils.Auth_KH;
import Utils.DDTienTe;
import Utils.XDate;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class LSDonHangPanel extends javax.swing.JPanel {

    public LSDonHangPanel() {
        initComponents();
        init();
    }
    public static HDDatHangDAO hdDatHangDAO = new HDDatHangDAO();
    public static CTHDDatHangDAO cthdDatHangDAO = new CTHDDatHangDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    gifLoadDiaLog gif = new gifLoadDiaLog(null, false);

    public void init() {
        fillTableHD(null, null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"), "1", Auth_KH.user.getSDT());
    }

    public static void fillTableHD(String maHD, String ngayBD, String ngayKT, String theoNgay, String tinhTrang, String sdt) {
        DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
        model.setRowCount(0);
        List<HDDatHang> listHD = hdDatHangDAO.selectBySearch(maHD, ngayBD, ngayKT, theoNgay, tinhTrang, sdt);
        for (HDDatHang hd : listHD) {
            String t = "";
            if (hd.getTrangThai() == 1) {
                t = "Chờ giao hàng";
            } else if (hd.getTrangThai() == 0) {
                t = "Đã hủy";
            } else {
                t = "Đã giao hàng";
            }
            Object row[] = {hd.getMaHD(), hd.getNgayDat(), hd.getDiaChiGH(), hd.isHinhThucTT() ? "Tiền mặt" : "Online", DDTienTe.FormatVND(hd.getTienKM()), hd.getDiemSD(), DDTienTe.FormatVND(hd.getTongTien()), t};
            model.addRow(row);
        }
    }

    public void fillTableCT(String maHD) {
        DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
        model.setRowCount(0);
        List<CTHDDatHang> listCT = cthdDatHangDAO.selectByMaHD(maHD);
        for (CTHDDatHang ct : listCT) {
            SanPham sp = spDAO.selectbyID(ct.getMaSP());
            Object row[] = {ct.getMaSP(), sp.getTenSP(), ct.getSL(), DDTienTe.FormatVND(ct.getGiaBan()), DDTienTe.FormatVND(ct.getTongTien())};
            model.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new HELP.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHD = new HELP.Table();
        jLabel1 = new javax.swing.JLabel();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        btnLoc = new javax.swing.JButton();
        cbo3ngayGanNhap = new javax.swing.JComboBox<>();
        cboTinhTrangDH = new javax.swing.JComboBox<>();
        btnHienThiAll = new javax.swing.JButton();
        txtMaHD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ĐH", "Ngày đặt", "ĐC GH", "Hình Thức TT", "Khuyến mãi", "Điểm SD", "Tổng tiền", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.setRowHeight(30);
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "SL", "Giá bán", "Tổng tiền"
            }
        ));
        tblCTHD.setRowHeight(30);
        jScrollPane2.setViewportView(tblCTHD);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Lịch Sử Đơn Hàng");

        txtNgayBD.setDateFormatString("dd-MM-yyyy");

        txtNgayKT.setDateFormatString("dd-MM-yyyy");

        btnLoc.setText("Lọc");
        btnLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocMouseClicked(evt);
            }
        });

        cbo3ngayGanNhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo3ngayGanNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm Nay", "1 ngày trước", "2 ngày trước" }));
        cbo3ngayGanNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo3ngayGanNhapItemStateChanged(evt);
            }
        });
        cbo3ngayGanNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo3ngayGanNhapActionPerformed(evt);
            }
        });

        cboTinhTrangDH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboTinhTrangDH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ giao hàng", "Đã giao hàng", "Đã hủy" }));
        cboTinhTrangDH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhTrangDHItemStateChanged(evt);
            }
        });

        btnHienThiAll.setText("Hiển thi tất cả");
        btnHienThiAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiAllActionPerformed(evt);
            }
        });

        txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaHDKeyTyped(evt);
            }
        });

        jLabel2.setText("Mã HĐ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(cbo3ngayGanNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHienThiAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTinhTrangDH, 0, 143, Short.MAX_VALUE))
                        .addGap(384, 384, 384))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHienThiAll)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbo3ngayGanNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTinhTrangDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNgayKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLoc)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        String maHD = (String) tblHD.getValueAt(tblHD.getSelectedRow(), 0);
        fillTableCT(maHD);
    }//GEN-LAST:event_tblHDMouseClicked

    private void cbo3ngayGanNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo3ngayGanNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo3ngayGanNhapActionPerformed

    private void cbo3ngayGanNhapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo3ngayGanNhapItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    if (cbo3ngayGanNhap.getSelectedIndex() == 0) {
                        fillTableHD(null, null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"), null, Auth_KH.user.getSDT());
                    } else if (cbo3ngayGanNhap.getSelectedIndex() == 1) {
                        fillTableHD(null, null, null, XDate.toString(XDate.addDays(XDate.getDate(), -1), "yyyy-MM-dd"), null, Auth_KH.user.getSDT());
                    } else {
                        fillTableHD(null, null, null, XDate.toString(XDate.addDays(XDate.getDate(), -2), "yyyy-MM-dd"), null, Auth_KH.user.getSDT());
                    }
                    tblCTHD.removeAll();
                    gif.dispose();
                    this.stop();
                }

            }.start();
        }

    }//GEN-LAST:event_cbo3ngayGanNhapItemStateChanged

    private void cboTinhTrangDHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhTrangDHItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    if (cboTinhTrangDH.getSelectedIndex() == 0) {
                        fillTableHD(null, null, null, null, "1", Auth_KH.user.getSDT());
                    } else if (cboTinhTrangDH.getSelectedIndex() == 1) {
                        fillTableHD(null, null, null, null, "2", Auth_KH.user.getSDT());
                    } else {
                        fillTableHD(null, null, null, null, "0", Auth_KH.user.getSDT());
                    }
                    DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
                    model.setRowCount(0);
                    gif.dispose();
                    this.stop();
                }

            }.start();
        }


    }//GEN-LAST:event_cboTinhTrangDHItemStateChanged

    private void btnLocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocMouseClicked
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                if (txtNgayBD.getDate() == null && txtNgayKT.getDate() == null) {
                    fillTableHD(null, null, null, null, null, Auth_KH.user.getSDT());
                } else if (txtNgayBD.getDate() == null) {
                    fillTableHD(null, null, XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"), null, null, Auth_KH.user.getSDT());
                } else if (txtNgayKT.getDate() == null) {
                    fillTableHD(null, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), null, null, null, Auth_KH.user.getSDT());
                }
                DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
                model.setRowCount(0);
                gif.dispose();
                this.stop();
            }

        }.start();


    }//GEN-LAST:event_btnLocMouseClicked

    private void btnHienThiAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiAllActionPerformed
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                fillTableHD(null, null, null, null, null, Auth_KH.user.getSDT());
                DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
                model.setRowCount(0);
                gif.dispose();
                this.stop();
            }

        }.start();

    }//GEN-LAST:event_btnHienThiAllActionPerformed

    private void txtMaHDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyTyped

        if (evt.getKeyChar() == 10) {
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    fillTableHD(txtMaHD.getText(), null, null, null, null, Auth_KH.user.getSDT());
                    DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
                    model.setRowCount(0);
                    gif.dispose();
                    this.stop();
                }
            }.start();
        }
    }//GEN-LAST:event_txtMaHDKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHienThiAll;
    private javax.swing.JButton btnLoc;
    private javax.swing.JComboBox<String> cbo3ngayGanNhap;
    private javax.swing.JComboBox<String> cboTinhTrangDH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private HELP.Table tblCTHD;
    public static HELP.Table tblHD;
    private javax.swing.JTextField txtMaHD;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    // End of variables declaration//GEN-END:variables
}
