package view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;

public class LoginPanel extends GradientPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;
    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("The Paws Shop");
        titleLabel.setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 40f));
        titleLabel.setForeground(Color.WHITE);

        JLabel usernameLabel = new JLabel("User Name:");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);

        showPasswordCheckBox = new JCheckBox("Show password");
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setForeground(Color.WHITE);

        loginButton = new GlossyButton("Login");

        // Add components to layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(showPasswordCheckBox, gbc);

        gbc.gridy++;
        add(loginButton, gbc);

        // Add action listeners
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char)0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        
        
        loginButton.addActionListener(e -> {
            LoginController loginController = new LoginController(mainFrame,this);
            loginController.authenticateUser(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            
        });
    }
    // Method to clear user name and password fields
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        showPasswordCheckBox.setSelected(false);
        passwordField.setEchoChar('•'); // Reset password echo character
    }
}
