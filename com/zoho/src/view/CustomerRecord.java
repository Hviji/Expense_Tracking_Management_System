package src.view;

import src.controller.*;
import src.model.ObjectCreation;
import src.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerRecord {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    UserdbHelper uh = ObjectCreation.getInInstanceofUserdbHelper();
    CustomerHelper ch = ObjectCreation.getInInstanceofCustomerHelper();
    CustomeroverviewHelper coh = ObjectCreation.getInInstanceofCustomeroverviewHelper();
    display d = ObjectCreation.getInInstanceofdisplay();
    display2 d2 = ObjectCreation.getInInstanceofdisplay2();
    display3 d3 = ObjectCreation.getInInstanceofdisplay3();

    public void Record(User u) {
        try {
            System.out.println("!+========================================+!");
            System.out.println("History : \n");
            while (true) {
                System.out.println("0. Exit\n");
                System.out.println("1. Data Wise\n");
                System.out.print("\nEnter your Choice: ");
                int choice = in.nextInt();
                in.nextLine();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting Analysis Management ...");
                        return;
                    case 1:
                        d3.displayDateWiseAllRecords(u.getId());
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching records: " + e.getMessage());
        }
    }
}

