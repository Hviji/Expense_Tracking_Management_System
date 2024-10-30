package src.controller;

import src.model.ObjectCreation;
import src.model.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHelper {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();

    public User LoginHelper(String username){
    Console console = System.console();
    try  {

        PreparedStatement ps = conn.prepareStatement(src.controller.Query.Select_logincredentialtrim_query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            int id = rs.getInt("user_id");
            String storedPassword = rs.getString("password").trim();
            String inputPassword;

            while (true) {
                console = System.console();
                if (console == null) {
                    System.out.println("No console available. Please run in a terminal.");
                    return null;
                }

                char[] passwordArray = console.readPassword("\nEnter your password: \n");
                inputPassword = new String(passwordArray).trim();
                if (!storedPassword.equals(inputPassword)) {
                    System.out.println("Incorrect password. Please try again.");
                }
                else {

                    String role = (username.equals("Admin")) ? "Admin" : "Customer";


                    ps = conn.prepareStatement(src.controller.Query.Select_users_query );
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    if(rs.next())
                    {
                        User user = new User(rs.getString("name"),rs.getString("email_id"),rs.getString("mobile_no"),id,role);
                        System.out.println(" \nLogin Successfully !  \n");
                        return user;
                    }
                }
            }
        }
        else
        {
            System.out.println("Username not Found !\n");
        }
    } catch (SQLException e) {
        System.out.println("SignIn failed: " + e.getMessage());
    }
		return null;
    }
}

