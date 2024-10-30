package src.controller;


import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminHelper3 {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public void budgetOverview() {

        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query. Select_budgetoverview_query );
            ResultSet rs = st.executeQuery();
            System.out.println("+=====================================================================================+");
            System.out.println("|                                            Budget Overview:                                        |");
            System.out.println("+=====================================================================================+");
            System.out.printf(" | %-35s | %-15s |%n", "User ID", "Total Budget");
            System.out.println("+=====================================================================================+");

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                double totalBudget = rs.getDouble("total_budget");
                System.out.printf("|%-35s | %-15s |%n", userId, totalBudget);
            }

            System.out.println("+=====================================================================================+");
        } catch (SQLException e) {
            System.out.println("Error retrieving budget overview: " + e.getMessage());
        }
    }

    public  void AccountTypesOverview() {

        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_accountoverview_query);
            ResultSet rs = st.executeQuery();
            System.out.println("+=====================================================================================+");
            System.out.println("|                               Account Type Overview:                                                                                |");
            System.out.println("+=====================================================================================+");
            System.out.printf("\t\t\t\t%-50s %n", "Account Type");
            System.out.println("+=====================================================================================+");

            while (rs.next()) {
                String Account_type = rs.getString("account_type");

                System.out.printf("%-35s%n", Account_type);
            }

            System.out.println("+=====================================================================================+");
        } catch (SQLException e) {
            System.out.println("Error retrieving payment methods overview: " + e.getMessage());
        }
    }

}
