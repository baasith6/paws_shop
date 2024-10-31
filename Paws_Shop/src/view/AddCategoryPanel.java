package view;

import javax.swing.*;
import java.awt.*;
import controller.CategoryController;
import util.ValidationUtil;


public class AddCategoryPanel extends GradientPanel {
    private MainFrame mainFrame;
    private JTextField categoryNameField;
    private JButton addButton;
    private JButton backButton;

    public AddCategoryPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        
        
        
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Add New Category");
        titleLabel.setFont(util.FontUtil.getAbiahFont().deriveFont(Font.BOLD, 40f));
        titleLabel.setForeground(Color.WHITE);

        JLabel categoryLabel = new JLabel("Category Name:");
        categoryLabel.setForeground(Color.WHITE);
        categoryNameField = new JTextField(20);

        addButton = new GlossyButton("Add Category");
        backButton = new GlossyButton("Back");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST; gbc.gridwidth = 1;
        gbc.gridy++;
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        add(categoryNameField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(backButton, gbc);

        // Action Listeners
        addButton.addActionListener(e -> {
            String categoryName = categoryNameField.getText().trim();
            if (categoryName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a category name.");
                return;
            }

            if (!ValidationUtil.isValidCategoryName(categoryName)) {
                JOptionPane.showMessageDialog(this, "Invalid category name.");
                return;
            }

            CategoryController categoryController = new CategoryController();
            boolean success = categoryController.addCategory(categoryName);
            if (success) {
                JOptionPane.showMessageDialog(this, "Category added successfully.");
                categoryNameField.setText("");

                // Now reload categories in AddProductPanel
                mainFrame.getAddProductPanel().reloadCategories();
                
             // Notify ViewProductsPanel to reload categories
                mainFrame.getViewProductsPanel().reloadCategories();
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

