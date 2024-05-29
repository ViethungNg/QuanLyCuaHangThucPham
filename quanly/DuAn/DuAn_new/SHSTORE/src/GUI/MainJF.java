/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Models.NhanVien;
import Models.SanPham;
import GUI.Children.BanHangPanel;
import static GUI.Children.BanHangPanel.hdBanLeDAO;
import UI.Children.DaiLog.DoiMK_NVDiaLog;
import GUI.Children.KeHangPanel;
import GUI.Children.KhuyenMaiPanel;
import GUI.Children.NhapKhoPanel;
import GUI.Children.PanelNhanvien;
import GUI.Children.PanelTrangchu;
import GUI.Children.SanPhamPanel;
import GUI.Children.ThietLapPanel;
import GUI.Children.ThongKePanel;
import GUI.Children.panelKhachHang;
import Utils.Auth;
import Utils.MsgBox;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author TIEN SY
 */
public class MainJF extends javax.swing.JFrame {

    /**
     * Creates new form MainJF
     */
    public MainJF() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon/icons8-grocery-store-32.png")));
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static List<SanPham> spTrenKe;
    CardLayout cardLayOut = new CardLayout();
    BanHangPanel banHangPanel = new BanHangPanel();
    KhuyenMaiPanel khuyenMai = new KhuyenMaiPanel();
    ThietLapPanel thietLap = new ThietLapPanel();
    KeHangPanel keHang = new KeHangPanel();
    NhapKhoPanel nhapKho = new NhapKhoPanel();
    SanPhamPanel sanPham = new SanPhamPanel();
    PanelNhanvien nhanVien = new PanelNhanvien();
    panelKhachHang khachHang = new panelKhachHang();
    ThongKePanel thongKe = new ThongKePanel();

    public void init() {
        NhanVien nv = Auth.user;
        lblTenNV.setText("Nhân Viên: " + nv.getHoTen());
        lblChucVu.setText("Chức Vụ: " + nv.getTenCV());
        panelND.setLayout(cardLayOut);
        panelND.add(new PanelTrangchu());
    }

    public int PhanQuyen() {
        if (Auth.user.getMaCV().equals("1")) {
            //chủ
            return 1;
        }

        if (Auth.user.getMaCV().equals("2")) {
            //quản lý
            return 2;
        }

        if (Auth.user.getMaCV().equals("3")) {
            //kho
            return 3;
        }

        if (Auth.user.getMaCV().equals("4")) {
            //bán hàng
            return 4;
        }

        if (Auth.user.getMaCV().equals("5")) {
            //kế toán
            return 5;
        }
        return -1;
    }

