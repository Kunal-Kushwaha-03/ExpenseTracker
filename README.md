# Expense Tracker

A console-based Java application to track your income and expenses. Data is stored in a MySQL database using JDBC and the DAO pattern.

## Features

- Add, view, delete and search expenses
- Add and view income
- Category-wise totals
- Current balance
- Summary of income and expenses
- Data saved permanently in MySQL

## Tech Stack

- Java
- MySQL 8.0
- JDBC (MySQL Connector/J)
- IntelliJ IDEA
- Git and GitHub

## Project Structure

```
ExpenseTracker/
└── src/com/expensetracker/
    ├── Main.java                 # Entry point and console menu
    ├── Expense.java              # Expense model
    ├── Income.java               # Income model
    ├── ExpenseManager.java       # Business logic
    ├── DataBaseConnection.java   # MySQL connection
    ├── ExpenseDAO.java           # Database operations for expenses
    └── IncomeDAO.java            # Database operations for income
```

## Setup

### 1. Clone the repository

```
git clone https://github.com/Kunal-Kushwaha-03/ExpenseTracker.git
```

### 2. Create the database

Open MySQL Workbench and run:

```sql
CREATE DATABASE expense_tracker;
USE expense_tracker;

-- Create the tables expenses and income here.
-- Add your CREATE TABLE statements so others can set up the same schema.
```

### 3. Add the MySQL Connector/J library

Download MySQL Connector/J and add the JAR to the project in IntelliJ:
File > Project Structure > Libraries > + > Java.

### 4. Configure the database connection

Open `DataBaseConnection.java` and set your own MySQL URL, username and password. Do not commit your real password to GitHub.

### 5. Run the project

Run `Main.java` from IntelliJ and use the console menu.

## Usage

When the program starts, a menu is shown in the console. Type the number of the option you want, for example add expense, view all expenses or view balance. Dates are entered in DD-MM-YYYY format.

## Future Improvements

- Auto-generated IDs using MySQL AUTO_INCREMENT
- Use LocalDate for dates
- Keep database credentials out of the source code
- Unit tests with JUnit
- Full CRUD testing against the database

## Author

Kunal Kushwaha
GitHub: [Kunal-Kushwaha-03](https://github.com/Kunal-Kushwaha-03)
