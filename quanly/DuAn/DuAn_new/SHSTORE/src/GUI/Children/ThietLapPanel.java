/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Children;

/**
 *
 * @author TIEN SY
 */
public class ThietLapPanel extends javax.swing.JPanel {

    /**
     * Creates new form ThietLapPanel
     */
    public ThietLapPanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        TabPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnNhaCC = new javax.swing.JLabel();
        btnLoaiHang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        TabPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 102)));

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 8, 8));

        btnNhaCC.setBackground(new java.awt.Color(0, 102, 102));
        btnNhaCC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNhaCC.setForeground(new java.awt.Color(255, 255, 255));
        btnNhaCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNhaCC.setText("Nhà Cung Cấp");
        btnNhaCC.setOpaque(true);
        btnNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNhaCCMousePressed(evt);
            }
        });
        jPanel1.add(btnNhaCC);

        btnLoaiHang.setBackground(new java.awt.Color(0, 102, 102));
        btnLoaiHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLoaiHang.setForeground(new java.awt.Color(255, 255, 255));
        btnLoaiHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLoaiHang.setText("Loại Hàng Hóa");
        btnLoaiHang.setOpaque(true);
        btnLoaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoaiHangMousePressed(evt);
            }
        });
        jPanel1.add(btnLoaiHang);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Thiết Lập");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TabPanel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 696, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(TabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhaCCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhaCCMousePressed
        PanelNCC pnNCC = new PanelNCC();
        TabPanel.removeAll();
        TabPanel.add(pnNCC);
        TabPanel.setTitleAt(0,"Thiết Lập Nhà Cung Cấp");
       
    }//GEN-LAST:event_btnNhaCCMousePressed

    private void btnLoaiHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoaiHangMousePressed
        LoaiHangPanel loaiHangPn = new LoaiHangPanel();
        TabPanel.removeAll();
        TabPanel.add(loaiHangPn);
        TabPanel.setTitleAt(0,"Thiết Lập Loại Hàng");
    }//GEN-LAST:event_btnLoaiHangMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabPanel;
    private javax.swing.JLabel btnLoaiHang;
    private javax.swing.JLabel btnNhaCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
