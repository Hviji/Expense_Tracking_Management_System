package src.controller;

import src.model.ObjectCreation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class display3 {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();

    public void displayExpenseOverview(int userId) {
        try {

            PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_expenseoverviewanalysis_query );
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            List<String> categories = new ArrayList<>();
            List<Double> amounts = new ArrayList<>();
            double totalExpenses = 0;
            while (rs.next()) {
                String category = rs.getString("category_name");
                double amount = rs.getDouble("total_expenses");
                categories.add(category);
                amounts.add(amount);
                totalExpenses += amount;
            }
            displayPieChart(categories, amounts, totalExpenses);
        } catch (SQLException e) {
            System.out.println("Error fetching expense overview: " + e.getMessage());
        }
    }
    private void displayPieChart(List<String> categories, List<Double> amounts, double total) {
        System.out.println("\nExpense Overview by Category :");
        for (int i = 0; i < categories.size(); i++) {
            double percentage = (amounts.get(i) / total) * 100;
            String bar = new String(new char[(int) percentage]).replace('\0', '*');
            System.out.printf("%-15s | %s (%.2f%%)\n", categories.get(i), bar, percentage);
        }
    }

    public void displayIncomeOverview(int userId) {
        try {

            PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_incomeoverviewanalysis_query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<String> incomeCategories = new ArrayList<>();
            List<Double> incomeAmounts = new ArrayList<>();
            double totalIncome = 0;

            while (rs.next()) {
                String category = rs.getString("incomecategory_name");
                double amount = rs.getDouble("total_income");
                incomeCategories.add(category);
                incomeAmounts.add(amount);
                totalIncome += amount;
            }

            displayIncomePieChart(incomeCategories, incomeAmounts, totalIncome);
        } catch (SQLException e) {
            System.out.println("Error fetching income overview: " + e.getMessage());
        }
    }
    private void displayIncomePieChart(List<String> categories, List<Double> amounts, double total) {
        System.out.println("\nIncome Overview :");
        if (total <= 0) {
            System.out.println("No income recorded.");
            return;
        }
        for (int i = 0; i < categories.size(); i++) {
            double amount = amounts.get(i);
            if (amount > 0) {
                double percentage = (amount / total) * 100;
                String bar = new String(new char[(int) percentage]).replace('\0', '*');
                System.out.printf("%-15s | %s (%.2f%%)\n", categories.get(i), bar, percentage);
            }
        }
    }

    public void displayExpenseFlow(int userId) {

        displayExpenseFlowByDay(userId);
    }

    private void displayExpenseFlowByDay(int userId) {
        System.out.println("\nDaily Expense Flow :");
        try {
            String query = "SELECT expense_date, COALESCE(SUM(amount), 0) AS total_expense " +
                    "FROM expenses WHERE user_id = ? GROUP BY expense_date ORDER BY expense_date";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("expense_date");
                double totalExpense = rs.getDouble("total_expense");
                String bar = new String(new char[(int) (totalExpense / 10)]).replace('\0', '#');
                System.out.printf("%-15s | %s (%.2f)\n", date, bar, totalExpense);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching daily expense flow: " + e.getMessage());
        }
    }

    public void displayIncomeFlow(int userId) {
        System.out.println("\nDaily Income Flow:");
        System.out.printf("%-15s | %-100s | (Total Income)\n", "Date", "Income Bar");

        try {
            PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_incomeflowanalysis_query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("income_date");
                double totalIncome = rs.getDouble("total_income");
                int maxBarLength = 100;
                int barLength = 0;
                if (totalIncome >= 0) {
                    barLength = (int) (totalIncome / 10);
                }

                if (barLength > maxBarLength) {
                    barLength = maxBarLength;
                }

                String bar = new String(new char[barLength]).replace('\0', '*');
                System.out.printf("%-15s | %-100s | %f\n", date, bar, totalIncome);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching daily income flow: " + e.getMessage());
        }
    }

    public void displayAccountAnalysis(int userId) {
        try (PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_accountanalysis_query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAccount Analysis:");

            while (rs.next()) {
                String accountType = rs.getString("account_type");
                double totalIncome = rs.getDouble("total_income");


                System.out.printf("Account Type: %s | Total Income: %.2f %n",
                        accountType, totalIncome);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDateWiseAllRecords(int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_datawisehistory_query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        System.out.println("Date-wise Expenses (All Categories):");
        System.out.printf("%-15s %-20s %-20s %-10s %-30s%n", "Date", "Category", "Subcategory", "Amount", "Description");
        System.out.println("-------------------------------------------------------------------------------");

        while (rs.next()) {
            String categoryName = rs.getString("category_name");
            String subcategoryName = rs.getString("subcategory_name");
            double amount = rs.getDouble("amount");
            java.sql.Date expenseDate = rs.getDate("expense_date");

            System.out.printf("%-15s %-20s %-20s %-10.2f %-30s%n",
                    expenseDate,
                    categoryName,
                    subcategoryName,
                    amount,
                    rs.getString("description"));
        }
    }

    public void displayUserProfile(int userId) {


        try (PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_userprofile_query )) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email_id");
                String mobile = rs.getString("mobile_no");

                System.out.println("User Profile:");
                System.out.println("---------------------------------------------------------------------");
                System.out.printf("Name      : %s%n", name);
                System.out.printf("Email ID  : %s%n", email);
                System.out.printf("Mobile No : %s%n", mobile);
            } else {
                System.out.println("User profile not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve user profile: " + e.getMessage());
        }
    }
}
