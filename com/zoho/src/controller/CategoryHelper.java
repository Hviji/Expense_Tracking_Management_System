package src.controller;


import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryHelper {

    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = new Scanner(System.in);
    public void viewCategories() {

        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_viewcategory_query  );
            ResultSet rs = st.executeQuery();

            System.out.println("+=====================================================================================+");
            System.out.println("|                                       Categories:                                        |");
            System.out.println("+=====================================================================================+");
            System.out.printf(" | %-35s | %-15s |%n", "Category ID", "Category Name");
            System.out.println("+=====================================================================================+");

            while (rs.next()) {
                int categoryId = rs.getInt("id");
                String categoryName = rs.getString("category_name");
                System.out.printf("|%-35s | %-15s |%n", categoryId, categoryName);
            }

            System.out.println("\n+=====================================================================================+");
        } catch (SQLException e) {
            System.out.println("Error retrieving categories: " + e.getMessage());
        }
    }

    public void addCategory() {
        System.out.println("Enter Category Name: ");
        String categoryName = in.next();

        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Insert_addcategory_query);

            st.setString(1, categoryName);
            st.executeUpdate();

            System.out.println("Category added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
    }

    public void editcategory() {
        System.out.println("Enter Category Name: ");
        String categoryName = in.nextLine();

        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_editcategoryID_query);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int categoryId = rs.getInt("id");
                System.out.println("Enter New category Name: ");
                String newcategoryName = in.nextLine();
                PreparedStatement stUpdate = conn.prepareStatement(src.controller.Query.Update_category_query);
                stUpdate.setString(1, newcategoryName);
                stUpdate.setInt(2, categoryId);
                int rowsAffected = stUpdate.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("category updated successfully!");
                } else {
                    System.out.println("Failed to update category.");
                }
            } else {
                System.out.println("category not found. Please enter a valid category name.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating category: " + e.getMessage());
        }
    }

    public void deletecategory() {
        System.out.print("Enter category Name to Delete: ");
        String subcategoryName = in.nextLine();
        try {
            PreparedStatement st = conn.prepareStatement(src.controller.Query. Delete_category_query);
            st.setString(1,subcategoryName);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("category deleted  successfully!");
            } else {
                System.out.println("Category not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting subcategory: " + e.getMessage());
        }
    }
    }


