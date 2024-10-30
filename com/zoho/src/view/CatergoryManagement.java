package src.view;

import src.controller.AdminHelper;
import src.controller.AdminHelper2;
import src.controller.AdminHelper3;
import src.controller.CategoryHelper;
import src.model.ObjectCreation;
import java.sql.Connection;
import java.util.Scanner;

public class CatergoryManagement {

    Connection conn= ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    AdminHelper ah = ObjectCreation.getInInstanceofAdminHelper();
    AdminHelper2 ah2 = ObjectCreation.getInInstanceofAdminHelper2();
    AdminHelper3 ah3 = ObjectCreation.getInInstanceofAdminHelper3();
    CategoryHelper ch = new CategoryHelper();

    public void categorymanagement() {

        System.out.println("!+========================================+!");
        System.out.println(" Category Management:\n");
        while(true)
        {
            System.out.println("\n 0. Exit \n");
            System.out.println("1. View Categories \n");
            System.out.println("2. Add Category \n");
            System.out.println("3.Sub Category \n");
            System.out.println("4. Edit Category \n");
            System.out.println("5. Delete Category \n");
            System.out.println("\nEnter your Choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting Category Management...");
                    return;
                case 1:
                    ch.viewCategories();
                    break;
                case 2:
                    ch.addCategory();
                    break;
                case 3:
                    new src.view.SubCategoryManagement().SubCategoryManagement();
                    break;
                case 4:
                    ch.editcategory();
                    break;
                case 5:
                    ch.deletecategory();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
