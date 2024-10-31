package util;

public class ValidationUtil {

    public static boolean isValidUsername(String username) {
        // User name must be 5-15 characters long, alphanumeric
        return username.matches("^[a-zA-Z0-9]{5,15}$");
    }

    public static boolean isValidPassword(String password) {
        // Password must be at least 8 characters long
        return password.length() >= 8;
    }

    public static boolean isValidProductName(String name) {
        // Product name should not be empty
        return !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        // Basic email pattern validation
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Validates phone numbers like 123-456-7890 or (123) 456-7890
        return phoneNumber.matches("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$");
    }

    public static boolean isValidPrice(String priceStr) {
        try {
            double price = Double.parseDouble(priceStr);
            // Price should be a positive number
            return price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidStockQuantity(String stockStr) {
        try {
            int stock = Integer.parseInt(stockStr);
            // Stock quantity should be a non-negative integer
            return stock >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidCategoryName(String name) {
        // Category name should not be empty
        return !name.trim().isEmpty();
    }

    public static boolean isValidID(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            // ID should be a positive integer
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Add more validation methods as needed

}
