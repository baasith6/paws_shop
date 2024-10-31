package view;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    private Color colorStart;
    private Color colorEnd;
    
    public GradientPanel() {
        // Default to a teal gradient background
        this.colorStart = new Color(0, 128, 128); // Teal color start
        this.colorEnd = new Color(0, 153, 153);   // Teal color end
    }
    
    public GradientPanel(Color colorStart, Color colorEnd) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, h, colorEnd);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
