-- Create Database
CREATE DATABASE IF NOT EXISTS reservation_db;
USE reservation_db;

-- Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Insert Default User
INSERT INTO users (username, password)
VALUES ('teja', 'gteja@123');

-- Reservations Table
CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pnr VARCHAR(20),
    name VARCHAR(100),
    train VARCHAR(100),
    date DATE
);
