package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeDAO {

    public String date;

    public void addIncome(Income income) {
        String sql = """  
                INSERT INTO income (id, amount, source, description, date) VALUES (?, ?, ?, ?, ?)""";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, income.getId());
            statement.setDouble(2, income.getAmount());
            statement.setString(3, income.getSource());
            statement.setString(4, income.getDescription());
            statement.setString(5, income.getDate());


            statement.executeUpdate();
            System.out.println("Income saved to database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Income> getAllIncome() {
        ArrayList<Income> incomeList = new ArrayList<>();

        String sql = """
                SELECT * from income
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                double amount = rs.getDouble("amount");
                String source = rs.getString("source");
                String description = rs.getString("description");
                String date = rs.getString("date");

                Income income = new Income(
                        id,
                        amount,
                        source,
                        description,
                        date
                );
                incomeList.add(income);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return incomeList;
    }
}

