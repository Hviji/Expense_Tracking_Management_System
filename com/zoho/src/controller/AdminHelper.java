package src.controller;


import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminHelper {

        Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
        Scanner in = ObjectCreation.getInInstanceofScanner();

        public void displayUserRegisterList() {
            try  {
                PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_user_display_query );
                ResultSet rs = st.executeQuery();
                System.out.println("+=========================================================================================================================+");
                System.out.println("|                                                        User Register List :                                          |");
                System.out.println("+=========================================================================================================================+");
                System.out.printf("| %-15s | %-40s | %-35s | %-15s |%n", "User ID", "Name", "Email_ID", "Mobile_No");
                System.out.println("+=========================================================================================================================+");


                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String userName = rs.getString("name");
                    String emailId = rs.getString("email_id");
                    String mobileNo = rs.getString("mobile_no");
                    System.out.printf("| %-15d | %-40s | %-35s | %-15s |%n", userId, userName, emailId, mobileNo);
                }	System.out.println("+=========================================================================================================================+");

            } catch (SQLException e) {
                System.out.println("Error retrieving user list: " + e.getMessage());
            }
        }

        public void userActivityOverview() {

            try  {
                PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_useroverview_query);
                ResultSet rs = st.executeQuery();

                System.out.println("+=========================================================================================================================+");
                System.out.println("|                                                       User Activity Overview:                                        |");
                System.out.println("+=========================================================================================================================+");
                System.out.printf("| %-40s | %-35s | %-15s |%n", "User ID", "User Name", "Expenses Added");
                System.out.println("+=========================================================================================================================+");

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String userName = rs.getString("user_name");
                    int expenseCount = rs.getInt("expense_count");
                    System.out.printf("| %-40s | %-35s | %-15s |%n", userId, userName, expenseCount);
                }

                System.out.println("+=========================================================================================================================+");
            } catch (SQLException e) {
                System.out.println("Error retrieving user activity overview: " + e.getMessage());
            }
        }

        public void incomeCategory() {
            System.out.println("How many income categories would you like to add?");
            int numberOfCategories = in.nextInt();

            for (int i = 0; i < numberOfCategories; i++) {
                System.out.println("Enter Income Category Name " + (i + 1) + ": ");
                String incomecategoryName = in.next();

                try {
                    PreparedStatement st = conn.prepareStatement(src.controller.Query.Insert_incomecategory_query);
                    st.setString(1, incomecategoryName);
                    st.executeUpdate();
                    System.out.println("Income Category " + (i + 1) + " added successfully!");
                } catch (SQLException e) {
                    System.out.println("Error adding category " + (i + 1) + ": " + e.getMessage());
                }
            }
        }

    }



