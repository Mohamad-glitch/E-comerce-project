DROP USER IF EXISTS 'ecommerce';

CREATE USER 'e-commerce'@'localhost' IDENTIFIED BY 'user123';

GRANT ALL privileges ON * .  * TO  'e-commerce'@'localhost';
