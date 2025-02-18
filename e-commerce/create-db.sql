DROP SCHEMA IF EXISTS `ecommerce`;

CREATE SCHEMA `ecommerce`;

USE `ecommerce`;

-- Create a user table for authentication and additional user details
CREATE TABLE `user` (
  `email` VARCHAR(128) NOT NULL,  -- Acts as the username
  `full_name` VARCHAR(128) DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,  -- Store hashed passwords
  `enabled` TINYINT DEFAULT 1,  -- 1 = enabled, 0 = disabled
  `role` VARCHAR(45) DEFAULT 'USER',  -- Default role
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create a products table for products in the e-commerce store
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    image_path TEXT NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0
);

-- Create a carts table, linking carts to users
CREATE TABLE carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,  -- Reference user email, since it's the primary key
    FOREIGN KEY (user_email) REFERENCES user(email) ON DELETE CASCADE
);

-- Create a cart_items table to store items in the cart
CREATE TABLE cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Create an orders table, linking orders to users
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,  -- Reference user email, since it's the primary key
    total_price DECIMAL(10,2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES user(email) ON DELETE CASCADE
);

-- Create an order_items table to store products in each order
CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
