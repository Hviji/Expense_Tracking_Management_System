package src.controller;

import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomeroverviewHelper {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in =ObjectCreation.getInInstanceofScanner();

    src.controller.UserdatabaseHelper dbHelper = new src.controller.UserdatabaseHelper();
    src.controller.UserdbHelper dbhelper = new src.controller.UserdbHelper();
    src.controller.display d = new src.controller.display();
    src.controller.display2 dd = new src.controller.display2();
    public void addExpense(int userId) {
        try {
            d.displayAccountTypes();
            in.nextLine();
            System.out.print("Select account type (as displayed above): ");
            String accountType = in.nextLine();
            int accountTypeId = dbHelper.getAccountTypeId(accountType);

            d.displayCategories();
            System.out.print("Select category (as displayed above): ");
            String category = in.nextLine();
            int categoryId = dbHelper.getCategoryId(category);

            dd.displaySubCategories(categoryId);
            System.out.print("Select subcategory (optional, press Enter to skip): ");
            String subcategory = in.nextLine();
            Integer subcategoryId = null;
            if (!subcategory.isEmpty()) {
                subcategoryId = dbHelper.getSubCategoryId(subcategory);
            }
            System.out.print("Enter expense amount (in rupee): ");
            double expenseAmount = in.nextDouble();
            in.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = in.nextLine();
            System.out.print("Enter description: ");
            String description = in.nextLine();
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Insert_expense_query);
            st.setInt(1, userId);
            st.setInt(2, accountTypeId);
            st.setInt(3, categoryId);
            if (subcategoryId != null) {
                st.setInt(4, subcategoryId);
            } else {
                st.setNull(4, java.sql.Types.INTEGER);
            }
            st.setDouble(5, expenseAmount);
            st.setString(6, date);
            st.setString(7, description);
            st.executeUpdate();
            updateBudget(userId, categoryId, expenseAmount);

            double totalIncome = dbhelper.getTotalIncome(userId);
            double totalExpenses = dbhelper.getTotalExpense(userId);
            double remainingBalance = totalIncome - totalExpenses;
            System.out.println("Expense added successfully!");
            System.out.println("Remaining balance: " + remainingBalance);
        } catch (SQLException e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }
    private void updateBudget(int userId, int categoryId, double expenseAmount) {

        try (PreparedStatement updateStatement = conn.prepareStatement(src.controller.Query.UPDATE_spendamount_query)) {
            updateStatement.setDouble(1, expenseAmount);
            updateStatement.setInt(2, categoryId);
            updateStatement.setInt(3, userId);
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Budget updated successfully for category ID: " + categoryId);
            } else {
                System.out.println("No budget entry found for user ID: " + userId + " and category ID: " + categoryId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating budget: " + e.getMessage());
        }
    }

    public void transferBetweenAccounts(int userId) {
        try {
            d.displayAccountTypes();
            in.nextLine();
            System.out.print("Select sender account type: ");
            String senderAccountType = in.nextLine();
            int senderAccountTypeId = dbHelper.getAccountTypeId(senderAccountType);

            double senderBalance = dbhelper.getAccountBalance(userId, senderAccountTypeId);
            System.out.println("Current balance in " + senderAccountType + " account:" + senderBalance);

            System.out.print("Select receiver account type: ");
            String receiverAccountType = in.nextLine();
            int receiverAccountTypeId = dbHelper.getAccountTypeId(receiverAccountType);

            if (senderAccountTypeId == receiverAccountTypeId) {
                System.out.println("Cannot transfer between the same account!");
                return;
            }
            System.out.print("Enter transfer amount: ");
            double transferAmount = in.nextDouble();
            in.nextLine();
            if (transferAmount > senderBalance) {
                System.out.println("Insufficient funds in sender's account!");
                return;
            }
            conn.setAutoCommit(false);
            dbhelper.updateAccountBalance(userId, senderAccountTypeId, -transferAmount);
            dbhelper.updateAccountBalance(userId, receiverAccountTypeId, transferAmount);
            try (PreparedStatement insertSt = conn.prepareStatement(src.controller.Query.Insert_transfer_query )) {
                insertSt.setInt(1, userId);
                insertSt.setInt(2, senderAccountTypeId);
                insertSt.setInt(3, receiverAccountTypeId);
                insertSt.setDouble(4, transferAmount);
                insertSt.executeUpdate();
            }
            conn.commit();
            System.out.println("Transfer of " + transferAmount + " from " + senderAccountType +
                    " to " + receiverAccountType + " successful!");
        } catch (SQLException e) {
            System.out.println("Error during transfer: " + e.getMessage());
            try {
                conn.rollback();
                System.out.println("Transaction back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException resetEx) {
                System.out.println("Error resetting auto-commit: " + resetEx.getMessage());
            }
        }
    }
}
