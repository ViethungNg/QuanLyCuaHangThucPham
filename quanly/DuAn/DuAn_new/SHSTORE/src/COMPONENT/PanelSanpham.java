/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COMPONENT;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author huuho
 */
public class PanelSanpham extends javax.swing.JPanel {

    /**
     * Creates new form PanelMucSP
     */
    String giaContent = "null";
    String soLuongContent = "null";
    String titleContent = null;
    Image icon = null;
    String maSPConten;

    public String getMaSPConten() {
        return maSPConten;
    }

    public void setMaSPConten(String maSPConten) {
        this.maSPConten = maSPConten;
    }

    

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getGiaContent() {
        return giaContent;
    }

    public void setGiaContent(String giaContent) {
        this.giaContent = giaContent;
    }

    public String getSoLuongContent() {
        return soLuongContent;
    }

    public void setSoLuongContent(String soLuongContent) {
        this.soLuongContent = soLuongContent;
    }

    public PanelSanpham() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitlleSP = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblMua = new javax.swing.JLabel();
        lblSLcontent = new javax.swing.JLabel();
        lblGiaconten = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(200, 250));

        lblTitlleSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitlleSP.setText("Tên Sản Phẩm");

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setText("Logo");

        lblSL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSL.setText("SL còn:");

        lblGia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGia.setText("Giá:");

        lblMua.setBackground(new java.awt.Color(102, 204, 0));
        lblMua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMua.setText("Chọn mua");
        lblMua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMua.setOpaque(true);

        lblSLcontent.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSLcontent.setText("null");

        lblGiaconten.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiaconten.setText("null");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSL, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSLcontent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGiaconten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitlleSP, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMua, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblMua, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitlleSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSL)
                    .addComponent(lblSLcontent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGia)
                    .addComponent(lblGiaconten))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getlblmua() {
        return lblMua;
    }

    public void setlblmua(JLabel lblMua) {
        this.lblMua = lblMua;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblGiaconten;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblMua;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblSLcontent;
    private javax.swing.JLabel lblTitlleSP;
    // End of variables declaration//GEN-END:variables
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(200, 250));
        lblSLcontent.setText(soLuongContent);
        lblGiaconten.setText(giaContent);
        lblTitlleSP.setText(titleContent);
        if (icon != null) {
            try {
                lblIcon.setText(null);
                lblIcon.setIcon(new ImageIcon(icon));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblIcon.setText("Logo");
        }
    }
}
