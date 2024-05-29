/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Children.DaiLog;

import DAO.LoaiHangDAO;
import DAO.NhaCCDAO;
import DAO.SanPhamDAO;
import Models.LoaiHang;
import Models.NhaCC;
import Models.SanPham;
import GUI.Children.SanPhamPanel;
import Utils.MsgBox;
import Utils.Validation;
import Utils.XImage;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIEN SY
 */
public class TaoSPMoiDialog extends javax.swing.JDialog {

    /**
     * Creates new form TaoSPMoiDialog
     */
    public TaoSPMoiDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon/icons8-grocery-store-32.png")));
        this.setLocationRelativeTo(null);
        init();
    }
    public static boolean kt = false;
    byte anhSP[];
    NhaCCDAO nccDAO = new NhaCCDAO();
    LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    
    public void init() {
        fillComboboxNCC();
        fillComboboxloaiSP();
    }
    
    public void chonAnh() throws IOException {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file);
            anhSP = fis.readAllBytes();
            ImageIcon icon = XImage.readChon(file, 222, 237);
            lblAnhSP.setIcon(icon);
        }
        
    }
    
    public void fillComboboxNCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBNhaCC.getModel();
        model.removeAllElements();
        List<NhaCC> listNCC = nccDAO.selectAllbyTinhTrang();
        for (NhaCC ncc : listNCC) {
            model.addElement(ncc);
        }
    }
    
    public void fillComboboxloaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel();
        model.removeAllElements();
        List<LoaiHang> listLH = loaiHangDAO.selectAll();
        for (LoaiHang lh : listLH) {
            model.addElement(lh);
        }
    }
    
    public SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setDonVi(txtDonVi.getText());
        sp.setQrCode(txtBarCode.getText());
        sp.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        NhaCC ncc = (NhaCC) cboBNhaCC.getSelectedItem();
        sp.setMaNCC(ncc.getMaNCC());
        LoaiHang lh = (LoaiHang) cboLoaiSP.getSelectedItem();
        sp.setMaLoai(lh.getMaLoai());
        sp.setHinh(anhSP);
        return sp;
    }
    
    public void insert() {
        
        try {
            for (int i = 0; i < spDAO.getListSP().size(); i++) {
                if (txtMaSP.getText().equals(spDAO.getListSP().get(i).getMaSP())) {
                    MsgBox.alert(this, "Mã sản phẩm đã tồn tại !");
                    return;
                }
            }
            spDAO.insert(getForm());;
            MsgBox.alert(this, "Tạo Mới Thành Công");
            kt = true;
            this.dispose();
        } catch (Exception e) {
            MsgBox.alert(this, "Tạo Mới Thất Bại");
        }
        
    }
    
    public boolean ckValidate() {
        if (Validation.isEmpty(txtTenSP, "Vui lòng nhập tên sản phẩm")) {
            return false;
        }
        if (Validation.isEmpty(txtMaSP, "Vui lòng nhập mã sản phẩm")) {
            return false;
        }
        
        if (Validation.isEmpty(txtDonVi, "Vui lòng nhập đơn vị")) {
            return false;
        }
        if (Validation.isEmpty(txtBarCode, "Vui lòng nhập Barcode")) {
            return false;
        }
        
        if (Validation.isEmpty(txtGiaNhap, "Vui lòng nhập giá nhập")) {
            return false;
        } else {
            if (Validation.isNumber(txtGiaNhap, "Giá nhập không được nhập chữ")) {
                return false;
            } else if (Double.parseDouble(txtGiaNhap.getText()) < 0) {
                MsgBox.alert(this, "Giá nhập sai định dạng");
                return false;
            }
        }
        
        if (Validation.isEmpty(txtGiaBan, "Vui lòng nhập giá bán")) {
            return false;
        } else {
            if (Validation.isNumber(txtGiaBan, "Giá bán không được nhập chữ")) {
                return false;
            } else if (Double.parseDouble(txtGiaBan.getText()) < 0) {
                MsgBox.alert(this, "Giá bán sai định dạng");
                return false;
            }
        }
        
        if (anhSP == null) {
            MsgBox.alert(this, "Vui lòng chọn ảnh sản phẩm");
            return false;
        }
        
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDonVi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboBNhaCC = new javax.swing.JComboBox<>();
        lblAnhSP = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        btnTao = new javax.swing.JLabel();
        txtBarCode = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tên SP:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mã SP:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Giá Nhập:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Giá bán:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Loại SP:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("BarCode:");

        txtDonVi.setBackground(new java.awt.Color(240, 240, 240));
        txtDonVi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtDonVi.setMinimumSize(new java.awt.Dimension(7, 25));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Đơn Vị:");

        cboBNhaCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAnhSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhSP.setText("Anh SP");
        lblAnhSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhSPMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nhà Cung Cấp:");

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTao.setBackground(new java.awt.Color(0, 153, 204));
        btnTao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTao.setForeground(new java.awt.Color(255, 255, 255));
        btnTao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTao.setText("Tạo");
        btnTao.setOpaque(true);
        btnTao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoMouseClicked(evt);
            }
        });

        txtBarCode.setBackground(new java.awt.Color(240, 240, 240));
        txtBarCode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtBarCode.setMinimumSize(new java.awt.Dimension(7, 25));

        txtMaSP.setBackground(new java.awt.Color(240, 240, 240));
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtMaSP.setMinimumSize(new java.awt.Dimension(7, 25));

        txtTenSP.setBackground(new java.awt.Color(240, 240, 240));
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtTenSP.setMinimumSize(new java.awt.Dimension(7, 25));

        txtGiaBan.setBackground(new java.awt.Color(240, 240, 240));
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtGiaBan.setMinimumSize(new java.awt.Dimension(7, 25));

        txtGiaNhap.setBackground(new java.awt.Color(240, 240, 240));
        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txtGiaNhap.setMinimumSize(new java.awt.Dimension(7, 25));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboBNhaCC, 0, 351, Short.MAX_VALUE)
                                .addComponent(cboLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(159, 159, 159)
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel3)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel12)
                                    .addComponent(cboBNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 204));
        jLabel5.setText("Tạo Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(59, 59, 59)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoMouseClicked
        gifLoadDiaLog gif = new gifLoadDiaLog(null, false);
        gif.setVisible(true);
        new Thread() {
            @Override
            public void run() {
                if (ckValidate()) {
                    insert();
                    gif.dispose();
                }
                
            }
            
        }.start();
        

    }//GEN-LAST:event_btnTaoMouseClicked

    private void lblAnhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhSPMouseClicked
        try {
            chonAnh();
        } catch (IOException ex) {
            Logger.getLogger(TaoSPMoiDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblAnhSPMouseClicked

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
            java.util.logging.Logger.getLogger(TaoSPMoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaoSPMoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaoSPMoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaoSPMoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaoSPMoiDialog dialog = new TaoSPMoiDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnTao;
    private javax.swing.JComboBox<String> cboBNhaCC;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    public static javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
