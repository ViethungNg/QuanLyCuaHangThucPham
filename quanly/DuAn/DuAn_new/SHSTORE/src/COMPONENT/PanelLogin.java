package COMPONENT;

import SWING_OTHER.SHADOW.ShadowRenderer;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class PanelLogin extends javax.swing.JPanel {

    private BufferedImage image;

    public PanelLogin() {
        initComponents();
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gd = (Graphics2D) g.create();
        gd.drawImage(image, 0, 0, null);
        gd.dispose();
        super.paintComponent(g);
    }

    public void createImage() {
        int shadowSize = 15;
        int width = getWidth();
        int height = getHeight();
        int space = shadowSize * 2;
        BufferedImage img = new BufferedImage(width - space, height - space, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D img_g2 = image.createGraphics();
        img_g2.drawImage(new ShadowRenderer(shadowSize, 1f, new Color(0, 0, 0)).createShadow(img), 0, 0, null);
        img_g2.setComposite(AlphaComposite.Clear);
        img_g2.fillRect(shadowSize, shadowSize, width - space, height - space);
        img_g2.dispose();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
