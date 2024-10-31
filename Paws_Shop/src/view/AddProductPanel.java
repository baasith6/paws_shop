package view;

import javax.swing.*;
import java.awt.*;
import controller.ProductController;
import model.Category;
import view.TitleBar;
import java.sql.*;
import java.util.ArrayList;
import util.DatabaseConnection;



public class AddProductPanel extends GradientPanel {
    private JTextField productNameField;
    private JComboBox<Category> categoryComboBox;
    private JTextField priceField;
    private JTextField stockQuantityField;
    private JButton addButton;
    private MainFrame mainFrame;
    private JButton backButton;

    public AddProductPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        
        

        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 40f));
        titleLabel.setForeground(Color.WHITE);

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setForeground(Color.WHITE);
        productNameField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(Color.WHITE);
        categoryComboBox = new JComboBox<>(getCategories());

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setForeground(Color.WHITE);
        priceField = new JTextField(10);

        JLabel stockLabel = new JLabel("Stock Quantity:");
        stockLabel.setForeground(Color.WHITE);
        stockQuantityField = new JTextField(5);

        addButton = new GlossyButton("  Add  ");
        backButton = new GlossyButton("  Back  ");

        // Add components to layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(productNameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        add(categoryComboBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(priceLabel, gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(stockLabel, gbc);
        gbc.gridx = 1;
        add(stockQuantityField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(addButton, gbc);
        
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(backButton, gbc);
        
     // Load categories initially
        reloadCategories();
        


        // Action listener
        addButton.addActionListener(e -> {
            ProductController productController = new ProductController();
            boolean success = productController.addProduct(productNameField.getText(),
                    (Category) categoryComboBox.getSelectedItem(),
                    priceField.getText(),
                    stockQuantityField.getText());

            if (success) {
                JOptionPane.showMessageDialog(this, "Product added successfully.");
                // Clear input fields
                productNameField.setText("");
                priceField.setText("");
                stockQuantityField.setText("");
                categoryComboBox.setSelectedIndex(0);
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
    
    public void reloadCategories() {
        categoryComboBox.removeAllItems();
        for (Category category : getCategories()) {
            categoryComboBox.addItem(category);
        }
    }
    

    private Category[] getCategories() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, name FROM categories";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Category> categoryList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categoryList.add(category);
            }

            return categoryList.toArray(new Category[0]);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading categories: " + ex.getMessage());
            return new Category[0];
        }
    }
    
    // Optionally, override addNotify to reload categories when the panel is displayed
    @Override
    public void addNotify() {
        super.addNotify();
        reloadCategories();
    }

}
