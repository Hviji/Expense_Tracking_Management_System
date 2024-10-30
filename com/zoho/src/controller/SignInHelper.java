package src.controller;

import src.model.ObjectCreation;
import src.model.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
public class SignInHelper {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    public void signinhelper(User user, String username, String password){
        try  {
            PreparedStatement st = conn.prepareStatement(Query.select_enroll_query);
            st.setString(1,user.getName());
            st.setString(2,user.getEmail());
            st.setString(3,user.getMobileNumber());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");

                PreparedStatement stt = conn.prepareStatement(Query.Select_logincredential_query);
                stt.setString(1,username);
                stt.setString(2,password);
                stt.setInt(3,userId);
                stt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("enroll failed: " + e.getMessage());
        }
    }
}


