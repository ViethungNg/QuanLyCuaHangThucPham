/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.KeHangDAO;
import DAO.LoaiHangDAO;
import DAO.NhaCCDAO;
import DAO.SanPhamDAO;
import HELP.CellRenderer;
import MODEL_SWING.CellRendererImage;
import Models.LoaiHang;
import Models.NhaCC;
import Models.SanPham;
import UI.Children.DaiLog.TaoSPMoiDialog;
import UI.Children.DaiLog.gifLoadDiaLog;
import static GUI.Children.KeHangPanel.khDAO;
import GUI.MainJF;
import Utils.MsgBox;
import Utils.XExcel;
import Utils.XImage;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class SanPhamPanel extends javax.swing.JPanel {

    /**
     * Creates new form SanPham
     */
    public SanPhamPanel() {
        initComponents();
        init();
    }
    public static SanPhamDAO spDAO = new SanPhamDAO();
    LoaiHangDAO lhDAO = new LoaiHangDAO();
    NhaCCDAO nccDAO = new NhaCCDAO();
    public static KeHangDAO khDAO = new KeHangDAO();

    public void init() {
        fillTable();
        fillComboboxLoaiSP();
        fillComboboxNhaCC();
    }

    public void chonAnh() throws IOException {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file);
            anhTam = fis.readAllBytes();
            ImageIcon icon = XImage.readChon(file, 222, 237);
            lblAnhSP.setIcon(icon);
        }

    }

    public void fillComboboxNhaCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaCC.getModel();
        model.removeAllElements();
        List<NhaCC> nhaCC = nccDAO.selectAll();
        for (NhaCC ncc : nhaCC) {
            model.addElement(ncc);
        }
    }

    public static void fillTable() {
        spDAO.setListSP(spDAO.selectAll());
        tblListSP.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImage());
        DefaultTableModel model = (DefaultTableModel) tblListSP.getModel();
        model.setRowCount(0);
        List<SanPham> listSP = spDAO.getListSP();
        if (listSP != null) {
            BufferedImage bfimage[] = new BufferedImage[listSP.size()];
            for (int i = 0; i < listSP.size(); i++) {
                ByteArrayInputStream byteArray = new ByteArrayInputStream(listSP.get(i).getHinh());
                try {
                    bfimage[i] = ImageIO.read(byteArray);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                Object row[] = {listSP.get(i).getMaSP(), bfimage[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH), listSP.get(i).getTenSP(), listSP.get(i).getSLTonKho(), listSP.get(i).getSlTrenKe(), listSP.get(i).getGiaBan() + " VND", listSP.get(i).getGiaNhap() + " VND", listSP.get(i).getMaLoai(), listSP.get(i).getDonVi(), listSP.get(i).getQrCode(), listSP.get(i).isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh"};
                model.addRow(row);
            }
        }
    }

    public void locSP() {
        if (cboLocSP.getSelectedIndex() == 0) {
            fillTable();
        } else if (cboLocSP.getSelectedIndex() == 1) {
            tblListSP.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImage());
            DefaultTableModel model = (DefaultTableModel) tblListSP.getModel();
            model.setRowCount(0);
            List<SanPham> listSP = spDAO.getSanPhamDangKinhDoanh(1);
            if (listSP != null) {
                BufferedImage bfimage[] = new BufferedImage[listSP.size()];
                for (int i = 0; i < listSP.size(); i++) {
                    System.out.println(i);
                    ByteArrayInputStream byteArray = new ByteArrayInputStream(listSP.get(i).getHinh());
                    try {
                        bfimage[i] = ImageIO.read(byteArray);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    Object row[] = {listSP.get(i).getMaSP(), bfimage[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH), listSP.get(i).getTenSP(), listSP.get(i).getSLTonKho(), listSP.get(i).getSlTrenKe(), listSP.get(i).getGiaBan() + " VND", listSP.get(i).getGiaNhap() + " VND", listSP.get(i).getMaLoai(), listSP.get(i).getDonVi(), listSP.get(i).getQrCode(), listSP.get(i).isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh"};
                    model.addRow(row);
                }

            }

        } else {
            tblListSP.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImage());
            DefaultTableModel model = (DefaultTableModel) tblListSP.getModel();
            model.setRowCount(0);
            List<SanPham> listSP = spDAO.getSanPhamDangKinhDoanh(0);
            System.out.println(listSP.size());
            if (listSP != null) {
                BufferedImage bfimage[] = new BufferedImage[listSP.size()];
                for (int i = 0; i < listSP.size(); i++) {
                    ByteArrayInputStream byteArray = new ByteArrayInputStream(listSP.get(i).getHinh());
                    try {
                        bfimage[i] = ImageIO.read(byteArray);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    Object row[] = {listSP.get(i).getMaSP(), bfimage[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH), listSP.get(i).getTenSP(), listSP.get(i).getSLTonKho(), listSP.get(i).getSlTrenKe(), listSP.get(i).getGiaBan() + " VND", listSP.get(i).getGiaNhap() + " VND", listSP.get(i).getMaLoai(), listSP.get(i).getDonVi(), listSP.get(i).getQrCode(), listSP.get(i).isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh"};
                    model.addRow(row);
                }
            }
        }
    }

    public SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        sp.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        sp.setSLTonKho(Integer.parseInt(txtSLTrongKho.getText()));
        sp.setSlTrenKe(Integer.parseInt(txtSLTrenKe.getText()));
        LoaiHang lh = (LoaiHang) cboLoaiSP.getSelectedItem();
        sp.setMaLoai(lh.getMaLoai());
        NhaCC ncc = (NhaCC) cboNhaCC.getSelectedItem();
        sp.setMaNCC(ncc.getMaNCC());
        sp.setDonVi(txtDonVi.getText());
        sp.setQrCode(txtBarCode.getText());
        sp.setTrangThai(rdoDangKD.isSelected() ? true : false);
        sp.setHinh(anhTam);

        anhTam = null;
        return sp;
    }

    public SanPhamPanel(ButtonGroup bgrTinhTrang, JLabel btnCapNhap, JLabel btnMoi, JLabel btnXoa, JComboBox<String> cboLoaiSP, JButton jButton2, JButton jButton3, JButton jButton4, JComboBox<String> jComboBox1, JLabel jLabel12, JLabel jLabel30, JLabel jLabel31, JLabel jLabel32, JLabel jLabel33, JLabel jLabel34, JLabel jLabel35, JLabel jLabel36, JLabel jLabel37, JLabel jLabel38, JLabel jLabel39, JLabel jLabel40, JPanel jPanel1, JPanel jPanel10, JPanel jPanel11, JPanel jPanel2, JPanel jPanel3, JPanel jPanel4, JPanel jPanel6, JScrollPane jScrollPane2, JTextField jTextField1, JLabel lblAnhSP, JRadioButton rdoDangKD, JRadioButton rdoNgungKD, JTabbedPane tabpanel, JTextField txtBarCode, JTextField txtDonVi, JTextField txtGiaBan, JTextField txtGiaNhap, JTextField txtMaSP, JTextField txtSLTrenKe, JTextField txtSLTrongKho, JTextField txtTenSP) {
        this.bgrTinhTrang = bgrTinhTrang;
        this.btnCapNhap = btnCapNhap;
        this.btnMoi = btnMoi;

        this.cboNhaCC = cboLoaiSP;
//        this.jButton2 = jButton2;
        this.btnTimKiem = jButton3;
        this.jButton4 = jButton4;

        this.jLabel12 = jLabel12;
        this.jLabel30 = jLabel30;
        this.jLabel31 = jLabel31;
        this.jLabel32 = jLabel32;
        this.jLabel33 = jLabel33;
        this.jLabel34 = jLabel34;
        this.jLabel35 = jLabel35;
        this.jLabel36 = jLabel36;
        this.jLabel37 = jLabel37;
        this.jLabel38 = jLabel38;
        this.jLabel39 = jLabel39;
        this.jLabel40 = jLabel40;
        this.jPanel1 = jPanel1;
        this.jPanel10 = jPanel10;
        this.jPanel11 = jPanel11;
        this.jPanel2 = jPanel2;
        this.jPanel3 = jPanel3;
        this.jPanel4 = jPanel4;
        this.jPanel6 = jPanel6;
        this.jScrollPane2 = jScrollPane2;
        this.jTextField1 = jTextField1;
        this.lblAnhSP = lblAnhSP;
        this.rdoDangKD = rdoDangKD;
        this.rdoNgungKD = rdoNgungKD;
        this.tabpanel = tabpanel;
        this.txtBarCode = txtBarCode;
        this.txtDonVi = txtDonVi;
        this.txtGiaBan = txtGiaBan;
        this.txtGiaNhap = txtGiaNhap;
        this.txtMaSP = txtMaSP;
        this.txtSLTrenKe = txtSLTrenKe;
        this.txtSLTrongKho = txtSLTrongKho;
        this.txtTenSP = txtTenSP;
    }
    public byte[] anhTam = null;

    public void setForm(SanPham sp) {
        txtTenSP.setText(sp.getTenSP());
        txtMaSP.setText(sp.getMaSP());
        if (sp.getGiaBan() == null) {
            txtGiaBan.setText("");
        } else {
            txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
        }
        if (sp.getGiaNhap() == null) {
            txtGiaNhap.setText("");
        } else {
            txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        }
        txtSLTrenKe.setText(String.valueOf(sp.getSlTrenKe()));
        txtSLTrongKho.setText(String.valueOf(sp.getSLTonKho()));
        LoaiHang lh = lhDAO.selectbyID(sp.getMaLoai());
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel();
        model.setSelectedItem(lh);

        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cboNhaCC.getModel();
        NhaCC ncc = nccDAO.selectbyID(sp.getMaNCC());
        model2.setSelectedItem(ncc);
        txtDonVi.setText(sp.getDonVi());
        txtBarCode.setText(sp.getQrCode());
        try {
            lblAnhSP.setIcon(XImage.read(sp.getHinh()));
            anhTam = sp.getHinh();
        } catch (IOException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (sp.isTrangThai()) {
            rdoDangKD.setSelected(true);
        } else {
            rdoNgungKD.setSelected(true);
        }
    }

    public void fillComboboxLoaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel();
        model.removeAllElements();
        List<LoaiHang> listLH = lhDAO.selectAll();
        for (LoaiHang lh : listLH) {
            model.addElement(lh);
        }
    }

    public void update() {
        SanPham sp = this.getForm();
        try {
            if (MsgBox.confrim(this, "Bạn thật sự muốn update ?")) {
                spDAO.update(sp);
                MsgBox.alert(this, "Update thành công");
                this.clearForm();
                fillTable();
                khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, null, null, null, null));
                BanHangPanel.fillTableSPDangBan();
                KeHangPanel.fillTable();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Update thất bại!");
            System.out.println(e);
        }

    }

    public void clearForm() {
        SanPham sp = new SanPham();
        setForm(sp);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTinhTrang = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tabpanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListSP = new HELP.Table();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSLTrongKho = new javax.swing.JTextField();
        txtSLTrenKe = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtBarCode = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        cboNhaCC = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        btnCapNhap = new javax.swing.JLabel();
        btnMoi = new javax.swing.JLabel();
        lblAnhSP = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        rdoDangKD = new javax.swing.JRadioButton();
        rdoNgungKD = new javax.swing.JRadioButton();
        jLabel41 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        cboLocSP = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1012, 900));
        setPreferredSize(new java.awt.Dimension(1012, 627));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("Tạo mới SP");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 102, 102));
        jButton5.setText("Xuất Excel");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpanel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabpanel.setMinimumSize(new java.awt.Dimension(81, 46));
        tabpanel.setPreferredSize(new java.awt.Dimension(1300, 761));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Sản Phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tblListSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Ảnh SP", "Tên Sản Phẩm", "SL Trong Kho", "SL Trên kệ", "Giá Bán", "Giá Nhập", "Loại Hàng", "Đơn Vị", "BarCode", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Byte.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblListSP.setGridColor(new java.awt.Color(153, 153, 153));
        tblListSP.setRowHeight(80);
        tblListSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListSPMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblListSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        tabpanel.addTab("Danh Sách", jPanel3);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 102));
        jLabel30.setText("Mã SP:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 102));
        jLabel31.setText("Tên SP:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 102));
        jLabel32.setText("Giá Nhập:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 102));
        jLabel33.setText("Giá bán:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 102));
        jLabel34.setText("SL Sản Phẩm Trong Kho:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 102));
        jLabel35.setText("SL Sản Phẩm Trên Kệ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 102, 102));
        jLabel36.setText("Loại SP:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 102, 102));
        jLabel38.setText("BarCode:");

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSP.setMinimumSize(new java.awt.Dimension(7, 25));

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenSP.setMinimumSize(new java.awt.Dimension(7, 25));

        txtGiaNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGiaNhap.setMinimumSize(new java.awt.Dimension(7, 25));
        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        txtGiaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGiaBan.setMinimumSize(new java.awt.Dimension(7, 25));

        txtSLTrongKho.setEditable(false);
        txtSLTrongKho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSLTrongKho.setMinimumSize(new java.awt.Dimension(7, 25));
        txtSLTrongKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLTrongKhoActionPerformed(evt);
            }
        });

        txtSLTrenKe.setEditable(false);
        txtSLTrenKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSLTrenKe.setMinimumSize(new java.awt.Dimension(7, 25));

        txtDonVi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDonVi.setMinimumSize(new java.awt.Dimension(7, 25));

        txtBarCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBarCode.setMinimumSize(new java.awt.Dimension(7, 25));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 102, 102));
        jLabel39.setText("Đơn Vị:");

        cboNhaCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnCapNhap.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCapNhap.setText("Cập Nhật");
        btnCapNhap.setOpaque(true);
        btnCapNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCapNhapMousePressed(evt);
            }
        });
        jPanel10.add(btnCapNhap);

        btnMoi.setBackground(new java.awt.Color(0, 102, 102));
        btnMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMoi.setText("Mới");
        btnMoi.setOpaque(true);
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMoiMousePressed(evt);
            }
        });
        jPanel10.add(btnMoi);

        lblAnhSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAnhSPMousePressed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 102));
        jLabel40.setText("Tình Trạng:");

        bgrTinhTrang.add(rdoDangKD);
        rdoDangKD.setText("Đang kinh doanh");

        bgrTinhTrang.add(rdoNgungKD);
        rdoNgungKD.setText("Ngừng kinh doanh");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 102));
        jLabel41.setText("Nhà cung cấp");

        cboLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSLTrongKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDonVi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBarCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSLTrenKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rdoDangKD)
                                .addGap(5, 5, 5)
                                .addComponent(rdoNgungKD))))
                    .addComponent(cboNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel37))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel30)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel31)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel32)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel33)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel34)
                            .addComponent(txtSLTrongKho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel35)
                            .addComponent(txtSLTrenKe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel36)
                            .addComponent(rdoDangKD)
                            .addComponent(rdoNgungKD)
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(cboNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39))
                                .addGap(10, 10, 10)
                                .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabpanel.addTab("Chi Tiết", jPanel4);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 102, 204));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Nhập mã/ Tên");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 204, 255)));

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboLocSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả sản phẩm", "Đang kinh doanh", "Ngừng kinh doanh" }));
        cboLocSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocSPItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addComponent(cboLocSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TaoSPMoiDialog spMoi = new TaoSPMoiDialog(null, true);
        spMoi.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (TaoSPMoiDialog.kt == true) {
//                        MainJF.ListSanPham = spDAO.selectAll();
                        fillTable();
                        TaoSPMoiDialog.kt = false;
                        break;
                    }
                }
            }

        }.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSLTrongKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLTrongKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLTrongKhoActionPerformed

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhapActionPerformed

    private void tblListSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListSPMousePressed
        if (evt.getClickCount() == 2) {
            tabpanel.setSelectedIndex(1);
            for (SanPham sp : spDAO.getListSP()) {
                if (sp.getMaSP().equals(tblListSP.getValueAt(tblListSP.getSelectedRow(), 0))) {
                    setForm(sp);
                }
            }
        }
    }//GEN-LAST:event_tblListSPMousePressed

    private void btnCapNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhapMousePressed
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                update();
                gif.dispose();
                this.stop();
            }
        }.start();


    }//GEN-LAST:event_btnCapNhapMousePressed

    private void lblAnhSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhSPMousePressed
        try {
            chonAnh();
        } catch (IOException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblAnhSPMousePressed

    private void btnMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMousePressed
        clearForm();
    }//GEN-LAST:event_btnMoiMousePressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboLocSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocSPItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    locSP();
                    gif.dispose();
                }
            }.start();
        }

    }//GEN-LAST:event_cboLocSPItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        XExcel<SanPham> excel = new XExcel<>();
        excel.setType(XExcel.ListType.SANPHAM);
        JFileChooser jf = new JFileChooser();
        String title[] = {"Mã SP", "Tên Sản Phẩm", "Số lượng trong kho", "Số lượng trên kệ", "Giá bán", "Giá nhập", "Loại hàng", "Đơn Vị", "BarCode", "Trạng thái"};
        List<SanPham> listSP = spDAO.getListSP();
        if (jf.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            excel.createFileExecel(jf.getSelectedFile(), title, listSP);
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTinhTrang;
    private javax.swing.JLabel btnCapNhap;
    private javax.swing.JLabel btnMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboLocSP;
    private javax.swing.JComboBox<String> cboNhaCC;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JRadioButton rdoDangKD;
    private javax.swing.JRadioButton rdoNgungKD;
    private javax.swing.JTabbedPane tabpanel;
    public static HELP.Table tblListSP;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSLTrenKe;
    private javax.swing.JTextField txtSLTrongKho;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
