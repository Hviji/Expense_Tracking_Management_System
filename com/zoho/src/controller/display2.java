package src.controller;


import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class display2 {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public int displaySubCategories(int categoryId) {
        boolean status = true;
        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_subcategories_by_category_query2);
            st.setInt(1,categoryId);
            ResultSet rs = st.executeQuery();

            System.out.println("Available Subcategories:");
            while (rs.next()) {
                status=false;
                String subcategoryName = rs.getString("subcategory_name");
                System.out.println("-" + subcategoryName);
            }
            if(status)
            {
                System.out.println("No available Sub Categories in this field :");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving subcategories: " + e.getMessage());
        }
        return 1;
    }

    public void displayTotalBudgetAndSpending(int userId) {
        try {
            PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_totalspendbudget_query );
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double totalBudget = rs.getDouble("total_budget");
                double totalSpent = rs.getDouble("total_spent");

                System.out.println("Total Budget: " + totalBudget);
                System.out.println("Total Spent: " + totalSpent);
                System.out.println("Remaining: " + (totalBudget - totalSpent));
            } else {
                System.out.println("No budget set for this user.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing total budget and spending: " + e.getMessage());
        }
    }
    public void displayCategoryWiseBudget(int userId) {
        try {

            PreparedStatement stmt = conn.prepareStatement(src.controller.Query.Select_categorywise_query );
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("category_name");
                double limit = rs.getDouble("limit_amount");
                double spent = rs.getDouble("current_spent");
                double remaining = rs.getDouble("remaining_amount");

                System.out.println("Category: " + category);
                System.out.println("Budget Limit: " + limit);
                System.out.println("Spent: " + spent);
                System.out.println("Remaining: " + remaining);
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing category-wise budget: " + e.getMessage());
        }
    }
}
