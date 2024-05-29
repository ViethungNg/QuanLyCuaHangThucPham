/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Children.DaiLog;

import DAO.CTPhieuNhapDAO;
import DAO.CTPhieuXuatDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.SanPhamDAO;
import Models.CTPhieuXuat;
import Models.PhieuXuat;
import Models.SanPham;
import GUI.Children.KeHangPanel;
import GUI.Children.SanPhamPanel;
import Utils.Auth;
import Utils.MsgBox;
import Utils.Validation;
import Utils.XDate;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class NhapSPLenKeDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form NhapSPLenKeDiaLog
     */
    public NhapSPLenKeDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon/icons8-grocery-store-32.png")));
        this.setLocationRelativeTo(null);
        init();
    }
    SanPhamDAO spDAO = new SanPhamDAO();
    PhieuXuatDAO pxDAO = new PhieuXuatDAO();
    CTPhieuXuatDAO ctPXDAO = new CTPhieuXuatDAO();
    public static boolean checkFill = false;
    
    public void init() {
        ckbutton(checkbutton);
        fillComboBoxSP();
    }
    
    public void fillTable(String maPX) {
        DefaultTableModel model = (DefaultTableModel) tblCTThem.getModel();
        model.setRowCount(0);
        List<CTPhieuXuat> listCTPX = ctPXDAO.selectbyMaPX(maPX);
        for (CTPhieuXuat ct : listCTPX) {
            SanPham sp = spDAO.selectbyID(ct.getMaSP());
            Object[] row = {ct.getMaSP(), sp.getTenSP(), ct.getSl()};
            model.addRow(row);
        }
    }
    
    public void fillComboBoxSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        List<SanPham> listSP = spDAO.getSanPhamDangKinhDoanh(1);
        for (SanPham sp : listSP) {
            if (sp.isTrangThai() == true) {
                model.addElement(sp);
            }
            
        }
    }
    
    public PhieuXuat getFromPX() {
        PhieuXuat px = new PhieuXuat();
        px.setNgayXuat(XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
        px.setMaNV(Auth.user.getMaNV());
        px.setMaKe("KH1");
        px.setGhiChu(txtGhiChu.getText());
        return px;
    }
    
    public CTPhieuXuat getFormCTPX() {
        CTPhieuXuat ctPX = new CTPhieuXuat();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        ctPX.setMaSP(sp.getMaSP());
        ctPX.setSl(Integer.parseInt(txtSL.getText()));
        ctPX.setMaPX(txtMaPX.getText());
        return ctPX;
    }
    
    public void taoPhieuXuat() {
        try {
            String maPX = pxDAO.insertPXRretunMaPX(getFromPX());
            txtMaPX.setText(maPX);
            MsgBox.alert(this, "Tạo phiếu thành công");
            checkbutton = 1;
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            ckbutton(checkbutton);
        } catch (Exception e) {
            MsgBox.alert(this, "Tạo phiếu thất bại !");
        }
    }
    
    public void themSP() {
        try {
            CTPhieuXuat ctPX = getFormCTPX();
            if (ctPX.getSl() > this.getSLTonKho()) {
                MsgBox.alert(this, "Số lượng thêm phải nhỏ hơn số lượng trong kho hiện có !");
                return;
            }
            CTPhieuXuat ctSelect = ctPXDAO.selectByMaPXmaSP(ctPX.getMaPX(), ctPX.getMaSP());
            if (ctSelect != null) {
                if (MsgBox.confrim(this, "Sản phẩm đã có trong phiếu bạn có muốn cập nhật lại không ?")) {
                    ctPXDAO.xoaSP(ctSelect);
                    ctPXDAO.themSP(getFormCTPX());
                    MsgBox.alert(this, "Cập nhật thành công");
                    fillTable(txtMaPX.getText());
                    lblSLTrongKho.setText(String.valueOf(this.getSLTonKho()));
                }
            } else {
                ctPXDAO.themSP(getFormCTPX());
                MsgBox.alert(this, "Thêm thành công");
                fillTable(txtMaPX.getText());
                lblSLTrongKho.setText(String.valueOf(this.getSLTonKho()));
            }
            
        } catch (Exception e) {
            MsgBox.alert(this, "Thất bại");
        }
    }
    
    public void xoaSP() {
        CTPhieuXuat ct = new CTPhieuXuat();
        int index = tblCTThem.getSelectedRow();
        ct.setMaSP((String) tblCTThem.getValueAt(index, 0));
        ct.setSl((Integer) tblCTThem.getValueAt(index, 2));
        ct.setMaPX(txtMaPX.getText());
        try {
            ctPXDAO.xoaSP(ct);
            MsgBox.alert(this, "Xóa sản phẩm thành công");
            fillTable(txtMaPX.getText());
            lblSLTrongKho.setText(String.valueOf(this.getSLTonKho()));
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa Sản Phẩm Thất bại");
            System.out.println(e);
        }
    }
    
    public int getSLTonKho() {
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        SanPham sl = spDAO.selectbyID(sp.getMaSP());
        return sl.getSLTonKho();
    }
    
    public void huyPhieuXuat() {
        try {
            for (int i = 0; i < tblCTThem.getRowCount(); i++) {
                CTPhieuXuat ctPX = new CTPhieuXuat();
                ctPX.setMaSP((String) tblCTThem.getValueAt(i, 0));
                ctPX.setSl((int) tblCTThem.getValueAt(i, 2));
                ctPX.setMaPX(txtMaPX.getText());
                ctPXDAO.xoaSP(ctPX);
            }
            pxDAO.delete(txtMaPX.getText());
            MsgBox.alert(this, "Hủy Thành Công");
            this.dispose();
        } catch (Exception e) {
            MsgBox.alert(this, "Hủy thất bại");
        }
        
    }
    
    public void hoanTat() {
        try {
            if (MsgBox.confrim(this, "Bạn thật sự hoàn tất thêm SP ?")) {
                List<CTPhieuXuat> ctPX = ctPXDAO.selectbyMaPX(txtMaPX.getText());
                pxDAO.hoanTatPhieu(txtGhiChu.getText(), txtMaPX.getText(), ctPX);
                checkFill = true;
                SanPhamPanel.fillTable();
                this.dispose();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Thất bại");
        }
    }
    int checkbutton = 0;
    
    public void ckbutton(int ck) {
        if (ck == 0) {
            btnTaoPhieu.setEnabled(true);
            btnHoanTat.setEnabled(false);
            btnHuy.setEnabled(false);
            btnXoaSP.setEnabled(false);
            btnThemSP.setEnabled(false);
            cboSanPham.setEnabled(false);
            txtSL.setEnabled(false);
            txtGhiChu.setEnabled(false);
        } else {
            btnTaoPhieu.setEnabled(false);
            btnHoanTat.setEnabled(true);
            btnHuy.setEnabled(true);
            btnXoaSP.setEnabled(true);
            btnThemSP.setEnabled(true);
            cboSanPham.setEnabled(true);
            txtSL.setEnabled(true);
            txtGhiChu.setEnabled(true);
            
        }
        
    }
    
    public boolean ckValidate() {
        
        if (Validation.isEmpty(txtSL, "Vui lòng nhập số lượng")) {
            return false;
        } else {
            if (Validation.isNumber(txtSL, "Vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtSL.getText()) <= 0) {
                MsgBox.alert(this, "Số lượng sai định dạng");
                return false;
            }
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTaoPhieu = new javax.swing.JButton();
        btnHoanTat = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaPX = new javax.swing.JTextField();
        lblSLTrongKho = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTThem = new HELP.Table();
        txtSL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnXoaSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        btnTaoPhieu.setBackground(new java.awt.Color(0, 102, 102));
        btnTaoPhieu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieu.setText("Tạo Phiếu");
        btnTaoPhieu.setBorder(null);
        btnTaoPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoPhieuMouseClicked(evt);
            }
        });
        jPanel1.add(btnTaoPhieu);

        btnHoanTat.setBackground(new java.awt.Color(0, 102, 102));
        btnHoanTat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHoanTat.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanTat.setText("Hoàn Tất");
        btnHoanTat.setBorder(null);
        btnHoanTat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanTatMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoanTat);

        btnHuy.setBackground(new java.awt.Color(204, 0, 0));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.setBorder(null);
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });
        jPanel1.add(btnHuy);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Xuất Sản Phẩm Lên kệ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Ghi Chú");

        cboSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSanPhamItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Số Lượng muốn thêm");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Chọn Sản Phẩm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("SL trong kho");

        txtMaPX.setEditable(false);

        lblSLTrongKho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSLTrongKho.setForeground(new java.awt.Color(204, 0, 51));
        lblSLTrongKho.setText("0");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        tblCTThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lương Thêm"
            }
        ));
        tblCTThem.setRowHeight(25);
        jScrollPane3.setViewportView(tblCTThem);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Mã Phiếu Xuất");

        jLabel11.setBackground(new java.awt.Color(0, 102, 102));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Danh Sách Sản Phẩm Xuất");
        jLabel11.setOpaque(true);

        btnXoaSP.setBackground(new java.awt.Color(204, 204, 0));
        btnXoaSP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoaSP.setForeground(new java.awt.Color(0, 102, 102));
        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.setBorder(null);
        btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseClicked(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(204, 204, 0));
        btnThemSP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(0, 102, 102));
        btnThemSP.setText("Thêm Sản Phẩm");
        btnThemSP.setBorder(null);
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 424, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSL, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(lblSLTrongKho)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel10)
                            .addComponent(lblSLTrongKho))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSanPhamItemStateChanged
        
        lblSLTrongKho.setText(String.valueOf(this.getSLTonKho()));

    }//GEN-LAST:event_cboSanPhamItemStateChanged

    private void btnTaoPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoPhieuMouseClicked
        taoPhieuXuat();
    }//GEN-LAST:event_btnTaoPhieuMouseClicked

    private void btnHoanTatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanTatMouseClicked
        if (tblCTThem.getRowCount() < 0) {
            MsgBox.alert(this, "Bạn chưa thêm sản phẩm nào !");
        } else {
            gifLoadDiaLog gif = new gifLoadDiaLog(null,false);
            gif.setVisible(true);
            new Thread(){
                @Override
                public void run() {
                     hoanTat();
                     gif.dispose();
                }
                
            }.start();
          
        }
        
    }//GEN-LAST:event_btnHoanTatMouseClicked

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
        
        huyPhieuXuat();
    }//GEN-LAST:event_btnHuyMouseClicked

    private void btnXoaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseClicked
        if (tblCTThem.getSelectedRow() > 0) {
            xoaSP();
        } else {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm để xóa !");
        }
        
    }//GEN-LAST:event_btnXoaSPMouseClicked

    private void btnThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseClicked
        if (ckValidate()) {
            themSP();
        }

    }//GEN-LAST:event_btnThemSPMouseClicked
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhapSPLenKeDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapSPLenKeDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapSPLenKeDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapSPLenKeDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhapSPLenKeDiaLog dialog = new NhapSPLenKeDiaLog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanTat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cboSanPham;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lblSLTrongKho;
    private HELP.Table tblCTThem;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaPX;
    private javax.swing.JTextField txtSL;
    // End of variables declaration//GEN-END:variables
}
