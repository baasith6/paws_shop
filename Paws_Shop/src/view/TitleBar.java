package view;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {
    private JLabel titleLabel;

    public TitleBar(String titleText) {
        setLayout(new BorderLayout());
        setOpaque(false); // Transparent background

        titleLabel = new JLabel(titleText, SwingConstants.CENTER);
        titleLabel. setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 40f)); // Set custom Abiah font
        titleLabel.setForeground(Color.WHITE);

        add(titleLabel, BorderLayout.CENTER);

        // Optional: Add a separator line below the title
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(240, 240, 240)); // Light gray as a secondary color
        add(separator, BorderLayout.SOUTH);
    }

    public void setTitleText(String titleText) {
        titleLabel.setText(titleText);
    }
}
