package src.controller;

import src.model.ObjectCreation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserdatabaseHelper {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public int getAccountTypeId(String accountType) throws SQLException {
        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_account_query);
            st.setString(1, accountType);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Account type not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error in getAccountTypeId: " + e.getMessage(), e);
        }
    }

    public int getIncomeCategoryId(String incomeCategory) throws SQLException {
        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_incomecategory_query);
            st.setString(1, incomeCategory);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Income category not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error in getIncomeCategoryId: " + e.getMessage(), e);
        }
    }

    public int getCategoryId(String category) throws SQLException {
        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_categories_query2);
            st.setString(1, category);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Category not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error in getCategoryId: " + e.getMessage(), e);
        }
    }

    public int getSubCategoryId(String subcategory) throws SQLException {
        try {
            PreparedStatement st = conn.prepareStatement(Query.Select_subcategories_by_category_query);
            st.setString(1, subcategory);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Category not found.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error in getCategoryId: " + e.getMessage(), e);
        }
    }
}

