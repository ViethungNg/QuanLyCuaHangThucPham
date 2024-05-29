/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

import DAO.HDBanLeDAO;
import DAO.ThongKeDAO;
import Models.HDBanLe;
import UI.Children.DaiLog.gifLoadDiaLog;
import Utils.DDTienTe;
import Utils.JDBCHelper;
import Utils.MsgBox;
import Utils.XDate;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN SY
 */
public class ThongKePanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePanel
     */
    public ThongKePanel() {
        initComponents();
        init();

    }

    public static void init() {
        thongke(0, XDate.toString(XDate.addDays(XDate.getDate(), -5), "yyyy-MM-dd"), XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
        lblDoanhThuHN.setText(String.valueOf(DDTienTe.FormatVND(ThongKeDAO.DTHomNay())));
        Top10SPbanChay();
        fillLoiNhuan(XDate.toString(XDate.getDate(), "yyyy-MM-dd"), XDate.toString(XDate.getDate(), "yyyy-MM-dd"));

    }

    public static ThongKeDAO tkDAO = new ThongKeDAO();

    public static void thongke(int index, String ngayBD, String ngayKT) {
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        List<Object[]> listTK = tkDAO.thongKeDT(index, ngayBD, ngayKT);
        if (listTK != null) {
            for (int i = 0; i < listTK.size(); i++) {
                System.out.println(listTK.get(i)[0]);
                dcd.setValue((Number) listTK.get(i)[1], "DOANH THU", listTK.get(i)[0] + "");
            }
            JFreeChart jchart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH THU", "THỜI GIAN", "VND", dcd, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.black);
            ChartFrame chartFaFrame = new ChartFrame("reco", jchart, true);
            chartFaFrame.setSize(200, 200);

            ChartPanel chartPanel = new ChartPanel(jchart);
            pnlBieuDo.removeAll();
            pnlBieuDo.add(chartPanel);
        }

    }

    public static void Top10SPbanChay() {
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        List<Object[]> listTK = tkDAO.thongKeSPBanChay();
        if (listTK != null) {
            for (int i = 0; i < listTK.size(); i++) {
                System.out.println(listTK.get(i)[0]);
                dcd.setValue((Number) listTK.get(i)[0], "Sản phẩm", listTK.get(i)[1] + "");
            }
            JFreeChart jchart = ChartFactory.createBarChart("TOP 10 SẢN PHẨM BÁN CHẠY  ", "Sản phẩm", "Số lượng", dcd, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.black);
            ChartFrame chartFaFrame = new ChartFrame("reco", jchart, true);
            chartFaFrame.setSize(200, 200);

            ChartPanel chartPanel = new ChartPanel(jchart);
            pnlSPbanChay.removeAll();
            pnlSPbanChay.add(chartPanel);
        }

    }

    public static void fillLoiNhuan(String ngayBD, String ngayKT) {
        double ln = tkDAO.LoiNhuan(ngayBD, ngayKT);
        lblLoiNhuan.setText(DDTienTe.FormatVND(ln));
    }

    public void locDoanhThu() {
        if (cboThongThe.getSelectedIndex() == 0) {
            thongke(0, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"));
        } else if (cboThongThe.getSelectedIndex() == 1) {
            thongke(1, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"));
        } else {

            thongke(2, XDate.toString(txtNgayBD.getDate(), "yyyy-MM-dd"), XDate.toString(txtNgayKT.getDate(), "yyyy-MM-dd"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDoanhThuHN = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        pnlBieuDo = new javax.swing.JPanel();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        cboThongThe = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        pnlSPbanChay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblLoiNhuan = new javax.swing.JLabel();
        cboLoiNhuan = new javax.swing.JComboBox<>();
        txtNgayKTLN = new com.toedter.calendar.JDateChooser();
        txtNgayBDLoiN = new com.toedter.calendar.JDateChooser();
        btnLocLN = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DOANH THU HÔM NAY");

        lblDoanhThuHN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoanhThuHN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuHN.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
            .addComponent(lblDoanhThuHN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoanhThuHN, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.setForeground(new java.awt.Color(0, 102, 102));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnlBieuDo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBieuDo.setLayout(new javax.swing.BoxLayout(pnlBieuDo, javax.swing.BoxLayout.LINE_AXIS));

        txtNgayBD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNgayBD.setDateFormatString("dd-MM-yyyy");

        txtNgayKT.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNgayKT.setDateFormatString("dd-MM-yyyy");

        cboThongThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tháng", "Năm" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("TG Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("TG Kết Thúc:");

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Lọc");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboThongThe, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
                .addGap(703, 703, 703))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboThongThe, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(txtNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Doanh Thu", jPanel5);

        pnlSPbanChay.setLayout(new javax.swing.BoxLayout(pnlSPbanChay, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSPbanChay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSPbanChay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Sản phẩm bán chạy", jPanel6);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("TỔNG HỢP - THỐNG KÊ");

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lợi Nhuận");

        lblLoiNhuan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLoiNhuan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoiNhuan.setText("0");

        cboLoiNhuan.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cboLoiNhuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm Nay", "1 Ngày Trước", "2 ngày Trước", "Tháng Trước", "Năm Trước" }));
        cboLoiNhuan.setBorder(null);
        cboLoiNhuan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoiNhuanItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLoiNhuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cboLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 187, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cboLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtNgayKTLN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNgayKTLN.setDateFormatString("dd-MM-yyyy");

        txtNgayBDLoiN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNgayBDLoiN.setDateFormatString("dd-MM-yyyy");

        btnLocLN.setBackground(new java.awt.Color(0, 102, 102));
        btnLocLN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLocLN.setForeground(new java.awt.Color(255, 255, 255));
        btnLocLN.setText("Lọc");
        btnLocLN.setBorder(null);
        btnLocLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocLNActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("TG Kết Thúc:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("TG Bắt Đầu:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayBDLoiN, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKTLN, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocLN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKTLN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayBDLoiN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLocLN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txtNgayBD.getDate() == null || txtNgayKT.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập khoản thời gian !");
        } else {
            gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
            gif.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    locDoanhThu();
                    gif.dispose();
                    this.stop();
                }

            }.start();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboLoiNhuanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoiNhuanItemStateChanged
        if (cboLoiNhuan.getSelectedIndex() == 0) {
            fillLoiNhuan(XDate.toString(XDate.getDate(), "yyyy-MM-dd"), XDate.toString(XDate.getDate(), "yyyy-MM-dd"));
        }
        if (cboLoiNhuan.getSelectedIndex() == 1) {
            fillLoiNhuan(XDate.toString(XDate.addDays(XDate.getDate(), -1), "yyyy-MM-dd"), XDate.toString(XDate.addDays(XDate.getDate(), -1), "yyyy-MM-dd"));
        }
        if (cboLoiNhuan.getSelectedIndex() == 2) {
            fillLoiNhuan(XDate.toString(XDate.addDays(XDate.getDate(), -2), "yyyy-MM-dd"), XDate.toString(XDate.addDays(XDate.getDate(), -2), "yyyy-MM-dd"));
        }
    }//GEN-LAST:event_cboLoiNhuanItemStateChanged

    private void btnLocLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocLNActionPerformed
        if(txtNgayBDLoiN.getDate() == null || txtNgayKTLN.getDate() == null){
            MsgBox.alert(this,"Vui lòng nhập đầy đủ khoản ngày !");
            return;
        }else if(txtNgayBDLoiN.getDate().after(txtNgayKTLN.getDate())){
            MsgBox.alert(this,"Ngày kết thúc phải sau hoặc bằng ngày bắt đầu");
            return;
        }else{
            fillLoiNhuan(XDate.toString(txtNgayBDLoiN.getDate(),"yyyy-MM-dd"), XDate.toString(txtNgayKTLN.getDate(),"yyyy-MM-dd"));
        }
    }//GEN-LAST:event_btnLocLNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocLN;
    private javax.swing.JComboBox<String> cboLoiNhuan;
    private javax.swing.JComboBox<String> cboThongThe;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JLabel lblDoanhThuHN;
    public static javax.swing.JLabel lblLoiNhuan;
    public static javax.swing.JPanel pnlBieuDo;
    public static javax.swing.JPanel pnlSPbanChay;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayBDLoiN;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private com.toedter.calendar.JDateChooser txtNgayKTLN;
    // End of variables declaration//GEN-END:variables
}
