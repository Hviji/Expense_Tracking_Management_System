package src.view;
import src.controller.CustomerHelper;
import src.controller.CustomeroverviewHelper;
import src.controller.display;
import src.controller.display2;
import src.model.ObjectCreation;
import src.model.User;
import java.sql.Connection;
import java.util.Scanner;

public class Customer {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in =ObjectCreation.getInInstanceofScanner();
    src.controller.UserdbHelper uh = new src.controller.UserdbHelper();
    CustomerHelper ch = new CustomerHelper();
    CustomeroverviewHelper ch2 = new CustomeroverviewHelper();
    display d = new display();
    display2 d2 = new display2();
    src.controller.display3 d3 = new src.controller.display3();

    public void Customer(User u)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("--------------------Welcome to  Customer Dashboard !! --------------------  \n");
        while(true)
        {
            System.out.println("\n Choose your Choice : \n");
            System.out.println("\n 0. Exit\n");
            System.out.println("1. profile\n");
            System.out.println("2. Add Income\n");
            System.out.println("3. Set Budget\n");
            System.out.println("4.Add Expense \n");
            System.out.println("5.Budgets Overview\n");
            System.out.println("6.Transfer Account \n");
            System.out.println("7. Analyze \n");
            System.out.println("8. Record History\n");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting Customer Dashboard.....");
                    return;
                case 1:
                    d3.displayUserProfile(u.getId());
                    break;
                case 2:
                    ch.addIncome(u.getId());
                    break;
                case 3:
                    ch.setBudgetLimit(u.getId());
                    break;
                case 4 :
                    ch2.addExpense(u.getId());
                    break;
                case 5 :
                    new Customeroverview().budgetOverview(u);
                    break;
                case 6 :
                    ch2.transferBetweenAccounts(u.getId());
                    break;
                case 7:
                    new src.view.CustomerAnalysis().Analysis(u);
                    break;
                case 8:
                    new src.view.CustomerRecord().Record(u);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
