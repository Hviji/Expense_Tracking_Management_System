package src.view;

import src.model.ObjectCreation;
import java.util.Scanner;

public class Page {
    Scanner in = ObjectCreation.getInInstanceofScanner();
    public void page() {
        while (true) {
            System.out.println("\n\n***********Greetings, and welcome to BudgetBuddy !! **************\n\n ----------Your personal guide to smarter spending and effortless budgeting 💰️ ------\n");
            System.out.println("Choose your Choice : \n");
            System.out.println("0. Exit \n");
            System.out.println("1. Sign Up / ********Create Your Profile********\n ");
            System.out.println("2. Sign In / --------Continue to Dashboard -------- \n");
            System.out.println("\n Enter your Choice : \n ");
            int choice = in.nextInt();
            if (choice == 0) {
                System.out.println(" \nThank you for visit come again... \n ");
                ObjectCreation.closeDatabaseConnection();
                System.exit(0);
            } else if (choice == 1) {
                new src.view.SignUp().signup();
            } else if (choice == 2) {
                try {
                    new src.view.Authorize().authorize(new src.view.Login().login());
                } catch (NullPointerException n)
                {
                    System.out.println("User not found try again ");
                }
            } else
                System.out.println("Invalid Choice Please try again");
        }
    }
}
