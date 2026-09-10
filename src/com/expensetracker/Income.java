package com.expensetracker;

public class Income {

    private int id;
    private Double amount;
    private String source;
    private String description;
    private String date;

    public Income(int id, double amount, String source, String description, String date) {

        this.id = id;
        this.amount = amount;
        this.source = source;
        this.description = description;
        this.date = date;


    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
