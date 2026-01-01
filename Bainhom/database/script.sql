-- 1. Tạo Database
CREATE DATABASE PetHotelDB;
GO
USE PetHotelDB;
GO

-- 2. Xóa bảng cũ nếu tồn tại
DROP TABLE IF EXISTS BookingServices;
DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Services;
DROP TABLE IF EXISTS Cages;
DROP TABLE IF EXISTS Pets;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Users;
GO

-- 3. Tạo bảng Users (Quản trị viên & Nhân viên)
CREATE TABLE Users (
                       UserID INT IDENTITY(1,1) PRIMARY KEY,
                       Username VARCHAR(50) NOT NULL UNIQUE,
                       Password VARCHAR(100) NOT NULL,
                       FullName NVARCHAR(100),
                       Role NVARCHAR(20) DEFAULT 'Staff'
);

-- 4. Tạo bảng Customers (Khách hàng)
CREATE TABLE Customers (
                           CustomerID INT IDENTITY(1,1) PRIMARY KEY,
                           FullName NVARCHAR(100) NOT NULL,
                           PhoneNumber VARCHAR(15) NOT NULL UNIQUE,
                           Email VARCHAR(100),
                           Address NVARCHAR(200),
                           Note NVARCHAR(MAX)
);

-- 5. Tạo bảng Pets (Thú cưng)
CREATE TABLE Pets (
                      PetID INT IDENTITY(1,1) PRIMARY KEY,
                      PetName NVARCHAR(100) NOT NULL,
                      Type NVARCHAR(50),
                      Breed NVARCHAR(100),
                      Weight FLOAT,
                      HealthStatus NVARCHAR(MAX),
                      CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

-- 6. Tạo bảng Cages (Chuồng/Phòng)
CREATE TABLE Cages (
                       CageID INT IDENTITY(1,1) PRIMARY KEY,
                       CageName NVARCHAR(50) NOT NULL,
                       Type NVARCHAR(50),
                       PricePerDay DECIMAL(18,2) NOT NULL,
                       Status NVARCHAR(20) DEFAULT 'Trong'
);

-- 7. Tạo bảng Services (Dịch vụ thêm)
CREATE TABLE Services (
                          ServiceID INT IDENTITY(1,1) PRIMARY KEY,
                          ServiceName NVARCHAR(100) NOT NULL,
                          Price DECIMAL(18,2) NOT NULL,
                          Unit NVARCHAR(20)
);

-- 8. Tạo bảng Bookings (Đơn đặt phòng)
CREATE TABLE Bookings (
                          BookingID INT IDENTITY(1,1) PRIMARY KEY,
                          CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID),
                          PetID INT FOREIGN KEY REFERENCES Pets(PetID),
                          CageID INT FOREIGN KEY REFERENCES Cages(CageID),

                          CheckInDate DATETIME NOT NULL,
                          CheckOutDate DATETIME,

                          Status NVARCHAR(20) DEFAULT 'Pending',
                          PaymentStatus NVARCHAR(20) DEFAULT 'Unpaid',
                          TotalPrice DECIMAL(18,2) DEFAULT 0,
                          CreatedDate DATETIME DEFAULT GETDATE()
);

-- 9. Tạo bảng BookingServices (Chi tiết dịch vụ dùng thêm)
CREATE TABLE BookingServices (
                                 DetailID INT IDENTITY(1,1) PRIMARY KEY,
                                 BookingID INT FOREIGN KEY REFERENCES Bookings(BookingID) ON DELETE CASCADE,
                                 ServiceID INT FOREIGN KEY REFERENCES Services(ServiceID),
                                 Quantity INT DEFAULT 1,
                                 PriceAtBooking DECIMAL(18,2)
);
GO