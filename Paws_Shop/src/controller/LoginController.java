package controller;


import java.sql.*;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;
import util.DatabaseConnection;
import util.ValidationUtil;
import view.MainFrame;
import view.LoginPanel;

public class LoginController {
    private MainFrame mainFrame;
    private LoginPanel loginPanel; // Add reference to LoginPanel
    

    public LoginController(MainFrame mainFrame, LoginPanel loginPanel) {
        this.mainFrame = mainFrame;
        this.loginPanel = loginPanel; // Initialize LoginPanel
    }

    public void authenticateUser(String username, String password) {
        // Trim input to remove extra spaces
        username = username.trim();
        password = password.trim();

        // Validate inputs
        if (!ValidationUtil.isValidUsername(username) || !ValidationUtil.isValidPassword(password)) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid username or password format.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT password, role FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String role = rs.getString("role");

                // Debugging statements
                // System.out.println("Plain Password: " + password);
                // System.out.println("Stored Hash: " + storedHash);

                // Verify the password
                if (BCrypt.checkpw(password, storedHash)) {
                	mainFrame.setCurrentUserRole(role);
                	// Clear fields after successful login
                    loginPanel.clearFields();
                    
                    // Password is correct
                    if (role.equalsIgnoreCase("Manager")) {
                        mainFrame.showCard("AdminDashboard");
                    } else if (role.equalsIgnoreCase("Cashier")) {
                        mainFrame.showCard("CashierDashboard");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid user role.");
                    }
                } else {
                    // Incorrect password
                    JOptionPane.showMessageDialog(mainFrame, "Invalid username or password.");
                }
            } else {
                // User name not found
                JOptionPane.showMessageDialog(mainFrame, "Invalid username or password.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage());
        }
    }
}
