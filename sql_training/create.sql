create table Customers(
CustomerID int primary key auto_increment not null,
Name varchar(100) not null,
ZipCode varchar(10),
City varchar(100)
);

create table Accounts(
AccountNumber varchar(25) not null primary key,
OpenDate datetime not null,
CreditLine float,
Status varchar(30) not null,
Balance float not null,
CustomerID int not null);

create table Transactions(
TransactionId int primary key auto_increment not null,
Status varchar(30),
DateOfTransaction datetime,
Value float,
Location varchar(100),
AccountNumber varchar(25));

create table Cards(
CardNumber varchar(16) primary key not null,
BuyingLimit Float,
CashWithdrawalLimit int,
Type varchar(30),
Validity datetime,
AccountNumber varchar(25));

create table CardTransactions(
TransactionID int primary key not null auto_increment,
Value float,
Location varchar(100),
Type varchar(30),
DateOfTransaction datetime not null,
CardNumber varchar(16) not null);

ALTER TABLE Accounts ADD CONSTRAINT Customers 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID);
ALTER TABLE CardTransactions ADD CONSTRAINT Cards
    FOREIGN KEY (CardNumber) REFERENCES Cards(CardNumber);
ALTER TABLE Transactions ADD CONSTRAINT Accounts
    FOREIGN KEY (AccountNumber) REFERENCES Accounts(AccountNumber);
ALTER TABLE Cards ADD CONSTRAINT Accounts
    FOREIGN KEY (AccountNumber) REFERENCES Accounts(AccountNumber);
    