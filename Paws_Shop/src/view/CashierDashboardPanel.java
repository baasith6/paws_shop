package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import controller.NavigationController;

public class CashierDashboardPanel extends GradientPanel {
    private MainFrame mainFrame;

    public CashierDashboardPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        
        // Title Bar
        TitleBar titleBar = new TitleBar(" Dashboard - Cashier");

        // Side bar Menu
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(4, 1));
        sidebar.setOpaque(false); // Transparent background

        GlossyButton addProductButton = new GlossyButton("Add Product");
        GlossyButton viewProductButton = new GlossyButton("View Products");
        GlossyButton addCategoryButton = new GlossyButton("Add Category");
        GlossyButton logoutButton = new GlossyButton("Logout");

        sidebar.add(addProductButton);
        sidebar.add(viewProductButton);
        sidebar.add(addCategoryButton);
        sidebar.add(logoutButton);

        // Main Content Area
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon(400, 400); // Resize logo to 400x200 pixels for the center display
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } 
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to layout
        add(sidebar, BorderLayout.WEST);
        add(logoLabel, BorderLayout.CENTER);
        add(titleBar, BorderLayout.NORTH);


        // Action listeners
        NavigationController navController = new NavigationController(mainFrame);
        addProductButton.addActionListener(e -> navController.navigateTo("AddProduct"));
        viewProductButton.addActionListener(e -> navController.navigateTo("ViewProducts"));
        addCategoryButton.addActionListener(e -> navController.navigateTo("AddCategory"));
        logoutButton.addActionListener(e -> navController.logout());
    }
    private ImageIcon loadLogoIcon(int width, int height) {
        try {
            // Load the image from resources using an absolute or relative path for testing
            File logoFile = new File("src/resources/logoforpetshop.png"); // Adjust the path to match the new location
            if (logoFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(logoFile.getAbsolutePath());
                // Resize the image
                Image scaledImage = originalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("Could not find logo image at: " + logoFile.getAbsolutePath());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

