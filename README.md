# The Paws Shop - Pet Supply Store Application

Welcome to **The Paws Shop**, a pet supply store management system designed to facilitate the management of pet products, categories, users, and inventory. This Java Swing application features a user-friendly interface for adding, viewing, and managing products and users, designed for a store environment.

## Features

- **User Roles**: Supports two user roles: Manager and Cashier, each with different access rights.
- **Add Product**: Allows managers and cashiers to add new pet products, including product name, category, price, and stock quantity.
- **View Products**: Search and filter products by name or category. Automatically updated to reflect changes like adding new products.
- **Manage Categories**: Add new product categories, which will dynamically update throughout the application.
- **User Management**: Managers can add new cashier users.
- **Login and Logout Functionality**: Secure user authentication and easy navigation between roles.
- **Beautiful UI**: Glossy buttons, gradient panels, and a cohesive color scheme for an engaging user experience.

## Tech Stack

- **Java SE**: Core logic and user interface built using Java Swing.
- **MySQL**: Backend database for storing user data, product information, and categories.
- **jBCrypt**: For secure password hashing.
- **JDBC**: For database connectivity and operations.

## Prerequisites

- **Java Development Kit (JDK)** 11 or higher.
- **MySQL Server**: Ensure MySQL is installed and running.
- **IDE**: Eclipse, IntelliJ, or any other Java IDE.

## Getting Started

### 1. Clone the Repository
Clone this repository to your local machine using the following command:
```bash
$ git clone https://github.com/baasith6/paws_shop.git
```

### 2. Setup Database
Create the database using the following SQL commands:

```sql
CREATE DATABASE paws_shop;
USE paws_shop;

-- Create users table
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  username VARCHAR(20) UNIQUE,
  password VARCHAR(100),
  role VARCHAR(10)
);

-- Create categories table
CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)
);

-- Create products table
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  category_id INT,
  price DOUBLE,
  stock_quantity INT,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Add default manager
-- u can hash the password using PasswordHasher.java (this file available in this project)
--Login user Name - admin , password - 12345678;
--password must be in 8 character
--username must be in 5 character 
INSERT INTO users (name, username, password, role)
VALUES ('Admin', 'admin', '$2a$10$LxyFaEOIUFZ4V6PLPAKuZ.bUCvmEetukGAivCv5omsVNxEuqOrCWW', 'Manager');
```

### 3. Configure Database Connection
Update the `DatabaseConnection` class (`util/DatabaseConnection.java`) with your MySQL credentials:

```java
private static final String URL = "jdbc:mysql://localhost:3306/paws_shop";
private static final String USERNAME = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

### 4. Run the Application
- Open the project in your preferred IDE.
- Run the `MainFrame` class (`view/MainFrame.java`) to launch the application.

## Usage

1. **Login**: Use the credentials to log in.
   - Default Manager: Username: `admin`, Password: `12345678`.

2. **Manager Dashboard**: After logging in as a manager, you can:
   - Add new products, categories, and users.
   - View all available products.

3. **Cashier Dashboard**: After logging in as a cashier, you can:
   - Add new products.
   - View and search products and filterd by category.

4. **Logout**: Click the `Logout` button to safely log out.

## Project Structure

- **view/**: Contains all the UI components and panels, such as `LoginPanel`, `AdminDashboardPanel`, and `CashierDashboardPanel`.
- **controller/**: Contains the controller classes like `LoginController` and `ProductController` for handling logic and user interactions.
- **model/**: Contains the data models such as `Category` and `Product`.
- **util/**: Utility classes for database connection (`DatabaseConnection`) and other helpers.

## Screenshots

### Login Screen
![Login Screen](resources/screenshots/login_screen.png)

### Admin Dashboard
![Admin Dashboard](resources/screenshots/admin_dashboard.png)

### View Products
![View Products](resources/screenshots/view_products.png)

## Troubleshooting

- **LoginPanel is null**: Ensure the `LoginPanel` is properly initialized in `MainFrame` and accessible when logging out.
- **Database Connection Issues**: Verify the database credentials in `DatabaseConnection` and ensure the MySQL server is running.
- **Image Not Loading**: Check that the image paths are correct and that resources are available in the specified folder.

## Contributing
Feel free to submit pull requests to contribute to the project. You can also open issues for any bugs or feature requests.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Acknowledgments

- **Java Swing Documentation** for the detailed components.
- **jBCrypt** for secure password hashing.
- **MySQL** for reliable database management.

Enjoy managing your pet store efficiently with **The Paws Shop**!

If you have any questions, feel free to reach out.

