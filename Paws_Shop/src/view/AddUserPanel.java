package view;

import javax.swing.*;
import java.awt.*;
import controller.UserController;
import util.ValidationUtil;
import view.TitleBar;
public class AddUserPanel extends GradientPanel {
    private MainFrame mainFrame;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addButton;
    private JButton backButton;

    public AddUserPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Cashier");
        titleLabel.setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 40f));
        titleLabel.setForeground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);

        addButton = new GlossyButton("Add Cashier");
        backButton = new GlossyButton("Back");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST; gbc.gridwidth = 1;
        gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(backButton, gbc);

        // Action Listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (!ValidationUtil.isValidUsername(username)) {
                JOptionPane.showMessageDialog(this, "Invalid username format.");
                return;
            }

            if (!ValidationUtil.isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters.");
                return;
            }

            UserController userController = new UserController();
            boolean success = userController.createCashier(name, username, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cashier added successfully.");
                // Clear fields
                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        backButton.addActionListener(e -> {
            String role = mainFrame.getCurrentUserRole();
            if ("Manager".equalsIgnoreCase(role)) {
                mainFrame.showCard("AdminDashboard");
            } else if ("Cashier".equalsIgnoreCase(role)) {
                mainFrame.showCard("CashierDashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Unknown user role.");
            }
        });

    }
}

