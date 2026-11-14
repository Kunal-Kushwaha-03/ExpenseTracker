package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ExpenseDAO {

    // Returns true only when the row was really saved, so the caller can show the right message.
    public boolean addExpense(Expense expense) {
        System.out.println("DAO addExpense called");

        String sql = "INSERT INTO expenses (id, amount, category, description, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expense.getid());
            statement.setDouble(2, expense.getAmount());
            statement.setString(3, expense.getcategory());
            statement.setString(4, expense.getDescription());
            statement.setDate(5, toSqlDate(expense.getdate()));

            statement.executeUpdate();

            System.out.println("Expense saved to database!");
            return true;

        } catch (SQLException | DateTimeParseException e) {
            System.out.println("Could not save expense: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        String sql = "SELECT * FROM expenses ORDER BY id";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String description = rs.getString("description");

                // MySQL already gives the date as yyyy-MM-dd, so no extra parsing is needed here.
                java.sql.Date sqlDate = rs.getDate("date");
                String date = (sqlDate == null) ? "" : sqlDate.toString();

                Expense expense = new Expense(
                        id,
                        amount,
                        category,
                        description,
                        date
                );
                expenses.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    // Accepts yyyy-MM-dd (what the database uses) or dd-MM-yyyy (what the user types).
    private java.sql.Date toSqlDate(String text) {
        try {
            return java.sql.Date.valueOf(LocalDate.parse(text));
        } catch (DateTimeParseException e) {
            DateTimeFormatter userFormat = DateTimeFormatter.ofPattern("dd-MM-uuuu");
            return java.sql.Date.valueOf(LocalDate.parse(text, userFormat));
        }
    }
}