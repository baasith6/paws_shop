package view;

import javax.swing.*;
import java.awt.*;

public class GlossyButton extends JButton {
    public GlossyButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(Color.WHITE);
        setForeground(new Color(0, 128, 128));
        setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 14f)); // Set custom Abiah font
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int h = getHeight();
        int w = getWidth();

        GradientPaint gp = new GradientPaint(0, 0, new Color(240, 240, 240),
                0, h, new Color(230, 230, 230));
        
        
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, w, h, 15, 15); // Add rounded corners

        super.paintComponent(g);
        g2.dispose();
    }
}

