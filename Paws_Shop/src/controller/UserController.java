package controller;

import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import java.sql.*;
import util.DatabaseConnection;

public class UserController {

    public boolean createCashier(String name, String username, String password) {
        // Hash the password using jBCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Save the new cashier to the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if username already exists
            String checkSql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
                return false;
            }

            String sql = "INSERT INTO users (name, username, password, role) VALUES (?, ?, ?, 'Cashier')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, hashedPassword);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            return false;
        }
    }
}
