/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.CTPhieuNhapDAO;
import DAO.NhaCCDAO;
import DAO.NhanVienDAO;
import DAO.PhieuNhapDAO;
import Models.NhaCC;
import Models.NhanVien;
import Models.PhieuNhap;
import UI.Children.DaiLog.NhapKhoDiaLog;
import Utils.DDTienTe;
import Utils.MsgBox;
import Utils.XDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class NhapKhoPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhapKhoPanel
     */
    public NhapKhoPanel() {
        initComponents();
        init();
    }
    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    public static NhaCCDAO nccDAO = new NhaCCDAO();
    public static NhanVienDAO nvDAO = new NhanVienDAO();

    public void init() {
        fillTablePhieuNhap(null, null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"), null);
    }

    CTPhieuNhapDAO CTphieuNhapDAO = new CTPhieuNhapDAO();

    public void fillTablePhieuNhap(String MaPN, String ngayBD, String ngayKT, String theoNgay, String maNCC) {
        DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
        model.setRowCount(0);
        List<PhieuNhap> listPN = phieuNhapDAO.selectPhieuNhapAndTimKiem(MaPN, ngayBD, ngayKT, theoNgay, maNCC);
        for (PhieuNhap pn : listPN) {
            NhaCC ncc = nccDAO.selectbyID(pn.getMaNCC());
            NhanVien nv = nvDAO.selectbyID(pn.getMaNV());
            Object row[] = {pn.getMaPhieuNhap(), ncc.getTenNCC(), pn.getNgayNhap(), DDTienTe.FormatVND(pn.getTongTien()), nv.getHoTen(), pn.getGhiChu()};
            model.addRow(row);
        }
    }

    public void fillCTPhieuNhap(String maPN) {
        DefaultTableModel model = (DefaultTableModel) tblCTPhieuNhap.getModel();
        model.setRowCount(0);
        List<Object[]> listCTPN = CTphieuNhapDAO.selectCTbyMaPN(maPN);
        for (Object[] pn : listCTPN) {
            Object row[] = {pn[0], pn[1], DDTienTe.FormatVND((Double) pn[2]), pn[3], pn[4], pn[5], pn[6], DDTienTe.FormatVND((Double) pn[7])};
            model.addRow(row);
        }
    }

    public void fillChiTiet() {
        String maPN = (String) tblPhieuNhap.getValueAt(tblPhieuNhap.getSelectedRow(), 0);
        fillCTPhieuNhap(maPN);
        lblMaPN.setText("Mã Phiếu Nhập: PN" + maPN);
        Tabpanel.setSelectedIndex(1);
    }

    public void timKiem() {
        PhieuNhap pn = phieuNhapDAO.selectbyID(txtTimKiem.getText());
        if (pn == null) {
            MsgBox.alert(this, "Không tìm thấy phiếu nhập");
            return;
        } else {
            System.out.println(pn.getMaPhieuNhap());
            fillTablePhieuNhap(pn.getMaPhieuNhap(), null, null, null, null);
        }
    }

    public void checkFillTable() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (NhapKhoDiaLog.checkNhapKho == true) {
                        fillTablePhieuNhap(null, null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"), null);
//                       KeHangPanel.fillTable();
                        cboTheoNgay.setSelectedIndex(0);
                        SanPhamPanel.fillTable();
                        NhapKhoDiaLog.checkNhapKho = false;
                        break;
                    }
                }
            }

        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Tabpanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPhieuNhap = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblMaPN = new javax.swing.JLabel();
        btnNhapSP = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        cboTheoNgay = new javax.swing.JComboBox<>();
        btnTatCa = new javax.swing.JButton();

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 204)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(0, 102, 102));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtNgayBD.setDateFormatString("dd-MM-yyyy");

        txtNgayKT.setDateFormatString("dd-MM-yyyy");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Từ ngày");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Đến ngày");

        Tabpanel.setForeground(new java.awt.Color(0, 204, 255));
        Tabpanel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        tblPhieuNhap.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu", "Nhà cung cấp", "Ngày nhập", "Tổng tiền", "Tên nhân viên", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.setRowHeight(25);
        tblPhieuNhap.setShowGrid(true);
        tblPhieuNhap.setShowVerticalLines(false);
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(222, 39));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Phiếu Nhập Kho");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(811, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        Tabpanel.addTab("Hóa đơn", jPanel3);

        tblCTPhieuNhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblCTPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Giá Nhập", "Số Lượng", "Đơn vị", "Ngày Sản Xuất", "Ngày Hết Hạn", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPhieuNhap.setRowHeight(25);
        tblCTPhieuNhap.setShowGrid(false);
        tblCTPhieuNhap.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tblCTPhieuNhap);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        lblMaPN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaPN.setForeground(new java.awt.Color(255, 255, 255));
        lblMaPN.setText("Mã Phiếu Nhập :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaPN, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tabpanel.addTab("Chi Tiết", jPanel4);

        btnNhapSP.setBackground(new java.awt.Color(0, 102, 102));
        btnNhapSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNhapSP.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNhapSP.setText("NHẬP SẢN PHẨM");
        btnNhapSP.setOpaque(true);
        btnNhapSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNhapSPMousePressed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(0, 102, 102));
        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cboTheoNgay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboTheoNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "1 Ngày trước", "2 Ngày trước" }));
        cboTheoNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoNgayItemStateChanged(evt);
            }
        });

        btnTatCa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTatCa.setForeground(new java.awt.Color(0, 102, 102));
        btnTatCa.setText("Tất cả");
        btnTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(75, 75, 75)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Tabpanel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cboTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLoc)
                                .addComponent(btnTatCa))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(Tabpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhập Kho", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnNhapSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapSPMousePressed
        NhapKhoDiaLog nhapKho = new NhapKhoDiaLog(null, true);
        nhapKho.setVisible(true);
        checkFillTable();
    }//GEN-LAST:event_btnNhapSPMousePressed

    private void tblPhieuNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMousePressed
        if (evt.getClickCount() == 2) {
            fillChiTiet();
        }
    }//GEN-LAST:event_tblPhieuNhapMousePressed

    private void cboTheoNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoNgayItemStateChanged
        if (cboTheoNgay.getSelectedIndex() == 0) {
            fillTablePhieuNhap(null, null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"), null);
        } else if (cboTheoNgay.getSelectedIndex() == 1) {
            fillTablePhieuNhap(null, null, null, XDate.toString(XDate.addDays(XDate.getDate(), -1), "yyyy-MM-dd"), null);
        } else {
            fillTablePhieuNhap(null, null, null, XDate.toString(XDate.addDays(XDate.getDate(), -2), "yyyy-MM-dd"), null);
        }
    }//GEN-LAST:event_cboTheoNgayItemStateChanged

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaActionPerformed
        fillTablePhieuNhap(null, null, null, null, null);
    }//GEN-LAST:event_btnTatCaActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        if (txtNgayBD.getDate() == null && txtNgayBD.getDate() == null) {
            fillTablePhieuNhap(null, null, null, null, null);
        } else if (txtNgayBD.getDate() == null) {
            fillTablePhieuNhap(null, null, XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"), null, null);

        } else if (txtNgayKT.getDate() == null) {
            fillTablePhieuNhap(null, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), null, null, null);
        } else {
            fillTablePhieuNhap(null, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"), null, null);
        }
    }//GEN-LAST:event_btnLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabpanel;
    private javax.swing.JButton btnLoc;
    private javax.swing.JLabel btnNhapSP;
    private javax.swing.JButton btnTatCa;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboTheoNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMaPN;
    private javax.swing.JTable tblCTPhieuNhap;
    private javax.swing.JTable tblPhieuNhap;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
