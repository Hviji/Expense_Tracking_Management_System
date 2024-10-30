package src.view;
import src.controller.SubCategoryHelper;
import src.controller.SubCategoryHelper2;
import src.model.ObjectCreation;
import java.sql.Connection;
import java.util.Scanner;

public class SubCategoryManagement {
    Connection conn = ObjectCreation.getInInstanceofDatabaseConnection();
    Scanner in =ObjectCreation.getInInstanceofScanner();
    SubCategoryHelper ach = ObjectCreation.getInInstanceofSubCategoryHelper();
    SubCategoryHelper2 ach2 = ObjectCreation.getInInstanceofSubCategoryHelper2();
    public void SubCategoryManagement() {

        System.out.println("!+========================================+!");
        System.out.println("Subcategory Management:");
        while(true)
        {
            System.out.println("0. Exit");
            System.out.println("1. View Subcategories");
            System.out.println("2. Add Subcategory");
            System.out.println("3. Edit Subcategory");
            System.out.println("4. Delete Subcategory");
            System.out.println("\nEnter your Choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting Subcategory Management...");
                    return;
                case 1:
                    ach.viewSubcategories();
                    break;
                case 2:
                    ach.addSubcategory();
                    break;
                case 3:
                    ach2.editSubcategory();
                    break;
                case 4:
                    ach2.deleteSubcategory();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
