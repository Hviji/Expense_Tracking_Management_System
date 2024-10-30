package src.view;

import src.controller.AdminHelper2;
import src.controller.AdminHelper3;
import src.model.ObjectCreation;
import src.model.User;
import java.sql.Connection;
import java.util.Scanner;

public class Admin {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    src.controller.AdminHelper ah=ObjectCreation.getInInstanceofAdminHelper();
    AdminHelper2 ah2=ObjectCreation.getInInstanceofAdminHelper2();
    AdminHelper3 ah3=ObjectCreation.getInInstanceofAdminHelper3();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    public void adminhelper(User u)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome Admin !!  \n");
        while(true)
        {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("\n0. Exit \n");
            System.out.println("1. User Register Count \n ");
            System.out.println("2. User Activity OverView \n");
            System.out.println("3. Expense Reports \n");
            System.out.println("4. Category Management \n");
            System.out.println("5. Budget Overview \n" );
            System.out.println("6. Account Types Overview \n");
            System.out.println("7. Data Export \n");
            System.out.println("8. Income category\n");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting Admin Menu...");
                    return;
                case 1:
                    ah.displayUserRegisterList();
                    break;
                case 2:
                    ah.userActivityOverview();
                    break;
                case 3:
                    ah2.expenseReports();
                    break;
                case 4:
                    new CatergoryManagement().categorymanagement();
                    break;
                case 5:
                    ah3.budgetOverview();
                    break;
                case 6:
                    ah3.AccountTypesOverview();
                    break;
                case 7:
                    ah2.dataExport();
                    break;
                case 8:
                    ah.incomeCategory();
                    break;
                default :
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
