DROP SCHEMA IF EXISTS `ecommerce`;
CREATE SCHEMA `ecommerce`;
USE `ecommerce`;

-- Rename 'user' to 'users' to avoid conflicts
CREATE TABLE users (
    email VARCHAR(128) NOT NULL,
    full_name VARCHAR(128) DEFAULT NULL,
    password VARCHAR(255) NOT NULL,
    enabled TINYINT DEFAULT 1,
    role VARCHAR(45) DEFAULT 'ROLE_USER',
    PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    image_path VARCHAR(255) NOT NULL,  -- Use VARCHAR(255) for file paths or URLs
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0
);

CREATE TABLE carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE
);

CREATE TABLE cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE
);

CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(128) NOT NULL,
    full_name VARCHAR(128) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    card_number VARCHAR(16) NOT NULL,  -- Assuming card number is stored as a string
    expiry_date VARCHAR(5) NOT NULL,  -- Format: MM/YY
    cvv VARCHAR(3) NOT NULL,          -- Assuming CVV is stored as a string
    FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE
);
