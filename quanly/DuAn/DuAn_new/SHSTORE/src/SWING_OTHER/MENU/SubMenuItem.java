package SWING_OTHER.MENU;

import SWING_OTHER.MENU.RippleEffect;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class SubMenuItem extends JButton {

    private final RippleEffect rippleEffect = new RippleEffect(this);
    private final int selected = -1;

    public SubMenuItem(String text) {
        super(text);
//        setFont(new java.awt.Font("SansSerif", 1, 20));
//        setForeground(new Color(51,204,255));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        initStyle();
    }

    private void initStyle() {
        Color color = UIManager.getColor("raven.submenu.ripplecolor");
        if (color != null) {
            rippleEffect.setRippleColor(color);
        } else {
            rippleEffect.setRippleColor(Color.WHITE);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (rippleEffect != null) {
            initStyle();
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        rippleEffect.reder(grphcs, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        Graphics2D gd = (Graphics2D) grphcs.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (FlatDarkLaf.isLafDark()) {
            gd.setColor(Color.WHITE);
            gd.fillRect(0, getHeight() - 1, getWidth(), 1);
        } else {
            gd.setColor(Color.BLACK);
            gd.fillRect(0, getHeight() - 1, getWidth(), 1);
        }
        super.paintComponent(grphcs);
    }
}
