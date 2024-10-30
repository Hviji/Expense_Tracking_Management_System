package src.view;

import src.controller.SignInHelper;;
import src.controller.ValidationImpl;
import src.model.ObjectCreation;
import src.model.User;
import java.sql.Connection;
import java.io.Console;
import java.util.Scanner;

public class SignUp {
    Console console = System.console();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    ValidationImpl validation = new ValidationImpl();

    public void signup() {
        String name;
        while (true) {
            in.nextLine();
            System.out.println("\nEnter your Name (Only alphabets and spaces allowed): ");
            name = in.nextLine();
            if (validation.validateName(name)) {
                break;
            } else {
                System.out.println("Invalid name! Name should contain only alphabets and spaces.");
            }
        }

        String email;
        while (true) {
            System.out.println("\nEnter your E-mail Id: ");
            email = in.next();
            if (validation.validateEmail(email)) {
                break;
            } else {
                System.out.println("Oops! The email address seems wrong. Please check and enter it again...");
            }
        }

        String number;
        while (true) {
            System.out.println("\nEnter your Mobile Number (with country code): ");
            number = in.next();
            if (validation.validateMobileNumber(number)) {
                break;
            } else {
                System.out.println("Mobile number not recognized. Please enter it again...");
            }
        }

        String username;
        while (true) {
            System.out.println("\nEnter your Username: ");
            username = in.next();
            if (validation.validateUsername(username)) {
                break;
            } else {
                System.out.println("Invalid username! Username must start with a letter, can contain letters and numbers, and cannot have spaces or be only digits.");
            }
        }

        String password;
        while(true){
            char[] passwordArray = console.readPassword("\nEnter your Password: \n");
            password= new String(passwordArray);
            if (validation.validatePassword(password)) {
                break;
            } else {
                System.out.println("Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, one digit, and one special character. Please try again.");
            }
        }

        User user = new User(name, email, number);
        SignInHelper signInHelper = new SignInHelper();
        signInHelper.signinhelper(user, username, password);
        System.out.println(" \n Your Account is Ready to Use !! \n");
    }
}

