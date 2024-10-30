package src.controller;



import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserdbHelper {

    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public double getTotalExpense(int userId) {
        double totalExpenses = 0.0;

        try (PreparedStatement st = conn.prepareStatement(Query.Select_total_expense_query )) {
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                totalExpenses = rs.getDouble("total_expense");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving total expenses: " + e.getMessage());
        }

        return totalExpenses;
    }

    public double getTotalIncome(int userId) {
        double totalIncome = 0.0;
        try (PreparedStatement st = conn.prepareStatement(Query.Select_total_income_query )) {
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                totalIncome = rs.getDouble("total_income");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving total income: " + e.getMessage());
        }

        return totalIncome;
    }

    public double getAccountBalance(int userId, int accountTypeId) {
        double balance = 0.0;


        try (PreparedStatement st = conn.prepareStatement(Query.Select_trasferaccount_query)) {
            st.setInt(1, userId);
            st.setInt(2, accountTypeId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("amount");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving balance: " + e.getMessage());
        }

        return balance;
    }

    public void updateAccountBalance(int userId, int accountTypeId, double amount) throws SQLException {


        try (PreparedStatement st = conn.prepareStatement(Query.Update_accountset_query )) {
            st.setDouble(1, amount);
            st.setInt(2, userId);
            st.setInt(3, accountTypeId);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No account updated. Check if the user ID and account type ID are correct.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error updating account balance: " + e.getMessage(), e);
        }
    }

    public void insertBudget(int userId, int categoryId, double limitAmount, double currentSpent) {
        String query = "INSERT INTO budget (user_id, category_id, limit_amount, current_spent) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setDouble(3, limitAmount);
            stmt.setDouble(4, currentSpent);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new budget record was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting data into the budget table: " + e.getMessage());
        }
    }

}
