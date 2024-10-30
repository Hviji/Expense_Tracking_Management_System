package src.controller;

import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class display {
Scanner in = ObjectCreation.getInInstanceofScanner();
Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public void displayAccountTypes() {
        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_account_types_query);
            ResultSet rs = st.executeQuery();

            System.out.println("Available Account Types:");
            while (rs.next()) {
                String accountType = rs.getString("account_type");
                System.out.println("- " + accountType);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving account types: " + e.getMessage());
        }
    }

    public void displayIncomeCategories() {
        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_income_categories_query);
            ResultSet rs = st.executeQuery();

            System.out.println("Available Income Categories:");
            while (rs.next()) {
                String incomeCategory = rs.getString("incomecategory_name");
                System.out.println("- " + incomeCategory);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving income categories: " + e.getMessage());
        }
    }
    public void displayCategories() {
        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_categories_query);
            ResultSet rs = st.executeQuery();

            System.out.println("Available Categories:");
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                System.out.println("-"+ categoryName);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving categories: " + e.getMessage());
        }
    }
}