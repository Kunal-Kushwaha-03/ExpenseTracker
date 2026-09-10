package com.expensetracker;

import java.time.DateTimeException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();


        boolean running = true;

        System.out.println("Welcome to Expense Tracker!");

        while (running) {

            System.out.println("========== EXPENSE TRACKER ==========");

            System.out.print("1. Add Expense \n");
            System.out.print("2. View Expense \n");
            System.out.print("3. Delete Expense \n");
            System.out.print("4. Add income\n");
            System.out.print("5. View Income\n");
            System.out.print("6. Show Balance \n");
            System.out.print("7. Delete Income \n");
            System.out.print("8. Category-wise Expense Total \n");
            System.out.print("9. summary \n");
            System.out.print("10. Exit \n");

            int choice;

            while (true) {
                System.out.print("Enter your choice :");

                if(scanner.hasNextInt()) {

                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= 10) {
                        break;
                    }

                    System.out.println("Enter number between 1-7");

                } else {
                    System.out.println("Enter A valid number");
                    scanner.next();
                }

            }
            switch (choice) {

                case 1:
                    System.out.print("Add expense selected \n");



                    double amount;

                    while (true) {

                        System.out.print("Enter amount: ");

                        if(scanner.hasNextDouble()) {
                            amount = scanner.nextDouble();

                            if (amount > 0) {
                                break;
                            }

                                System.out.println("Enter Amount grater than 0. ");

                        } else {

                            System.out.println("Enter valid Amount");
                            scanner.next();

                        }
                    }

                    System.out.print("Enter category: ");
                    String category = scanner.next();

                    scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    DateTimeFormatter Formate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    String date = "";

                    LocalDate parseDate = null;

                        while(parseDate == null) {

                            System.out.print("Enter Date (DD-MM-YYYY): ");
                            date = scanner.nextLine();

                            try {

                                parseDate = LocalDate.parse(date, Formate);

                                System.out.print("Validate" + parseDate);

                            } catch (DateTimeException d) {

                                System.out.print("Enter Valid date");
                            }
                        }

                    int id = manager.getNextId();

                    Expense expense = new Expense(
                            id,
                            amount,
                            category,
                            description,
                            date
                    );

                    manager.addExpense(expense);

                    System.out.println("Expense added successfully!");

                    break;

                case 2:

                    System.out.println("\n========== ALL EXPENSES ==========");

                    manager.viewExpense();

//                if (expenses.isEmpty()) {
//
//                    System.out.println("No expenses found.");
//
//                } else {
//
//                    for (Expense e : expenses) {
//
//                        System.out.println("ID: " + e.getid());
//                        System.out.println("Amount: ₹" + e.getAmount());
//                        System.out.println("Category: " + e.getcategory());
//                        System.out.println("Description: " + e.getdiscription());
//                        System.out.println("Date: " + e.getdate());
//
//                        System.out.println("----------------------------");
//                    }
//                }

                    break;

                case 3:

                    System.out.print("Enter Expense ID to delete: ");
                    int deleteId = scanner.nextInt();

                    boolean found = manager.DeleteExpense(deleteId);

                    if (found) {
                        System.out.println("Expense found and deleted.");
                    } else {
                        System.out.println(
                                "Expense ID " + deleteId + " not found."
                        );
                    }
//
//                System.out.print("Enter Expense ID to delete");
//                int deleteId = scanner.nextInt();
//                boolean Found = false;
//
//                for(Expense e : expenses){
//
//                    if ( e.getid() == deleteId ) {
//
//                          expenses.remove(e);
//                          Found = true;
//
//                          System.out.println("Expense found and Deleted");
//                          break;
//                    }
//                }
//                if (!Found){
//                    System.out.println("Expense ID" + deleteId + " not found");
//                }
                    break;

                case 4:

                    double incomeAmount;

                    while(true){

                    System.out.print("Enter income amount: ");

                    if(scanner.hasNextDouble()) {

                    incomeAmount = scanner.nextDouble();

                        if (incomeAmount > 0) {
                            break;
                        }

                        System.out.print("Enter income greater than 0 \n");

                    } else {

                        System.out.println("Enter valid amount");
                        scanner.next();

                    }
                    }

                    System.out.print("Enter income source: ");
                    String source = scanner.next();

                    scanner.nextLine();

                    System.out.print("Enter income Description: ");
                    String IncomeDescription = scanner.nextLine();

                    DateTimeFormatter IncomeFormate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    String IncomeDate = "";

                    LocalDate IncomeparseDate = null;

                    while(IncomeparseDate == null) {

                        System.out.print("Enter Date (DD-MM-YYYY): ");
                        date = scanner.nextLine();

                        try {

                            IncomeparseDate = LocalDate.parse(date, IncomeFormate);

                            System.out.print("Validate" + IncomeparseDate);

                        } catch (DateTimeException d) {

                            System.out.print("Enter Valid date");
                        }
                    }

                    int Incomeid = manager.getNextId();

                    Income income = new Income(
                            Incomeid,
                            incomeAmount,
                            source,
                            IncomeDescription,
                            IncomeDate
                    );

                    manager.addIncome(income);

                    System.out.println("Income added successfully!");

                    break;


                case 5:
                    System.out.println("\n========== ALL Income ==========");

                    manager.viewIncome();
                    break;

                case 6:

                    double Total = manager.getTotalAmount();
                    double Income = manager.getTotalIncome();

                    double balance = Income - Total;

                    System.out.println("\n========== BALANCE ==========");
                    System.out.println("   Total Income   : ₹" + Income);
                    System.out.println(" + Total Expenses : ₹" + Total);
                    System.out.println(" = Balance        : ₹" + balance+"\n" );

//                double Total = 0;
//
//                for (Expense e : expenses){
//
//                    Total += e.getAmount();
//                }
//
//                System.out.println("\n========== TOTAL EXPENSES ==========");
//                System.out.println("Total Expenses: ₹" + Total);

                    break;

                case 7:

                    System.out.print("Enter Income ID to delete: ");
                    int IncomeDeleteId = scanner.nextInt();

                    boolean Incomefound = manager.DeleteIncome(IncomeDeleteId);

                    if (Incomefound) {
                        System.out.println("Income found and deleted.");
                    } else {
                        System.out.println(
                                "Income ID " + IncomeDeleteId + " not found."
                        );
                    }

                    break;
                case 8:

                    scanner.nextLine();

                    System.out.print("Enter category: ");
                    String searchCategory = scanner.nextLine();

                    double categoryTotal = manager.getCategoryTotal(searchCategory);

                    System.out.println("\n========== CATEGORY TOTAL ==========");
                    System.out.println("Category : " + searchCategory);
                    System.out.println("Total    : ₹" + categoryTotal);

                    break;

                case 9:

                    int IncomeCount = manager.CountIncome();
                    int ExpenseCount = manager.CountExpense();

                    System.out.println("\n========== TOTAL COUNT ==========");
                    System.out.println("No. of Expense Added: " + IncomeCount);
                    System.out.println("No. of Income Added: " + ExpenseCount + "\n");

                    break;
                case 10:
                    running = false;
                    System.out.print("EXIT");
                    break;

//                default:
//                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
