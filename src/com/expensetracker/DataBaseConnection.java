package com.expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static final String URL ="jdbc:mysql://localhost:3306/expense_tracker";
    public static final String USER = "root";
    public static final String PASSWORD = "Kunal@2610";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try {
            Connection connection = getConnection();

            System.out.println("Database connected successfully!");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
