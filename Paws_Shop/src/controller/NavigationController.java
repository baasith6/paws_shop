package controller;

import view.MainFrame;

public class NavigationController {
    private MainFrame mainFrame;

    public NavigationController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void navigateTo(String panelName) {
        mainFrame.showCard(panelName);
    }

    public void logout() {
        // Navigate to the login screen
        mainFrame.showCard("Login");
        
        
    }
    
    
}

