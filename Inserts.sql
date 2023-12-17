CREATE TABLE msuser(
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    UserName VARCHAR(255) UNIQUE NOT NULL,
    UserPassword VARCHAR(255) NOT NULL,
    UserRole VARCHAR(255) NOT NULL,
    UserAge INT NOT NULL
);

CREATE TABLE mspc (
    PC_ID INT PRIMARY KEY AUTO_INCREMENT,
    PC_Condition VARCHAR(255) NOT NULL
);

CREATE TABLE mspcbook (
    BookID INT PRIMARY KEY AUTO_INCREMENT,
    PC_ID INT NOT NULL,
    UserID INT NOT NULL,
    BookedDate DATETIME DEFAULT NOW(),
    FOREIGN KEY (PC_ID) REFERENCES mspc(PC_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (UserID) REFERENCES msuser(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE transactionheader(
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    StaffID INT NOT NULL,
    StaffName VARCHAR(255) NOT NULL,
    TransactionDate DATETIME DEFAULT NOW(),
    FOREIGN KEY (StaffID) REFERENCES msuser(UserID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (StaffName) REFERENCES msuser(UserName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE transactiondetail (
    TransactionID INT AUTO_INCREMENT,
    PC_ID INT,
    CustomerName VARCHAR(255) NOT NULL,
    BookedTime DATETIME DEFAULT NOW(),
    PRIMARY KEY (TransactionID, PC_ID),
    FOREIGN KEY (TransactionID) REFERENCES transactionheader(TransactionID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PC_ID) REFERENCES mspc(PC_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CustomerName) REFERENCES msuser(UserName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE msjob (
    Job_ID INT AUTO_INCREMENT,
    UserID INT NOT NULL,
    PC_ID INT NOT NULL,
    JobStatus VARCHAR(255) NOT NULL,
    PRIMARY KEY (Job_ID, UserID, PC_ID),
    FOREIGN KEY (UserID) REFERENCES msuser(UserID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PC_ID) REFERENCES mspc(PC_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE msreport (
    Report_ID INT AUTO_INCREMENT,
    UserRole VARCHAR(255) NOT NULL,
    PC_ID INT NOT NULL,
    ReportNote VARCHAR(255) NOT NULL,
    ReportDate DATETIME DEFAULT NOW(),
    PRIMARY KEY (Report_ID, UserRole, PC_ID),
    FOREIGN KEY (PC_ID) REFERENCES mspc(PC_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO mspc(PC_Condition) VALUES
("Usable"), ("Maintenance"), ("Broken"), 
("Usable"), ("Maintenance"), ("Broken"), 
("Usable"), ("Maintenance"), ("Broken"), 
("Usable"), ("Maintenance"), ("Broken"),
("Usable"), ("Maintenance"), ("Broken");

INSERT INTO msuser VALUES
(1, 'averine08', 'ave123', 'Customer', 20),
(2, 'user2', 'ave123', 'Customer', 22),
(3, 'aveAdmin', 'ave123', 'Admin', 20),
(4, 'aveOp', 'ave123', 'Operator', 20),
(5, 'filbert', 'test345', 'Operator', 20),
(6, 'user6', 'ave123', 'Computer Technician', 22),
(7, 'daniel', 'ave123', 'Computer Technician', 21);

INSERT INTO mspcbook(PC_ID, UserID, BookedDate) 
VALUES(1, 2, NOW()), (2, 1, NOW()), (3, 1, NOW()), (4, 1, NOW()), (5, 1, NOW());

INSERT INTO transactionheader (TransactionID, StaffID, StaffName, TransactionDate) VALUES 
(1,2,'AveAdmin', NOW()),
(2,3, 'AveOp', NOW());

INSERT INTO transactiondetail (TransactionID, PC_ID, CustomerName, BookedTime) VALUES 
(1, 1, 'averine08', NOW());


	
