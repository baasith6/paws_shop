package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import controller.NavigationController;

public class AdminDashboardPanel extends GradientPanel {
    private MainFrame mainFrame;

    public AdminDashboardPanel(MainFrame mainFrame) {
    	super(new Color(0, 128, 128), new Color(0, 153, 153)); // Teal background gradient
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        
        // Title Bar
        TitleBar titleBar = new TitleBar(" Dashboard - Manager");

        // Button Panel (under the title bar)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Horizontal alignment with spacing between buttons
        buttonPanel.setOpaque(false); // Transparent background
        
        // Create buttons
        GlossyButton addProductButton = new GlossyButton("Add Product");
        GlossyButton viewProductButton = new GlossyButton("View Product");
        GlossyButton addUserButton = new GlossyButton("Add User");
        GlossyButton addCategoryButton = new GlossyButton("Add Category");
        GlossyButton logoutButton = new GlossyButton("Logout");

     // Add buttons to the button panel
        buttonPanel.add(addProductButton);
        buttonPanel.add(viewProductButton);
        buttonPanel.add(addUserButton);
        buttonPanel.add(addCategoryButton);
        buttonPanel.add(logoutButton);
        
        
        // Main Content Area
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            System.err.println("Logo image not found!");
        }
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

     // Add components to layout
        add(titleBar, BorderLayout.NORTH);       // Add the title bar at the top
        add(buttonPanel, BorderLayout.CENTER);   // Add button panel directly below title bar
        add(logoLabel, BorderLayout.SOUTH);      // Logo label in the center


        // Action listeners
        NavigationController navController = new NavigationController(mainFrame);
        addProductButton.addActionListener(e -> navController.navigateTo("AddProduct"));
        viewProductButton.addActionListener(e -> navController.navigateTo("ViewProducts"));
        addUserButton.addActionListener(e -> navController.navigateTo("AddUser"));
        addCategoryButton.addActionListener(e -> navController.navigateTo("AddCategory"));
        logoutButton.addActionListener(e -> navController.logout());
    }
    private ImageIcon loadLogoIcon() {
    	// Load the image from resources using an absolute or relative path for testing
        try {
            // Use relative path to find the logo file in the resources folder under src
            File logoFile = new File("src/resources/logoforpetshop.png");
            if (logoFile.exists()) {
            	
            	ImageIcon originalIcon = new ImageIcon(logoFile.getAbsolutePath());
                // Resize the image
                Image scaledImage = originalIcon.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
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

