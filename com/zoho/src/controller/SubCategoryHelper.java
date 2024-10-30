package src.controller;

import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SubCategoryHelper {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    public void viewSubcategories() {
        try  {
            PreparedStatement st = conn.prepareStatement(Query.Select_subviewcategory_query );
            ResultSet rs = st.executeQuery();

            System.out.println("+=========================================================================================================================+");
            System.out.println("|                                                      Sub - Categories:                                        |");
            System.out.println("+=========================================================================================================================+");

            System.out.printf("| %-15s | %-40s | %-35s | %-15s |%n", "Subcategory ID","Category", "Subcategory Name", "Description");

            System.out.println("+=========================================================================================================================+");

            while (rs.next()) {
                int subcategoryId = rs.getInt("subcategory_id");
                String categoryName = rs.getString("category_name");
                String subcategoryName = rs.getString("subcategory_name");
                String description = rs.getString("description");
                System.out.printf("| %-15d | %-40s | %-35s | %-15s |%n", subcategoryId,categoryName, subcategoryName, description);
            }

            System.out.println("\n+=========================================================================================================================+");
        } catch (SQLException e) {
            System.out.println("Error retrieving subcategories: " + e.getMessage());
        }
    }


    public void addSubcategory() {
        System.out.println("Enter Category Name: ");
        String categoryName = in.next();
        in.nextLine();

        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_subaddcategoryID_query);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int categoryId = rs.getInt("id");

                boolean addMore = true;
                while (addMore) {
                    in.nextLine();
                    System.out.println("Enter Subcategory Name: ");
                    String subcategoryName = in.nextLine();
                    System.out.println("Enter Description: ");
                    String description = in.nextLine();

                    try {
                        PreparedStatement stt = conn.prepareStatement(Query.Insert_subcategory_query);
                        stt.setInt(1, categoryId);
                        stt.setString(2, subcategoryName);
                        stt.setString(3, description);

                        stt.executeUpdate();
                        System.out.println("Subcategory added successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error adding subcategory: " + e.getMessage());
                    }

                    System.out.println("Do you want to add another subcategory? (yes/no): ");
                    String response = in.next();
                    if (!response.equalsIgnoreCase("yes")) {
                        addMore = false;
                    }
                }
            } else {
                System.out.println("Category not found. Please enter a valid category name.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching category ID: " + e.getMessage());
        }
    }
}
