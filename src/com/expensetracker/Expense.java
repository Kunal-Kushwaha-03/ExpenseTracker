package com.expensetracker;

public class Expense {

    private int id;
    private double amount;
    private String category;
    private String discription;
    private String date;

    public Expense(int id, double amount, String category, String discription, String date) {

        this.id = id;
        this.amount = amount;
        this.category = category;
        this.discription = discription;
        this.date = date;
    }

    public int getid() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getcategory() {

        return category;
    }

    public String getdiscription() {

        return discription;
    }

    public String getdate() {

        return date;
    }


    public void setAmount(double amount) {

        this.amount = amount;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public void setDiscription(String discription) {

        this.discription = discription;
    }

    public void setDate(String date) {

        this.date = date;
    }

}

