package src.view;

import src.controller.LoginHelper;
import src.model.ObjectCreation;
import src.model.User;
import java.io.Console;
import java.util.Scanner;

public class Login {
    Console console = System.console();
    Scanner in = ObjectCreation.getInInstanceofScanner();
    public User login()
    {
        in.nextLine();
        System.out.println(" ---Login:---");
        System.out.println("\n Enter your Username: ");
        String username = in.nextLine();
        return new LoginHelper().LoginHelper(username);
    }
    }

