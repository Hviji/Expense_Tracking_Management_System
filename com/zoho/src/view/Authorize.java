package src.view;

import src.model.User;
public class Authorize {
    public void authorize(User u)
    {
        switch (u.getRole())
        {
            case "Admin":
                new Admin().adminhelper(u);
                break;
            case "Customer":
                new Customer().Customer(u);
                break;
            case "Error":
                System.out.println(" Something Went Wrong!! Please Correct ! ");
        }
    }
}
