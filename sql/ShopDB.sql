CREATE DATABASE ShopDB;
GO

USE ShopDB;
GO

-- Category Table
CREATE TABLE Category (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL
);

-- Product Table
CREATE TABLE Product (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    ProductName VARCHAR(255) NOT NULL,
    ProductType INT FOREIGN KEY REFERENCES Category(CategoryID),
    Image VARCHAR(255),
    Producer VARCHAR(255),
    Description VARCHAR(500)
);


CREATE TABLE Color(
	ColorID INT IDENTITY(1,1) PRIMARY KEY,
	ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	Color VARCHAR(50)
);

CREATE TABLE Size(
	SizeID INT IDENTITY(1,1) PRIMARY KEY,
	ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	Size VARCHAR(50)
);

-- ProductDetail Table
CREATE TABLE ProductDetail (
    DetailID INT IDENTITY(1,1) PRIMARY KEY,
    ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	ColorID INT FOREIGN KEY REFERENCES Color(ColorID),
	SizeID INT FOREIGN KEY REFERENCES Size(SizeID),
    Quantity INT NOT NULL,
    Price FLOAT NOT NULL
);

-- Role Table
CREATE TABLE Role (
    RoleID INT PRIMARY KEY,
    RoleName VARCHAR(255) NOT NULL
);

-- User Table
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    UserName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role INT FOREIGN KEY REFERENCES Role(RoleID),
    Address VARCHAR(500),
    MobileNumber VARCHAR(20)
);

-- Cart Table
CREATE TABLE Cart (
    UserID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    PRIMARY KEY (UserID, ProductID)
);

-- Receipt Table
CREATE TABLE Receipt (
    UserID INT,
    ReceiptID INT IDENTITY(1,1) PRIMARY KEY,
    TotalPrice FLOAT NOT NULL,
    Address VARCHAR(500),
    MobileNumber VARCHAR(20)
);

-- Order Table
CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    TotalPrice FLOAT NOT NULL
);

-- OrderDetail Table
CREATE TABLE OrderDetail (
    OrderID INT FOREIGN KEY REFERENCES Orders(OrderID),
    ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
    Quantity INT NOT NULL,
    TotalPrice FLOAT NOT NULL,
    PRIMARY KEY (OrderID, ProductID)
);

ALTER TABLE Users ADD Status INT DEFAULT 0;
ALTER TABLE Role ADD Status INT DEFAULT 0;
ALTER TABLE Size ADD Status INT DEFAULT 0;
ALTER TABLE Color ADD Status INT DEFAULT 0;
ALTER TABLE Product ADD Status INT DEFAULT 0;
ALTER TABLE Category ADD Status INT DEFAULT 0;
ALTER TABLE ProductDetail ADD Status INT DEFAULT 0;
ALTER TABLE OrderDetail
ADD DetailID INT;


-- Insert Categories
INSERT INTO Category (CategoryName) VALUES 
('Men Clothing'),
('Women Clothing'),
('Accessories');

-- Insert Products (Clothes)
INSERT INTO Product (ProductName, ProductType, Image, Producer, Description) VALUES 
('Men T-Shirt', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoKE-3Zz-zAVvlvf8L-fGAAz0zU1eGdytZTg&s', 'Nike', 'Cotton T-Shirt for Men'),
('Women Dress', 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI2F6JnGUvF6yaXW-doPKlHQGYwTYSuJelWw&s', 'Zara', 'Elegant summer dress'),
('Leather Belt', 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSunc8GYKMTAP0ITrsJ1jHHJ60jFmicBrRMSw&s', 'Gucci', 'High-quality leather belt');

-- Insert Colors
INSERT INTO Color (ProductID, Color) VALUES 
(3, 'Black'),
(3, 'White'),
(3, 'Red'),
(3, 'Blue'),
(3, 'Brown');

-- Insert Sizes
INSERT INTO Size (ProductID, Size) VALUES 
(3, 'S'),
(3, 'M'),
(3, 'L'),
(3, 'XL'),
(3, 'XXL');

-- Insert Product Details (Quantity & Price)
INSERT INTO ProductDetail (ProductID, ColorID, SizeID, Quantity, Price) VALUES 
(1, 1, 1, 100, 19.99),
(2, 6, 6, 50, 49.99),
(3, 11, 11, 200, 79.99);



-- Insert Roles
INSERT INTO Role (RoleID, RoleName) VALUES 
(1, 'Admin'),
(2, 'Customer');

-- Insert Users
INSERT INTO Users (UserName, Email, Password, Role, Address, MobileNumber) VALUES 
('admin', 'admin@example.com', 'admin123', 1, '123 Admin Street', '1234567890'),
('alice', 'alice@example.com', 'password123', 2, '456 Alice Street', '0987654321');

