package controller;

import javax.swing.*;
import java.sql.*;
import util.DatabaseConnection;

public class CategoryController {

    public boolean addCategory(String categoryName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if category already exists
            String checkSql = "SELECT id FROM categories WHERE name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, categoryName);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Category already exists.");
                return false;
            }

            // Insert new category
            String sql = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoryName);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            return false;
        }
    }
}
