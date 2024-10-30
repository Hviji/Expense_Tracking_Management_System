package src.view;

import src.controller.*;
import src.model.ObjectCreation;
import src.model.User;
import java.sql.Connection;
import java.util.Scanner;

public class CustomerAnalysis {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    src.controller.UserdbHelper uh = new src.controller.UserdbHelper();
    CustomerHelper ch = new CustomerHelper();
    CustomeroverviewHelper ch2 = new CustomeroverviewHelper();
    display d = new display();
    display2 d2 = new display2();
    display3 d3 = new display3();
    src.controller.UserdatabaseHelper dbHelper = new src.controller.UserdatabaseHelper();
    src.controller.UserdbHelper dbhelper = new src.controller.UserdbHelper();

    public void Analysis(User u) {
        System.out.println("!+========================================+!");
        System.out.println("Analysis :");
        double expenseTotal = dbhelper.getTotalExpense(u.getId());
        double incomeTotal = dbhelper.getTotalIncome(u.getId());
        double totalBalance = incomeTotal - expenseTotal;
        System.out.println("!+----------------------------------------+!");
        System.out.println("Expense Overview  : " + expenseTotal);
        System.out.println("Income Overview   : " + incomeTotal);
        System.out.println("Total Balance     : " + totalBalance);
        System.out.println("!+----------------------------------------+!");
        while(true)
        {
            System.out.println("0. Exit\n");
            System.out.println("1. Expense Overview\n");
            System.out.println("2. Income OverView \n");
            System.out.println("3. Expense Flow\n");
            System.out.println("4.Income Flow\n ");
            System.out.println("5.Account Analysis \n ");
            System.out.println("\nEnter your Choice: ");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting Analysis Management ...");
                    return;
                case 1:
                    d3.displayExpenseOverview(u.getId());
                    break;
                case 2:
                    d3.displayIncomeOverview(u.getId());
                    break;
                case 3:
                    d3. displayExpenseFlow(u.getId());
                    break;
                case 4:
                    d3.displayIncomeFlow(u.getId());
                    break;
                case 5:
                    d3.displayAccountAnalysis(u.getId());
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}


