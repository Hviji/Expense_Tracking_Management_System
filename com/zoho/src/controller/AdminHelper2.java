package src.controller;

import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AdminHelper2 {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();

    public void expenseReports() {

        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_report_query );
            ResultSet rs = st.executeQuery();

            System.out.println("+=====================================================================================+");
            System.out.println("|                                                       Expense Reports:                                        |");
            System.out.println("+=====================================================================================+");
            System.out.printf(" | %-35s | %-15s |%n", "Category", "Total Expenses");
            System.out.println("+=====================================================================================+");

            while (rs.next()) {
                String category = rs.getString("category_name");
                double totalExpenses = rs.getDouble("total_expenses");
                System.out.printf("|%-35s | %-15s |%n", category, totalExpenses);

            }
            System.out.println("\n+=====================================================================================+");
        } catch (SQLException e) {
            System.out.println("Error retrieving expense reports: " + e.getMessage());
        }
    }

    public void dataExport() {
        try  {
            PreparedStatement st = conn.prepareStatement(src.controller.Query. Select_exportdata_query );
            ResultSet rs = st.executeQuery();
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter("categories_export.csv",true))){
                fileWriter.append("Subcategory ID, Subcategory Name, Category Name, Description\n");
                while (rs.next()) {
                    int subcategoryId = rs.getInt("id");
                    String subcategoryName = rs.getString("subcategory_name");
                    String categoryName = rs.getString("category_name");
                    String description = rs.getString("description");

                    fileWriter.append(String.valueOf(subcategoryId))
                            .append(",")
                            .append(subcategoryName)
                            .append(",")
                            .append(categoryName)
                            .append(",")
                            .append(description != null ? description : "")
                            .append("\n");
                }
            }catch(IOException e)
            {
                System.out.println("Error fetching data: " + e.getMessage());
            }
            System.out.println("Data export completed successfully. File saved as 'categories_export.txt'.");
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


}