    public void resetMau() {
        btnIconBanHang.setBackground(new Color(0, 102, 102));
        btnBanHang.setBackground(new Color(0, 102, 102));

        btnIconKeHang.setBackground(new Color(0, 102, 102));
        btnKeHang.setBackground(new Color(0, 102, 102));

        btnIconNhapSP.setBackground(new Color(0, 102, 102));
        btnKhoHang.setBackground(new Color(0, 102, 102));

        btnIconSP.setBackground(new Color(0, 102, 102));
        btnSanPham.setBackground(new Color(0, 102, 102));

        btnIconKM.setBackground(new Color(0, 102, 102));
        btnKhuyenMai.setBackground(new Color(0, 102, 102));

        btnIconThietLap.setBackground(new Color(0, 102, 102));
        btnThietLap.setBackground(new Color(0, 102, 102));

        btnIconThongK.setBackground(new Color(0, 102, 102));
        btnThongKe.setBackground(new Color(0, 102, 102));

        btnIconKH.setBackground(new Color(0, 102, 102));
        btnQLKH.setBackground(new Color(0, 102, 102));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JLabel();
        btnIconBanHang = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnKeHang = new javax.swing.JLabel();
        btnIconKeHang = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnKhoHang = new javax.swing.JLabel();
        btnIconNhapSP = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JLabel();
        btnIconSP = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnQLKH = new javax.swing.JLabel();
        btnIconKH = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnKhuyenMai = new javax.swing.JLabel();
        btnIconKM = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnThietLap = new javax.swing.JLabel();
        btnIconThietLap = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnThongKe = new javax.swing.JLabel();
        btnIconThongK = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTenNV = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelND = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuNhanVien = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setRollover(true);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 20, 2));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));

        btnBanHang.setBackground(new java.awt.Color(0, 102, 102));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBanHang.setText("Bán Hàng");
        btnBanHang.setIconTextGap(25);
        btnBanHang.setOpaque(true);
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBanHangMousePressed(evt);
            }
        });

        btnIconBanHang.setBackground(new java.awt.Color(0, 102, 102));
        btnIconBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/trade32px.png"))); // NOI18N
        btnIconBanHang.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(btnIconBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        btnKeHang.setBackground(new java.awt.Color(0, 102, 102));
        btnKeHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKeHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKeHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKeHang.setText("Kệ Hàng");
        btnKeHang.setIconTextGap(25);
        btnKeHang.setOpaque(true);
        btnKeHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKeHangMousePressed(evt);
            }
        });

        btnIconKeHang.setBackground(new java.awt.Color(0, 102, 102));
        btnIconKeHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconKeHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shelves.png"))); // NOI18N
        btnIconKeHang.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btnIconKeHang, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnKeHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnKeHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconKeHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        btnKhoHang.setBackground(new java.awt.Color(0, 102, 102));
        btnKhoHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKhoHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhoHang.setText("Nhập Sản Phẩm");
        btnKhoHang.setIconTextGap(25);
        btnKhoHang.setOpaque(true);
        btnKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhoHangMousePressed(evt);
            }
        });

        btnIconNhapSP.setBackground(new java.awt.Color(0, 102, 102));
        btnIconNhapSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconNhapSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kho32px.png"))); // NOI18N
        btnIconNhapSP.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnIconNhapSP, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIconNhapSP, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(btnKhoHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        jPanel13.setBackground(new java.awt.Color(0, 102, 102));

        btnSanPham.setBackground(new java.awt.Color(0, 102, 102));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.setText("QL Sản Phẩm");
        btnSanPham.setIconTextGap(25);
        btnSanPham.setOpaque(true);
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSanPhamMousePressed(evt);
            }
        });

        btnIconSP.setBackground(new java.awt.Color(0, 102, 102));
        btnIconSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/products.png"))); // NOI18N
        btnIconSP.setOpaque(true);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(btnIconSP, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconSP, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel13);

        jPanel11.setBackground(new java.awt.Color(0, 102, 102));

        btnQLKH.setBackground(new java.awt.Color(0, 102, 102));
        btnQLKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnQLKH.setForeground(new java.awt.Color(255, 255, 255));
        btnQLKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLKH.setText("QL Khách Hàng");
        btnQLKH.setIconTextGap(25);
        btnQLKH.setOpaque(true);
        btnQLKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLKHMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQLKHMousePressed(evt);
            }
        });

        btnIconKH.setBackground(new java.awt.Color(0, 102, 102));
        btnIconKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-male-user-32.png"))); // NOI18N
        btnIconKH.setOpaque(true);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnIconKH, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnQLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQLKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconKH, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(0, 102, 102));

        btnKhuyenMai.setBackground(new java.awt.Color(0, 102, 102));
        btnKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhuyenMai.setText("Khuyến Mãi");
        btnKhuyenMai.setIconTextGap(25);
        btnKhuyenMai.setOpaque(true);
        btnKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhuyenMaiMousePressed(evt);
            }
        });

        btnIconKM.setBackground(new java.awt.Color(0, 102, 102));
        btnIconKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sales32px.png"))); // NOI18N
        btnIconKM.setOpaque(true);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(btnIconKM, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconKM, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel12);

        btnThietLap.setBackground(new java.awt.Color(0, 102, 102));
        btnThietLap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThietLap.setForeground(new java.awt.Color(255, 255, 255));
        btnThietLap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThietLap.setText("Thiết Lập");
        btnThietLap.setIconTextGap(25);
        btnThietLap.setOpaque(true);
        btnThietLap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThietLapMousePressed(evt);
            }
        });

        btnIconThietLap.setBackground(new java.awt.Color(0, 102, 102));
        btnIconThietLap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconThietLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/setting32px.png"))); // NOI18N
        btnIconThietLap.setOpaque(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(btnIconThietLap, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnThietLap, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThietLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnIconThietLap, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10);

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));

        btnThongKe.setBackground(new java.awt.Color(0, 102, 102));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThongKe.setText("Thống Kê");
        btnThongKe.setIconTextGap(25);
        btnThongKe.setOpaque(true);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });

        btnIconThongK.setBackground(new java.awt.Color(0, 102, 102));
        btnIconThongK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIconThongK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thongke32px.png"))); // NOI18N
        btnIconThongK.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnIconThongK, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIconThongK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8);

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logotrong145.png"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        jLabel5.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblTenNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(0, 102, 102));
        lblTenNV.setText("Nhân viên:");

        lblChucVu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(0, 102, 102));
        lblChucVu.setText("Chức Vụ:");

        jLabel10.setBackground(new java.awt.Color(0, 102, 102));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("LOGOUT");
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChucVu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelND.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelNDLayout = new javax.swing.GroupLayout(panelND);
        panelND.setLayout(panelNDLayout);
        panelNDLayout.setHorizontalGroup(
            panelNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        panelNDLayout.setVerticalGroup(
            panelNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("Hệ Thống");

        jMenuItem2.setText("Đổi Mật Khẩu");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Đăng Xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Thoát");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản Lý");

        menuNhanVien.setText("Nhân Viên");
        menuNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuNhanVienMousePressed(evt);
            }
        });
        menuNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(menuNhanVien);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThietLapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThietLapMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(thietLap);
            cardLayOut.first(panelND);
            resetMau();
            btnIconThietLap.setBackground(new Color(0, 51, 51));
            btnThietLap.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }

    }//GEN-LAST:event_btnThietLapMousePressed

    private void btnKeHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeHangMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 4) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(keHang);
            cardLayOut.first(panelND);
            resetMau();
            btnIconKeHang.setBackground(new Color(0, 51, 51));
            btnKeHang.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_btnKeHangMousePressed

    private void btnKhoHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhoHangMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 3) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(nhapKho);
            cardLayOut.first(panelND);
            resetMau();
            btnIconNhapSP.setBackground(new Color(0, 51, 51));
            btnKhoHang.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_btnKhoHangMousePressed

    private void btnBanHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 4) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(banHangPanel);
            cardLayOut.first(panelND);
            resetMau();
            btnIconBanHang.setBackground(new Color(0, 51, 51));
            btnBanHang.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_btnBanHangMousePressed
    private void btnKhuyenMaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhuyenMaiMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(khuyenMai);
            cardLayOut.first(panelND);
            resetMau();
            btnIconKM.setBackground(new Color(0, 51, 51));
            btnKhuyenMai.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }

    }//GEN-LAST:event_btnKhuyenMaiMousePressed

    private void btnSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 4) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(sanPham);
            cardLayOut.first(panelND);
            resetMau();
            btnIconSP.setBackground(new Color(0, 51, 51));
            btnSanPham.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_btnSanPhamMousePressed

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 5) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(thongKe);
            cardLayOut.first(panelND);
            resetMau();
            btnIconThongK.setBackground(new Color(0, 51, 51));
            btnThongKe.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void menuNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuNhanVienActionPerformed

    private void menuNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhanVienMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(new PanelNhanvien());
            cardLayOut.first(panelND);
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }
    }//GEN-LAST:event_menuNhanVienMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (banHangPanel.lblMaHD.getText() != null) {
            hdBanLeDAO.deleteHD(banHangPanel.lblMaHD.getText());
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void btnQLKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLKHMousePressed
        if (PhanQuyen() == 1 || PhanQuyen() == 2 || PhanQuyen() == 4) {
            panelND.remove(0);
            panelND.setLayout(cardLayOut);
            panelND.add(khachHang);
            cardLayOut.first(panelND);
            resetMau();
            btnIconKH.setBackground(new Color(0, 51, 51));
            btnQLKH.setBackground(new Color(0, 51, 51));
        } else {
            MsgBox.alert(null, "Bạn không có quyền truy cập chức năng này !");
        }

    }//GEN-LAST:event_btnQLKHMousePressed

    private void btnQLKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLKHMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQLKHMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.dispose();
        Auth.clear();
        FormLogin login = new FormLogin();
        login.setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        Auth.clear();
        FormLogin login = new FormLogin();
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        DoiMK_NVDiaLog doiMK = new DoiMK_NVDiaLog(null, true);
        doiMK.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        System.setProperty("sun.java2d.uiScale", "1.25");
        FlatIntelliJLaf.setup();
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBanHang;
    private javax.swing.JLabel btnIconBanHang;
    private javax.swing.JLabel btnIconKH;
    private javax.swing.JLabel btnIconKM;
    private javax.swing.JLabel btnIconKeHang;
    private javax.swing.JLabel btnIconNhapSP;
    private javax.swing.JLabel btnIconSP;
    private javax.swing.JLabel btnIconThietLap;
    private javax.swing.JLabel btnIconThongK;
    private javax.swing.JLabel btnKeHang;
    private javax.swing.JLabel btnKhoHang;
    private javax.swing.JLabel btnKhuyenMai;
    private javax.swing.JLabel btnQLKH;
    private javax.swing.JLabel btnSanPham;
    private javax.swing.JLabel btnThietLap;
    private javax.swing.JLabel btnThongKe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JMenuItem menuNhanVien;
    private javax.swing.JPanel panelND;
    // End of variables declaration//GEN-END:variables
}
