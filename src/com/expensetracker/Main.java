package com.expensetracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {

    // STRICT makes impossible dates like 31-02-2026 invalid instead of silently fixing them.
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        boolean running = true;

        System.out.println("Welcome to Expense Tracker - Summary Edition!");

        while (running) {

            System.out.println("========== EXPENSE TRACKER ==========");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. Add Income");
            System.out.println("5. View Income");
            System.out.println("6. Show Balance");
            System.out.println("7. Delete Income");
            System.out.println("8. Category-wise Expense Total");
            System.out.println("9. Summary");
            System.out.println("10. Exit");

            int choice = readInt(scanner, "Enter your choice: ", 1, 10);

            switch (choice) {

                case 1: {
                    System.out.println("Add expense selected");

                    double amount = readPositiveAmount(scanner, "Enter amount: ");

                    System.out.print("Enter category: ");
                    String category = scanner.next();
                    scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    LocalDate date = readDate(scanner);

                    int id = manager.getNextId();

                    // The converted date (yyyy-MM-dd) is what MySQL needs, not the typed text.
                    Expense expense = new Expense(
                            id,
                            amount,
                            category,
                            description,
                            date.toString()
                    );

                    manager.addExpense(expense);

                    System.out.println("Expense added successfully!");
                    break;
                }

                case 2:
                    System.out.println("\n========== ALL EXPENSES ==========");
                    manager.viewExpense();
                    break;

                case 3: {
                    int deleteId = readInt(scanner, "Enter Expense ID to delete: ", 1, Integer.MAX_VALUE);

                    boolean found = manager.DeleteExpense(deleteId);

                    if (found) {
                        System.out.println("Expense found and deleted.");
                    } else {
                        System.out.println("Expense ID " + deleteId + " not found.");
                    }
                    break;
                }

                case 4: {
                    double incomeAmount = readPositiveAmount(scanner, "Enter income amount: ");

                    System.out.print("Enter income source: ");
                    String source = scanner.next();
                    scanner.nextLine();

                    System.out.print("Enter income description: ");
                    String incomeDescription = scanner.nextLine();

                    LocalDate incomeDate = readDate(scanner);

                    int incomeId = manager.getNextId();

                    Income income = new Income(
                            incomeId,
                            incomeAmount,
                            source,
                            incomeDescription,
                            incomeDate.toString()
                    );

                    manager.addIncome(income);

                    System.out.println("Income added successfully!");
                    break;
                }

                case 5:
                    System.out.println("\n========== ALL INCOME ==========");
                    manager.viewIncome();
                    break;

                case 6: {
                    double totalExpenses = manager.getTotalAmount();
                    double totalIncome = manager.getTotalIncome();
                    double balance = totalIncome - totalExpenses;

                    System.out.println("\n========== BALANCE ==========");
                    System.out.println("   Total Income   : ₹" + totalIncome);
                    System.out.println(" - Total Expenses : ₹" + totalExpenses);
                    System.out.println(" = Balance        : ₹" + balance + "\n");
                    break;
                }

                case 7: {
                    int incomeDeleteId = readInt(scanner, "Enter Income ID to delete: ", 1, Integer.MAX_VALUE);

                    boolean incomeFound = manager.DeleteIncome(incomeDeleteId);

                    if (incomeFound) {
                        System.out.println("Income found and deleted.");
                    } else {
                        System.out.println("Income ID " + incomeDeleteId + " not found.");
                    }
                    break;
                }

                case 8: {
                    scanner.nextLine(); // clear the leftover newline after the menu number

                    System.out.print("Enter category: ");
                    String searchCategory = scanner.nextLine();

                    double categoryTotal = manager.getCategoryTotal(searchCategory);

                    System.out.println("\n========== CATEGORY TOTAL ==========");
                    System.out.println("Category : " + searchCategory);
                    System.out.println("Total    : ₹" + categoryTotal);
                    break;
                }

                case 9: {
                    int incomeCount = manager.CountIncome();
                    int expenseCount = manager.CountExpense();

                    System.out.println("\n========== TOTAL COUNT ==========");
                    System.out.println("No. of Expenses Added: " + expenseCount);
                    System.out.println("No. of Income Added: " + incomeCount + "\n");
                    break;
                }

                case 10:
                    running = false;
                    System.out.println("EXIT");
                    break;
            }
        }

        scanner.close();
    }

    // Keeps asking until the user types a whole number between min and max.
    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Enter a number between " + min + " and " + max + ".");
            } else {
                System.out.println("Enter a valid number.");
                scanner.next();
            }
        }
    }

    // Keeps asking until the user types an amount greater than 0.
    private static double readPositiveAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();

                if (value > 0) {
                    return value;
                }

                System.out.println("Enter an amount greater than 0.");
            } else {
                System.out.println("Enter a valid amount.");
                scanner.next();
            }
        }
    }

    // Keeps asking until the user types a real date in DD-MM-YYYY format.
    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter Date (DD-MM-YYYY): ");
            String text = scanner.nextLine().trim();

            try {
                return LocalDate.parse(text, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use DD-MM-YYYY, for example 10-10-2026.");
            }
        }
    }
}