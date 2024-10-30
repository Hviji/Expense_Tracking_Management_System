package src.view;

import src.controller.CustomerHelper;
import src.controller.CustomeroverviewHelper;
import src.controller.display;
import src.controller.display2;
import java.sql.Connection;
import java.util.Scanner;

public class Customeroverview {
    Connection conn = src.model.ObjectCreation.getInInstanceofDatabaseConnection();
    src.controller.UserdbHelper uh = new src.controller.UserdbHelper();
    Scanner in = src.model.ObjectCreation.getInInstanceofScanner();
    CustomerHelper ch = new CustomerHelper();
    CustomeroverviewHelper ch2 = new CustomeroverviewHelper();
    display d = new display();
    display2 d2 = new display2();
    src.controller.display3 d3 = new src.controller.display3();
    src.controller.UserdatabaseHelper dbHelper = new src.controller.UserdatabaseHelper();
    src.controller.UserdbHelper dbhelper = new src.controller.UserdbHelper();
    
    public void budgetOverview (src.model.User u) {
        System.out.println("!+========================================+!");
        System.out.println("Budget Overview Management :");
        while(true)
        {
            System.out.println("0. Exit\n");
            System.out.println("1. Display Total Budget & Spending\n");
            System.out.println("2. Display Category wise Budget \n");
            System.out.println("\nEnter your Choice: ");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting Budget Management ...");
                    return;
                case 1:
                    d2.displayTotalBudgetAndSpending(u.getId());
                    break;
                case 2:
                    d2.displayCategoryWiseBudget(u.getId());
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
