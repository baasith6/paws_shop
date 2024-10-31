package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import util.DatabaseConnection;


public class ViewProductsPanel extends GradientPanel {
    private MainFrame mainFrame;
    private JTable productTable;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> categorySearchComboBox;
    private JButton backButton;

    public ViewProductsPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        
        

        // Top Panel for search functionality
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setOpaque(false);

        searchField = new JTextField(15);
        searchField.setBackground(Color.WHITE); // Set search field background color to white
        searchField.setForeground(Color.BLACK); // Set search field text color to black for better readability
        categorySearchComboBox = new JComboBox<>(getCategoryNames());
        categorySearchComboBox.setBackground(Color.WHITE); // Set category dropdown background color to white
        categorySearchComboBox.setForeground(Color.BLACK); // Set category dropdown text color to black

        categorySearchComboBox.setEditable(true); // Make the combo box editable for typing/searching

        searchButton = new GlossyButton("Search");
        
        topPanel.add(new JLabel("Search "));
        topPanel.add(searchField);
        topPanel.add(new JLabel("Category "));
        topPanel.add(categorySearchComboBox);
        topPanel.add(searchButton);

        // Center Panel for displaying the table
        productTable = new JTable();
        scrollPane = new JScrollPane(productTable);

        // Back Button
        backButton = new GlossyButton("   Back   ");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load all products initially
        loadProducts(null, null);

        // Action Listeners
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            String category = (String) categorySearchComboBox.getSelectedItem();
            if (category.equals("All Categories")) {
                category = null;
            }
            loadProducts(searchText, category);
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
    
    @Override
    public void addNotify() {
        super.addNotify();
        reloadCategories(); // Reload categories whenever the panel is displayed
    }
    

    private void loadProducts(String searchText, String categoryName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT products.id, products.name, categories.name as category, products.price, products.stock_quantity FROM products JOIN categories ON products.category_id = categories.id");

            boolean hasSearchText = searchText != null && !searchText.isEmpty();
            boolean hasCategory = categoryName != null && !categoryName.isEmpty();

            if (hasSearchText || hasCategory) {
                sql.append(" WHERE");
                if (hasSearchText) {
                    sql.append(" products.name LIKE ?");
                }
                if (hasCategory) {
                    if (hasSearchText) {
                        sql.append(" AND");
                    }
                    sql.append(" categories.name = ?");
                }
            }
            
            

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (hasSearchText) {
                stmt.setString(paramIndex++, "%" + searchText + "%");
            }
            if (hasCategory) {
                stmt.setString(paramIndex++, categoryName);
            }

            ResultSet rs = stmt.executeQuery();

            // Get metadata to dynamically build the table model
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            // Column Names
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columns; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            // Data Rows
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int i = 1; i <= columns; i++) {
                    vector.add(rs.getObject(i));
                   
                }
                data.add(vector);
            }

         // Set Table Model on EDT to refresh UI
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                productTable.setModel(model);
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No products found.");
                }
            });


        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading products: " + ex.getMessage());
        }
    }
    
    public void reloadCategories() {
        categorySearchComboBox.removeAllItems();
        for (String category : getCategoryNames()) {
            categorySearchComboBox.addItem(category);
        }
    }
   
    private String[] getCategoryNames() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT name FROM categories";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            Vector<String> categories = new Vector<>();
            categories.add("All Categories"); // Add default "All Categories" option
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }

            return categories.toArray(new String[0]);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading categories: " + ex.getMessage());
            return new String[0];
        }
    }
    
    
}

