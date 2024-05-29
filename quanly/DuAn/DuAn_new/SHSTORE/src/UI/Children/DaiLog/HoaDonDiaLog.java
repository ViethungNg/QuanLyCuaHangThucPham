package UI.Children.DaiLog;

import COMPONENT.PanelHoadon;
import Utils.MsgBox;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class HoaDonDiaLog extends javax.swing.JDialog {

    public HoaDonDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon/icons8-grocery-store-32.png")));
        Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        dimension.width = this.getWidth();
        dimension.height = dimension.height - 35;
        setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getWidth() / 2, 0));
        setSize(dimension);
    }

    public static int ckThaoTacHD = 0;

    public PanelHoadon getPanelHoadon1() {
        return panelHoadon1;
    }

    public void setPanelHoadon1(PanelHoadon panelHoadon1) {
        this.panelHoadon1 = panelHoadon1;
    }

    public void TTXuatHD() {
        JFileChooser jf = new JFileChooser();
        if (jf.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = new File("dataPDF\\a.pdf");
            File taget = jf.getSelectedFile();
            if (!taget.getName().contains(".pdf")) {
                taget = new File(taget.getAbsoluteFile() + ".pdf");
            }
            try {
                Files.copy(file.toPath(), taget.toPath(), StandardCopyOption.REPLACE_EXISTING);
                MsgBox.alert(null, "Xuất File Thành Công");
                ckThaoTacHD = 1;
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(HoaDonDiaLog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelHoadon1 = new COMPONENT.PanelHoadon();
        jPanel1 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        tbnTTxuatHĐ = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelHoadon1.setFile(new java.io.File("H:\\p.png"));

        javax.swing.GroupLayout panelHoadon1Layout = new javax.swing.GroupLayout(panelHoadon1);
        panelHoadon1.setLayout(panelHoadon1Layout);
        panelHoadon1Layout.setHorizontalGroup(
            panelHoadon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        panelHoadon1Layout.setVerticalGroup(
            panelHoadon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1392, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelHoadon1);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 6, 6));

        btnThanhToan.setBackground(new java.awt.Color(0, 102, 102));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setBorder(null);
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        btnThanhToan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnThanhToanKeyTyped(evt);
            }
        });
        jPanel1.add(btnThanhToan);

        tbnTTxuatHĐ.setBackground(new java.awt.Color(0, 102, 102));
        tbnTTxuatHĐ.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbnTTxuatHĐ.setForeground(new java.awt.Color(255, 255, 255));
        tbnTTxuatHĐ.setText("Xuất HĐ - TT");
        tbnTTxuatHĐ.setBorder(null);
        tbnTTxuatHĐ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnTTxuatHĐActionPerformed(evt);
            }
        });
        jPanel1.add(tbnTTxuatHĐ);

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbnTTxuatHĐActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnTTxuatHĐActionPerformed
        TTXuatHD();
    }//GEN-LAST:event_tbnTTxuatHĐActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        ckThaoTacHD = 2;
        this.dispose();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThanhToanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnThanhToanKeyTyped
      if(evt.getKeyChar()==13){
           ckThaoTacHD = 2;
        this.dispose();
      }
    }//GEN-LAST:event_btnThanhToanKeyTyped

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
            java.util.logging.Logger.getLogger(HoaDonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDonDiaLog dialog = new HoaDonDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private COMPONENT.PanelHoadon panelHoadon1;
    private javax.swing.JButton tbnTTxuatHĐ;
    // End of variables declaration//GEN-END:variables
}
