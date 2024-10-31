package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private String currentUserRole;
    private LoginPanel loginPanel;
    private AddProductPanel addProductPanel;
    private ViewProductsPanel viewProductsPanel;

    public MainFrame() {
    	// Apply the custom Abiah font to the entire UI
        util.FontUtil.applyAbiahFontToAll();
        
        setTitle("The Paws Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Initialize panels
        LoginPanel loginPanel = new LoginPanel(this);
        AdminDashboardPanel adminDashboardPanel = new AdminDashboardPanel(this);
        CashierDashboardPanel cashierDashboardPanel = new CashierDashboardPanel(this);
        
   
        // Properly initialize addProductPanel and assign it to the field
        addProductPanel = new AddProductPanel(this); 
        viewProductsPanel = new ViewProductsPanel(this);
        AddUserPanel addUserPanel = new AddUserPanel(this);
        AddCategoryPanel addCategoryPanel = new AddCategoryPanel(this);

        // Add panels to cardsPanel
        cardsPanel.add(loginPanel, "Login");
        cardsPanel.add(adminDashboardPanel, "AdminDashboard");
        cardsPanel.add(cashierDashboardPanel, "CashierDashboard");
        cardsPanel.add(addProductPanel, "AddProduct");
        cardsPanel.add(viewProductsPanel, "ViewProducts");
        cardsPanel.add(addUserPanel, "AddUser");
        cardsPanel.add(addCategoryPanel, "AddCategory");

        // Add the cardsPanel to the frame
        add(cardsPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showCard(String cardName) {
    	if (cardName.equals("Login") && loginPanel != null) {
            loginPanel.clearFields(); // Clear login fields on logout
        }
    	
        cardLayout.show(cardsPanel, cardName);
        
       
        
        
        
    }
    public void setCurrentUserRole(String role) {
        this.currentUserRole = role;
    }

    public String getCurrentUserRole() {
        return currentUserRole;
    }
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    // Getter for AddProductPanel
    public AddProductPanel getAddProductPanel() {
		return addProductPanel;
    }
    public ViewProductsPanel getViewProductsPanel() {
        return viewProductsPanel; // Provide access to ViewProductsPanel
    }
    

    public static void main(String[] args) {
        new MainFrame();
    }
}
