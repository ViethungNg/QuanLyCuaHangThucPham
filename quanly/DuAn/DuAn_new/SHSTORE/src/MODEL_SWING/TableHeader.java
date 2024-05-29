/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL_SWING;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author huuho
 */
public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(false);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(10, 5, 10, 5));
        setFont(new Font("sansserif", 1, 12));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setColor(new Color(230, 230, 230));
        graphics2D.fill(new Rectangle(0, getHeight()-1, getWidth(), 1));
    }

}
