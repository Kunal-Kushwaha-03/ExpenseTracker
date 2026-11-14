package com.expensetracker;

import java.util.ArrayList;


public class ExpenseManager {

    private ExpenseDAO expenseDAO = new ExpenseDAO();
    private IncomeDAO incomeDAO = new IncomeDAO();

    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<Income> incomes;
    private int nextId = 1;


    public ExpenseManager() {

        expenses = new ArrayList<>();
        incomes = new ArrayList<>();


        expenses = expenseDAO.getAllExpenses();
        incomes = incomeDAO.getAllIncome();


        int maxId = 0;
        for (Expense e : expenses) {
            if (e.getid() > maxId) maxId = e.getid();
        }
        for (Income i : incomes) {
            if (i.getId() > maxId) maxId = i.getId();
        }
        nextId = maxId + 1;
    }

    public void addExpense(Expense expense) {
        expenseDAO.addExpense(expense);
        expenses.add(expense);
        nextId++;
    }

    public void addIncome(Income income) {
        incomeDAO.addIncome(income);
        incomes.add(income);
        nextId++;
    }

    public double getCategoryTotal(String category){
        double total = 0;

        for(Expense e : expenses){

            if(e.getcategory().equalsIgnoreCase(category)){
                total += e.getAmount();
            }
        }
        return total;
    }


    public void viewIncome() {
        if (incomes.isEmpty()) {
            System.out.println("There is no Income to display");
        } else {
            for (Income i : incomes) {

                System.out.println("ID : " + i.getId());
                System.out.println("Amount : " + i.getAmount());
                System.out.println("Source : " + i.getSource());
                System.out.println("Description : " + i.getDescription());
                System.out.println("Daate : " + i.getDate());

                System.out.println("----------------------------");
            }
        }
    }

    public int CountExpense(){
        return expenses.size();
    }

    public int CountIncome(){
        return incomes.size();
    }

    public double getTotalIncome() {
        double Total = 0;

        for (Income i : incomes) {

            Total += i.getAmount();
        }
        return Total;
    }

    public int getNextId() {

        return nextId;
    }


    public void viewExpense() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses found.");
            return;
        } else {

            for (Expense e : expenses) {

                System.out.println("ID: " + e.getid());
                System.out.println("Amount: ₹" + e.getAmount());
                System.out.println("Category: " + e.getcategory());
                System.out.println("Description: " + e.getDescription());
                System.out.println("Date: " + e.getdate());

                System.out.println("----------------------------");
            }
        }
    }

    public boolean DeleteExpense(int id) {
        for (Expense e : expenses) {

//            Iterator<Expense> iterator = expenses.iterator();

            if (e.getid() == id) {

                expenses.remove(e);
                return true;

            }
        }
        return false;
    }

    public boolean DeleteIncome(int Incomeid){

        for (Income i : incomes){

            if(i.getId() == Incomeid){

            incomes.remove(i);
            return true;

            }
        }

        return false;
    }

    public double getTotalAmount() {

        double Total = 0;

        for (Expense e : expenses) {

            Total += e.getAmount();
        }
        return Total;
    }

}
