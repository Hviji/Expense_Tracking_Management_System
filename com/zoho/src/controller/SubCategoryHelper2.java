package src.controller;



import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SubCategoryHelper2 {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = ObjectCreation.getInInstanceofScanner();

    public void editSubcategory() {
        in.nextLine();
        System.out.println("Enter Category Name: ");
        String categoryName = in.nextLine();

        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_editsubaddcategoryID_query);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int categoryId = rs.getInt("id");

                System.out.println("Enter Subcategory Name to Edit: ");
                String subcategoryName = in.nextLine();
                PreparedStatement stt = conn.prepareStatement(Query.Select_editsubcategoryName_query);
                stt.setInt(1, categoryId);
                stt.setString(2, subcategoryName);
                ResultSet rsSub = stt.executeQuery();

                if (rsSub.next()) {
                    int subcategoryId = rsSub.getInt("id");

                    System.out.println("Enter New Subcategory Name: ");

                    String newSubcategoryName = in.nextLine();
                    System.out.println("Enter New Description: ");
                    String newDescription = in.nextLine();

                    PreparedStatement stUpdate = conn.prepareStatement(Query.Update_subcategory_query);
                    stUpdate.setString(1, newSubcategoryName);
                    stUpdate.setString(2, newDescription);
                    stUpdate.setInt(3, subcategoryId);


                    int rowsAffected = stUpdate.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Subcategory updated successfully!");
                    } else {
                        System.out.println("Failed to update subcategory.");
                    }
                } else {
                    System.out.println("Subcategory not found. Please enter a valid subcategory name.");
                }
            } else {
                System.out.println("Category not found. Please enter a valid category name.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating subcategory: " + e.getMessage());
        }
    }


    public void deleteSubcategory() {
        in.nextLine();
        System.out.print("Enter Subcategory Name to Delete: ");
        String subcategoryName = in.nextLine();
        try {
            PreparedStatement st = conn.prepareStatement(Query.Delete_subcategory_query);
            st.setString(1, subcategoryName);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Subcategory deleted successfully!");
            } else {
                System.out.println("Subcategory not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting subcategory: " + e.getMessage());
        }


    }
}
