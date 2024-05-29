/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Children.DaiLog;

import DAO.CTPhieuNhapDAO;
import DAO.NhaCCDAO;
import DAO.SanPhamDAO;
import DAO.PhieuNhapDAO;
import Models.CTPhieuNhap;
import Models.NhaCC;
import Models.PhieuNhap;
import Models.SanPham;
import GUI.Children.NhapKhoPanel;
import GUI.Children.SanPhamPanel;
import Utils.Auth;
import Utils.JDBCHelper;
import Utils.MsgBox;
import Utils.Validation;
import Utils.XDate;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.validation.Validator;

/**
 *
 * @author TIEN SY
 */
public class NhapKhoDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form NhapKhoDiaLog
     */
    public NhapKhoDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon/icons8-grocery-store-32.png")));
        this.setLocationRelativeTo(null);
        init();
    }
    int ckButton = 0;
    public static boolean checkNhapKho = false;
    NhaCCDAO nhaCCDAO = new NhaCCDAO();
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    CTPhieuNhapDAO CTPhieuNhapDAO = new CTPhieuNhapDAO();

    public void init() {
        fillNhaCC();
        checkButton(ckButton);
    }

    public void fillNhaCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaCC.getModel();
        model.removeAllElements();
        List<NhaCC> listNCC = nhaCCDAO.selectAll();
        for (NhaCC ncc : listNCC) {
            if (ncc.isTrangThai() == true) {
                model.addElement(ncc);
            }

        }
        fillComboboxSanPham();
    }

    public void fillComboboxSanPham() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        NhaCC ncc = (NhaCC) cboNhaCC.getSelectedItem();
        List<SanPham> listSP = sanPhamDAO.selectSPbyNCC(ncc.getMaNCC());
        for (SanPham sp : listSP) {
            if (sp.isTrangThai() == true) {
                model.addElement(sp);
            }

        }
    }

    public void getGiaNhap() {
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        if (sp != null) {
            txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        }
    }

    public PhieuNhap getFormPhieuNhap() {
        PhieuNhap phieuNhap = new PhieuNhap();
        phieuNhap.setMaPhieuNhap(txtNgayNhap.getText());
        Date now = new Date();
        phieuNhap.setNgayNhap(XDate.toString(now, "yyyy-MM-dd"));
        phieuNhap.setMaNV(Auth.user.getMaNV());
        phieuNhap.setMaKho("KHO1");
        NhaCC ncc = (NhaCC) cboNhaCC.getSelectedItem();
        phieuNhap.setMaNCC(ncc.getMaNCC());
        phieuNhap.setGhiChu(txtGhiChu.getText());
        return phieuNhap;
    }
    PhieuNhap pn;

    public void taoPhieuNhap() {
        pn = phieuNhapDAO.insertPhieuNhap(getFormPhieuNhap());
        txtMaPN.setText(pn.getMaPhieuNhap());
        txtNgayNhap.setText(pn.getNgayNhap());
        ckButton = 1;
        checkButton(ckButton);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void huyPhieuNhap() {
        try {
            for (int i = 0; i < tblListCTSP.getRowCount(); i++) {
                CTPhieuNhap ct = new CTPhieuNhap();
                ct.setMaSP((String) tblListCTSP.getValueAt(i, 0));
                ct.setSl((Integer) tblListCTSP.getValueAt(i, 1));
                ct.setMaPN(txtMaPN.getText());
                CTPhieuNhapDAO.xoaSPCTPhieu(ct);
            }
            phieuNhapDAO.delete(pn.getMaPhieuNhap());
            MsgBox.alert(this, "Hủy Thành Công");
            this.dispose();
        } catch (Exception e) {
            MsgBox.alert(this, "Hủy Thất Bại");
            System.out.println(e);
        }
    }

    public CTPhieuNhap getCTPhieuNhap() {
        CTPhieuNhap ctPhieuNhap = new CTPhieuNhap();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        ctPhieuNhap.setMaSP(sp.getMaSP());
        ctPhieuNhap.setSl(Integer.parseInt(txtSoLuong.getText()));
        ctPhieuNhap.setMaPN(pn.getMaPhieuNhap());
        ctPhieuNhap.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        ctPhieuNhap.setNgaySX(XDate.toString(txtNgaySX.getDate(), "yyyy-MM-dd"));
        ctPhieuNhap.setNgayHH(XDate.toString(txtNgayHH.getDate(), "yyyy-MM-dd"));
        return ctPhieuNhap;
    }

    public void themSP() {
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        try {
            CTPhieuNhap ct = CTPhieuNhapDAO.SelectBymaPNmaSP(sp.getMaSP(), pn.getMaPhieuNhap());
            if (ct != null) {
                if (MsgBox.confrim(this, "Sản Phẩm Đã Có Trong Hóa Đơn Bạn Có Muốn Cập Nhật Lại Không ?")) {
                    CTPhieuNhapDAO.xoaSPCTPhieu(ct);
                    CTPhieuNhapDAO.insert(getCTPhieuNhap());
                    fillTableCTPhieuNhap();
                    txtTongTien.setText(String.valueOf(getTongTien()));
                }
            } else {
                CTPhieuNhapDAO.insert(getCTPhieuNhap());
                fillTableCTPhieuNhap();
                txtTongTien.setText(String.valueOf(getTongTien()));
            }

        } catch (Exception e) {
            System.out.println("lỗi");
            System.out.println(e);
        }

    }

    public Double getTongTien() {
        List<CTPhieuNhap> listCT = CTPhieuNhapDAO.selectCTPNbyMaPN(pn.getMaPhieuNhap());
        Double tongTien = 0.0;
        for (CTPhieuNhap ctPN : listCT) {
            tongTien = tongTien + ctPN.getTongTien();
        }
        return tongTien;
    }

    public void fillTableCTPhieuNhap() {
        DefaultTableModel model = (DefaultTableModel) tblListCTSP.getModel();
        model.setRowCount(0);
        List<CTPhieuNhap> listCT = CTPhieuNhapDAO.selectCTPNbyMaPN(pn.getMaPhieuNhap());

        for (CTPhieuNhap sp : listCT) {
            Object row[] = {sp.getMaSP(), sp.getSl(), sp.getGiaNhap(), sp.getNgaySX(), sp.getNgayHH(), sp.getTongTien()};
            model.addRow(row);
        }
    }

    public void xoaSPChiTiet() {
        CTPhieuNhap ctPN = new CTPhieuNhap();
       if(tblListCTSP.getSelectedRow() <0){
           MsgBox.alert(this,"Vui lòng chọn sản phẩm để xóa !");
           return;
       }
        ctPN.setMaSP((String) tblListCTSP.getValueAt(tblListCTSP.getSelectedRow(), 0));
        ctPN.setSl((Integer) tblListCTSP.getValueAt(tblListCTSP.getSelectedRow(), 1));
        ctPN.setMaPN(txtMaPN.getText());
        try {
            CTPhieuNhapDAO.xoaSPCTPhieu(ctPN);
            fillTableCTPhieuNhap();
            MsgBox.alert(this, "Xóa Thành Công");
            txtTongTien.setText(String.valueOf(getTongTien()));
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa Thất Bại !");
            System.out.println(e);
        }
    }

    public void checkButton(int ck) {
        if (ck == 1) {
            txtSoLuong.setEnabled(true);
            cboSanPham.setEnabled(true);
            txtNgaySX.setEnabled(true);
            txtNgayHH.setEnabled(true);
            btnThemSP.setEnabled(true);
            btnXoaSP.setEnabled(true);
            btnHoaTat.setEnabled(true);
            btnHuy.setEnabled(true);
            btnTaoPhieu.setEnabled(false);
            cboNhaCC.setEnabled(false);
        } else if (ck == 0) {
            cboNhaCC.setEnabled(true);

            txtSoLuong.setEnabled(false);
            cboSanPham.setEnabled(false);
            txtNgaySX.setEnabled(false);
            txtNgayHH.setEnabled(false);
            btnThemSP.setEnabled(false);
            btnXoaSP.setEnabled(false);
            btnHoaTat.setEnabled(false);
            btnHuy.setEnabled(false);
        }
    }

    public boolean ckValidate() {

        if (Validation.isEmpty(txtSoLuong, "Vui lòng nhập số lượng")) {
            return false;
        } else {
            if (Validation.isNumber(txtSoLuong, "Vui lòng nhập số")) {
                return false;
            } else if (Integer.parseInt(txtSoLuong.getText()) <= 0) {
                MsgBox.alert(this, "Số lượng sai định dạng");
                return false;
            }
        }

        if (txtNgaySX.getDate() == null || txtNgayHH.getDate() == null) {
            MsgBox.alert(this, "Không được bỏ trống ngày sx và ngày hết hạn");
            return false;
        } else {
            if (Validation.ktNgay(txtNgaySX, "Ngày sản xuất phải trước ngày nhập !")) {
                return false;
            }

        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListCTSP = new javax.swing.JTable();
        cboSanPham = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        txtNgayNhap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtTongTien = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnTaoPhieu = new javax.swing.JLabel();
        btnHoaTat = new javax.swing.JLabel();
        btnHuy = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboNhaCC = new javax.swing.JComboBox<>();
        txtNgaySX = new com.toedter.calendar.JDateChooser();
        txtNgayHH = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMaPN = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu Nhập Kho");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Phiếu Nhập Sản Phẩm Vào Kho");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mã phiếu nhập");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ngày Nhập ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Ghi Chú");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tổng Tiền");

        tblListCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Số Lượng", "Giá Nhập", "Ngày Sản Xuất", "Ngày Hết Hạn", "Tổng Tiền"
            }
        ));
        jScrollPane1.setViewportView(tblListCTSP);

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Sản Phẩm" }));
        cboSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSanPhamItemStateChanged(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(204, 204, 0));
        btnThemSP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(0, 102, 102));
        btnThemSP.setText("Thêm Sản Phẩm");
        btnThemSP.setBorder(null);
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemSPMousePressed(evt);
            }
        });
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnXoaSP.setBackground(new java.awt.Color(204, 204, 0));
        btnXoaSP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoaSP.setForeground(new java.awt.Color(0, 102, 102));
        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.setBorder(null);
        btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaSPMousePressed(evt);
            }
        });

        txtNgayNhap.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Chọn Sản Phẩm");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Số Lượng");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Danh Sách Sản Phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        txtGiaNhap.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Giá Nhập");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        txtTongTien.setEditable(false);

        jPanel2.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        btnTaoPhieu.setBackground(new java.awt.Color(0, 102, 102));
        btnTaoPhieu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTaoPhieu.setText("TẠO PHIẾU");
        btnTaoPhieu.setOpaque(true);
        btnTaoPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTaoPhieuMousePressed(evt);
            }
        });
        jPanel2.add(btnTaoPhieu);

        btnHoaTat.setBackground(new java.awt.Color(0, 102, 102));
        btnHoaTat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHoaTat.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaTat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHoaTat.setText("Hoàn tất");
        btnHoaTat.setOpaque(true);
        btnHoaTat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHoaTatMousePressed(evt);
            }
        });
        jPanel2.add(btnHoaTat);

        btnHuy.setBackground(new java.awt.Color(255, 51, 0));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHuy.setText("Hủy");
        btnHuy.setOpaque(true);
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHuyMousePressed(evt);
            }
        });
        jPanel2.add(btnHuy);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nhà Cung Cấp");

        cboNhaCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNhaCCItemStateChanged(evt);
            }
        });
        cboNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhaCCActionPerformed(evt);
            }
        });

        txtNgaySX.setDateFormatString("dd-MM-yyyy");

        txtNgayHH.setDateFormatString("dd-MM-yyyy");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("Ngày Sản Xuất");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Ngày Hết Hạn");

        txtMaPN.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNgaySX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgayHH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 25, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(cboNhaCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaPN)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cboNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgaySX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void cboNhaCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNhaCCItemStateChanged
        fillComboboxSanPham();
    }//GEN-LAST:event_cboNhaCCItemStateChanged

    private void cboSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSanPhamItemStateChanged
        getGiaNhap();
    }//GEN-LAST:event_cboSanPhamItemStateChanged

    private void cboNhaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhaCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNhaCCActionPerformed

    private void btnTaoPhieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoPhieuMousePressed
        taoPhieuNhap();

    }//GEN-LAST:event_btnTaoPhieuMousePressed

    private void btnHuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMousePressed
        huyPhieuNhap();
    }//GEN-LAST:event_btnHuyMousePressed

    private void btnThemSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMousePressed
        if (ckValidate()) {
            themSP();
        }

    }//GEN-LAST:event_btnThemSPMousePressed

    private void btnXoaSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMousePressed
        xoaSPChiTiet();
    }//GEN-LAST:event_btnXoaSPMousePressed

    private void btnHoaTatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaTatMousePressed
        if (tblListCTSP.getRowCount() == 0) {
            MsgBox.alert(this, "Phiếu nhập phải trên 1 sản phẩm");
            return;
        } else {
            this.dispose();
            checkNhapKho = true;
        }

    }//GEN-LAST:event_btnHoaTatMousePressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(NhapKhoDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapKhoDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapKhoDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapKhoDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhapKhoDiaLog dialog = new NhapKhoDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnHoaTat;
    private javax.swing.JLabel btnHuy;
    private javax.swing.JLabel btnTaoPhieu;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cboNhaCC;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable tblListCTSP;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaPN;
    private com.toedter.calendar.JDateChooser txtNgayHH;
    private javax.swing.JTextField txtNgayNhap;
    private com.toedter.calendar.JDateChooser txtNgaySX;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
