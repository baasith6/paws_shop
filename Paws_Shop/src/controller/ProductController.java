package controller;

import javax.swing.*;
import java.sql.*;
import model.Category;
import util.DatabaseConnection;
import util.ValidationUtil;

public class ProductController {

    public boolean addProduct(String name, Category category, String priceStr, String stockQuantityStr) {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
            return false;
        }

        if (category == null) {
            JOptionPane.showMessageDialog(null, "Please select a category.");
            return false;
        }

        double price;
        int stockQuantity;
        try {
            price = Double.parseDouble(priceStr);
            stockQuantity = Integer.parseInt(stockQuantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price and stock quantity must be numeric.");
            return false;
        }

        // Insert into database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO products (name, category_id, price, stock_quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, category.getId());
            stmt.setDouble(3, price);
            stmt.setInt(4, stockQuantity);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding product: " + ex.getMessage());
            return false;
        }
    }
}
