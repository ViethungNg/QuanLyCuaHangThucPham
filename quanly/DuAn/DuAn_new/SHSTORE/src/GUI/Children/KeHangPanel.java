/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.CTPhieuXuatDAO;
import DAO.GGSanPhamDAO;
import DAO.KeHangDAO;
import DAO.LoaiHangDAO;
import DAO.NhanVienDAO;
import DAO.PhieuXuatDAO;
import DAO.SanPhamDAO;
import HELP.CellRenderer;
import MODEL_SWING.CellRendererImage;
import Models.CTPhieuXuat;
import Models.GGSanPham;
import Models.LoaiHang;
import Models.NhanVien;
import Models.PhieuXuat;
import Models.SanPham;
import UI.Children.DaiLog.NhapSPLenKeDiaLog;
import UI.Children.DaiLog.gifLoadDiaLog;
import static GUI.Children.SanPhamPanel.tblListSP;
import GUI.MainJF;
import Utils.DDTienTe;
import Utils.MsgBox;
import Utils.XDate;
import Utils.XExcel;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class KeHangPanel extends javax.swing.JPanel {

    /**
     * Creates new form KeHangPanel
     */
    public KeHangPanel() {
        initComponents();
        init();
    }
    public static KeHangDAO khDAO = new KeHangDAO();
    public static LoaiHangDAO lhDAO = new LoaiHangDAO();
    public static PhieuXuatDAO pxDAO = new PhieuXuatDAO();
    public static CTPhieuXuatDAO ctPXDAO = new CTPhieuXuatDAO();
    public static NhanVienDAO nvDAO = new NhanVienDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    gifLoadDiaLog gif = new gifLoadDiaLog(null, false);

    public static void init() {
        fillTable();
        soMatHangTrenKe();
        fillTableSPSapHet();
        fillLichSuPX(null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
    }

    public static void fillTable() {
        tblSanTrenKe.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImage());
        DefaultTableModel model = (DefaultTableModel) tblSanTrenKe.getModel();
        model.setRowCount(0);
        List<SanPham> listSP = khDAO.getlistSPTrenKe();
        if (listSP != null) {
            BufferedImage bfimage[] = new BufferedImage[listSP.size()];
            for (int i = 0; i < listSP.size(); i++) {
                ByteArrayInputStream byteArray = new ByteArrayInputStream(listSP.get(i).getHinh());
                try {
                    bfimage[i] = ImageIO.read(byteArray);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                LoaiHang lh = lhDAO.selectbyID(listSP.get(i).getMaLoai());
                GGSanPhamDAO gg = new GGSanPhamDAO();
                GGSanPham sp = gg.selectByCheckHieuLuc(listSP.get(i).getMaSP());
                if (sp == null) {
                    Object row[] = {listSP.get(i).getMaSP(), bfimage[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH), listSP.get(i).getTenSP(), listSP.get(i).getSlTrenKe(), listSP.get(i).getDonVi(), DDTienTe.FormatVND(listSP.get(i).getGiaBan()), lh.getTenLoai(), listSP.get(i).getQrCode()};
                    model.addRow(row);
                } else {
                    Object row[] = {listSP.get(i).getMaSP(), bfimage[i].getScaledInstance(70, 70, BufferedImage.SCALE_SMOOTH), listSP.get(i).getTenSP(), listSP.get(i).getSlTrenKe(), listSP.get(i).getDonVi(), "<HTML><strike>" + DDTienTe.FormatVND(+listSP.get(i).getGiaBan()) + "</strike>" + " - " + DDTienTe.FormatVND(listSP.get(i).getGiaGiam()), lh.getTenLoai(), listSP.get(i).getQrCode()};
                    model.addRow(row);
                }

            }

        }

    }

    public static void soMatHangTrenKe() {
        lblSoMatHang.setText(String.valueOf(khDAO.selectAll().size()));
    }

    public static void fillTableSPSapHet() {
        DefaultTableModel model = (DefaultTableModel) tblSPSapHet.getModel();
        model.setRowCount(0);
        int slSapHet = 0;
        List<SanPham> listSP = khDAO.getlistSPTrenKe();
        if (listSP != null) {
            for (int i = 0; i < listSP.size(); i++) {
                if (listSP.get(i).getSlTrenKe() <= 5) {
                    Object row[] = {listSP.get(i).getMaSP(), listSP.get(i).getTenSP(), listSP.get(i).getSlTrenKe(), listSP.get(i).getDonVi(), listSP.get(i).getQrCode()};
                    slSapHet += 1;
                    model.addRow(row);
                }
            }
            lblSLSapHet.setText(String.valueOf(slSapHet));
        }

    }

    public void timKiem() {
        int indexTheoMuc = cboTheoMuc.getSelectedIndex();
        if (indexTheoMuc == 0) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, null, null, null, null));
            cboKhoanGia.setSelectedIndex(0);
            fillTable();
        } else if (indexTheoMuc == 1) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(txtNDTim.getText(), null, null, null, null, null));
            fillTable();
        } else if (indexTheoMuc == 2) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, txtNDTim.getText(), null, null, null, null));
            fillTable();
        } else if (indexTheoMuc == 3) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, null, null, null, txtNDTim.getText()));
            fillTable();
        }
    }

    public void timKiemByKhoanGia() {
        int indexKhoanGia = cboKhoanGia.getSelectedIndex();
        if (indexKhoanGia == 0) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, null, null, null, null));
            cboTheoMuc.setSelectedIndex(0);
            fillTable();
        } else if (indexKhoanGia == 1) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, null, 50000.0, null, null));

            fillTable();
        } else if (indexKhoanGia == 2) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, 50000.0, 100000.0, null, null));
            fillTable();
        } else if (indexKhoanGia == 3) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, 100000.0, 200000.0, null, null));
            fillTable();
        } else if (indexKhoanGia == 4) {
            khDAO.setListSPTrenKe(khDAO.selectBySeach(null, null, 200000.0, null, null, null));
            fillTable();
        }
    }

    public void sapXepTheoGia() {
        List<SanPham> listSP = khDAO.getlistSPTrenKe();
        if (cboSapXepGia.getSelectedIndex() == 2) {
            Collections.sort(listSP, new Comparator<SanPham>() {
                @Override
                public int compare(SanPham o1, SanPham o2) {
                    return (int) (o1.getGiaBan() - o2.getGiaBan());
                }
            });
        } else if (cboSapXepGia.getSelectedIndex() == 1) {
            Collections.sort(listSP, new Comparator<SanPham>() {
                @Override
                public int compare(SanPham o1, SanPham o2) {
                    return (int) (o2.getGiaBan() - o1.getGiaBan());
                }

            });
        }
        fillTable();

    }

    ///tab3
    public static void fillLichSuPX(String ngayBD, String ngayKT, String theoNgay) {
        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        List<PhieuXuat> list = pxDAO.selectLSPhieuXuat(ngayBD, ngayKT, theoNgay);
        for (PhieuXuat px : list) {
            NhanVien nv = nvDAO.selectbyID(px.getMaNV());
            Object row[] = {px.getMaPX(), px.getNgayXuat(), px.getGhiChu(), nv.getHoTen()};
            model.addRow(row);
        }
    }

    public void fillLSCTPX(String maPX) {
        DefaultTableModel model = (DefaultTableModel) tblCTPX.getModel();
        model.setRowCount(0);
        List<CTPhieuXuat> listCTPX = ctPXDAO.selectbyMaPX(maPX);
        for (CTPhieuXuat px : listCTPX) {
            SanPham sp = spDAO.selectbyID(px.getMaSP());
            Object row[] = {px.getMaSP(), sp.getTenSP(), px.getSl()};
            model.addRow(row);
        }
    }
    //end tab3

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtNDTim = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTheoMuc = new javax.swing.JComboBox<>();
        btnNhapSPLenKe = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanTrenKe = new HELP.Table();
        cboSapXepGia = new javax.swing.JComboBox<>();
        cboKhoanGia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblSoMatHang = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPSapHet = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblSLSapHet = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLichSu = new HELP.Table();
        cbo3ngayGanNhap = new javax.swing.JComboBox<>();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        btnTimKiemLichSu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCTPX = new HELP.Table();
        jLabel8 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtNDTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 153, 153)));
        txtNDTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNDTimActionPerformed(evt);
            }
        });
        txtNDTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNDTimKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Danh Sách Sản Phẩm Trên Kệ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        cboTheoMuc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboTheoMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn mục tìm kiếm", "Mã Sản Phẩm", "Tên Sản Phẩm", "Barcode" }));
        cboTheoMuc.setToolTipText("");
        cboTheoMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoMucItemStateChanged(evt);
            }
        });

        btnNhapSPLenKe.setBackground(new java.awt.Color(0, 102, 102));
        btnNhapSPLenKe.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnNhapSPLenKe.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapSPLenKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNhapSPLenKe.setText("Thêm Sản Phẩm Lên Kệ");
        btnNhapSPLenKe.setOpaque(true);
        btnNhapSPLenKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapSPLenKeMouseClicked(evt);
            }
        });

        tblSanTrenKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Hình", "Tên SP", "Số Lượng", "Đơn Vị", "Giá Bán", "Loại Hàng", "BarCode"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanTrenKe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblSanTrenKe.setRowHeight(80);
        jScrollPane3.setViewportView(tblSanTrenKe);
        if (tblSanTrenKe.getColumnModel().getColumnCount() > 0) {
            tblSanTrenKe.getColumnModel().getColumn(4).setMaxWidth(50);
            tblSanTrenKe.getColumnModel().getColumn(5).setMinWidth(100);
        }

        cboSapXepGia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboSapXepGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp theo giá", "Giảm dần", "Tăng dần", " " }));
        cboSapXepGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSapXepGiaItemStateChanged(evt);
            }
        });

        cboKhoanGia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboKhoanGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khoảng giá (ALL)", "Dưới 50.000VND", "50.000VND -100.000VND", "100.000VND - 200.000VND", "200.000VND Trở lên", " " }));
        cboKhoanGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoanGiaItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Quản Lý Sản Phẩm Trên Kệ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtNDTim, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboTheoMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                                        .addComponent(cboKhoanGia, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboSapXepGia, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNhapSPLenKe, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNhapSPLenKe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNDTim, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTheoMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSapXepGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhoanGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm Trên Kệ", jPanel2);

        jPanel5.setBackground(new java.awt.Color(0, 153, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Số Mặt Hàng Trên Kệ");

        lblSoMatHang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblSoMatHang.setForeground(new java.awt.Color(255, 255, 255));
        lblSoMatHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoMatHang.setText("1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSoMatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblSoMatHang)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblSPSapHet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblSPSapHet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Vị", "BarCode"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSPSapHet);

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sản Phẩm Sắp Hết Cần Nhập Thêm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Số Sản Phẩm Cần Thêm");

        lblSLSapHet.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblSLSapHet.setForeground(new java.awt.Color(255, 255, 255));
        lblSLSapHet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSapHet.setText("1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSLSapHet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblSLSapHet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("Kiểm Kê", jPanel4);

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PX", "Ngày Giờ", "Ghi chú", "Nhân Viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblLichSu.setRowHeight(35);
        tblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblLichSu);

        cbo3ngayGanNhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbo3ngayGanNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm Nay", "1 ngày trước", "2 ngày trước" }));
        cbo3ngayGanNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo3ngayGanNhapItemStateChanged(evt);
            }
        });

        txtNgayBD.setDateFormatString("dd-MM-yyyy");

        txtNgayKT.setDateFormatString("dd-MM-yyyy");

        btnTimKiemLichSu.setText("Tìm Kiếm");
        btnTimKiemLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemLichSuMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Ngày Bắt Đầu");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Ngày Kết Thúc");

        tblCTPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPX.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblCTPX.setRowHeight(35);
        jScrollPane5.setViewportView(tblCTPX);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Lịch Sử Đưa Sản Phẩm Lên Kệ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiemLichSu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo3ngayGanNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo3ngayGanNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTimKiemLichSu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lịch Sử Thêm SP", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNDTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNDTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDTimActionPerformed

    private void btnNhapSPLenKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapSPLenKeMouseClicked
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                NhapSPLenKeDiaLog nhapKe = new NhapSPLenKeDiaLog(null, true);
                nhapKe.setVisible(true);
                gif.dispose();
                if (NhapSPLenKeDiaLog.checkFill == true) {
                    khDAO.setListSPTrenKe(khDAO.selectAll());
                    fillTable();
                    BanHangPanel.fillTableSPDangBan();
                    fillLichSuPX(null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
                    fillTableSPSapHet();
                    this.stop();
                } else {
                    gif.dispose();
                    this.stop();
                }
            }

        }.start();
    }//GEN-LAST:event_btnNhapSPLenKeMouseClicked

    private void cboTheoMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoMucItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTheoMucItemStateChanged

    private void cboKhoanGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoanGiaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    timKiemByKhoanGia();
                    gif.dispose();
                    this.stop();                }

            }.start();
        }


    }//GEN-LAST:event_cboKhoanGiaItemStateChanged

    private void cboSapXepGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSapXepGiaItemStateChanged
 if (evt.getStateChange() == ItemEvent.SELECTED) {
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(false);
        new Thread() {
            @Override
            public void run() {
                sapXepTheoGia();
                gif.dispose();
                this.stop();
            }

        }.start();
 }
    }//GEN-LAST:event_cboSapXepGiaItemStateChanged

    private void btnTimKiemLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemLichSuMouseClicked
        if (txtNgayBD.getDate() == null || txtNgayKT.getDate() == null) {
            MsgBox.alert(this,"Vui lòng nhập đầy đủ khoản ngày");
            return;
        } else if (txtNgayBD.getDate() == null) {
            fillLichSuPX(null, XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"), null);
            return;
        } else if (txtNgayKT.getDate() == null) {
            fillLichSuPX(XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), null, null);
            return;
        } else {
            fillLichSuPX(XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"), null);
        }
        DefaultTableModel model = (DefaultTableModel) tblCTPX.getModel();
        model.setRowCount(0);

    }//GEN-LAST:event_btnTimKiemLichSuMouseClicked

    private void tblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMouseClicked
        String maPX = (String) tblLichSu.getValueAt(tblLichSu.getSelectedRow(), 0);
        fillLSCTPX(maPX);
    }//GEN-LAST:event_tblLichSuMouseClicked

    private void cbo3ngayGanNhapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo3ngayGanNhapItemStateChanged
        if (cbo3ngayGanNhap.getSelectedIndex() == 0) {
            fillLichSuPX(null, null, XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
        } else if (cbo3ngayGanNhap.getSelectedIndex() == 1) {
            fillLichSuPX(null, null, XDate.toString(XDate.addDays(XDate.getDate(), -1), "yyyy-MM-dd"));
        } else {
            fillLichSuPX(null, null, XDate.toString(XDate.addDays(XDate.getDate(), -2), "yyyy-MM-dd"));
        }

        DefaultTableModel model = (DefaultTableModel) tblCTPX.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_cbo3ngayGanNhapItemStateChanged

    private void txtNDTimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDTimKeyTyped
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(true);
        if (evt.getKeyChar() == 10) {
            new Thread() {
                @Override
                public void run() {
                    timKiem();
                    gif.dispose();
                    this.stop();
                }
            }.start();
        }
    }//GEN-LAST:event_txtNDTimKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnNhapSPLenKe;
    private javax.swing.JButton btnTimKiemLichSu;
    private javax.swing.JComboBox<String> cbo3ngayGanNhap;
    private javax.swing.JComboBox<String> cboKhoanGia;
    private javax.swing.JComboBox<String> cboSapXepGia;
    private javax.swing.JComboBox<String> cboTheoMuc;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JLabel lblSLSapHet;
    public static javax.swing.JLabel lblSoMatHang;
    public static HELP.Table tblCTPX;
    public static HELP.Table tblLichSu;
    public static javax.swing.JTable tblSPSapHet;
    public static HELP.Table tblSanTrenKe;
    private javax.swing.JTextField txtNDTim;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    // End of variables declaration//GEN-END:variables
}
