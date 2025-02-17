DROP SCHEMA IF EXISTS `ecommerce`;

CREATE SCHEMA `ecommerce`;

USE `ecommerce`;

-- Create a single user table for authentication and additional user details
CREATE TABLE `user` (
  `email` VARCHAR(128) NOT NULL,  -- Acts as the username
  `full_name` VARCHAR(128) DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,  -- Store hashed passwords
  `enabled` TINYINT DEFAULT 1,  -- 1 = enabled, 0 = disabled
  `role` VARCHAR(45) DEFAULT 'USER',  -- Default role
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
