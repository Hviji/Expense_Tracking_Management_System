package src.controller;

import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class CustomerHelper {

Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
Scanner in = ObjectCreation.getInInstanceofScanner();
src.controller.UserdatabaseHelper dbHelper = new src.controller.UserdatabaseHelper();
src.controller.display d = new src.controller.display();
    display2 dd = new display2();

        public void addIncome(int userId)
        {
            try {
                d.displayAccountTypes();
                in.nextLine();
                System.out.print("Select account type (as displayed above): ");
                String accountType = in.nextLine();
                int accountTypeId = dbHelper.getAccountTypeId(accountType);

                d.displayIncomeCategories();
                System.out.print("Select income category (as displayed above): ");
                String incomeCategory = in.nextLine();
                int incomeCategoryId = dbHelper.getIncomeCategoryId(incomeCategory);

                System.out.print("Enter income amount (in rupee): ");
                double incomeAmount = in.nextDouble();
                in.nextLine();

                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = in.nextLine();

                System.out.print("Enter description: ");
                String description = in.nextLine();
                PreparedStatement st = conn.prepareStatement(src.controller.Query.Insert_income_query);
                st.setInt(1, userId);
                st.setInt(2, accountTypeId);
                st.setInt(3, incomeCategoryId);
                st.setDouble(4, incomeAmount);
                st.setString(5, date);
                st.setString(6, description);
                st.executeUpdate();


                System.out.println("Income added successfully!");
            } catch (SQLException e) {
                System.out.println("Error adding income: " + e.getMessage());
            }
        }

        public void setBudgetLimit(int userId) {
            try {
                d.displayCategories();
                in.nextLine();
                System.out.print("Select category (as displayed above): ");
                String category = in.nextLine();
                int CategoryId = dbHelper.getCategoryId(category);
                System.out.print("Do you want to set a budget for a subcategory? (yes/no): ");
                String response = in.nextLine();
                int SubCategoryId = 0;
                if (response.equalsIgnoreCase("yes")) {
                    if(dd.displaySubCategories(CategoryId) == 1){
                        System.out.print("Select subcategory (as displayed above): ");
                        String subcategory = in.nextLine();
                        SubCategoryId = dbHelper. getSubCategoryId(subcategory);
                    }
                }
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = in.nextLine();
                System.out.print("Enter budget limit amount (in rupees): ");
                double budgetLimit = in.nextDouble();
                in.nextLine();
                System.out.print("Enter description: ");
                String description = in.nextLine();
                PreparedStatement st = conn.prepareStatement(src.controller.Query.Select_budget_query);
                st.setInt(1, userId);
                st.setInt(2, CategoryId);
                if (SubCategoryId != 0) {
                    st.setInt(3, SubCategoryId);
                } else {
                    st.setNull(3, java.sql.Types.INTEGER);
                }
                st.setString(4, date);
                st.setDouble(5, budgetLimit);
                st.setString(6, description);
                st.executeUpdate();
                System.out.println("Budget limit set successfully!");
            } catch (SQLException e) {
                System.out.println("Error setting budget limit: " + e.getMessage());
            }
        }

}

