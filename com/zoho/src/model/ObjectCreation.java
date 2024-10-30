package src.model;

import src.controller.*;
import java.sql.Connection;
import java.util.Scanner;

public class ObjectCreation {
        private static Scanner in = null;
        private static src.controller.DBConnection db =null;
        private static AdminHelper ah =null;
        private static AdminHelper2 ah2 =null;
        private static AdminHelper3 ah3 =null;
        private static CategoryHelper ch =null;
        private static src.controller.SubCategoryHelper ach=null;
        private static src.controller.SubCategoryHelper2 ach2 =null;
        private static CustomeroverviewHelper coh=null;
        private static CustomerHelper chh=null;
        private static UserdbHelper uh = null;
        private static display3 d3 = null;
        private static display d = null;
       private static display2 d2 = null;

    public static Scanner getInInstanceofScanner()
        {
            if (in == null)
                in = new Scanner(System.in);
            return in;
        }

        public static Connection getInInstanceofDatabaseConnection()
        {
            if (db == null)
                db = src.controller.DBConnection.Connection();
            return db.connect();
        }

        public static void closeDatabaseConnection(){
        if(db==null)
            return;
        db.closeConnect();
        }

    public static AdminHelper getInInstanceofAdminHelper()
    {
        if (ah == null)
            ah = new AdminHelper();
        return ah;
    }

    public static AdminHelper2 getInInstanceofAdminHelper2()
    {
        if (ah2 == null)
            ah2 = new AdminHelper2();
        return ah2;
    }

    public static AdminHelper3 getInInstanceofAdminHelper3()
    {
        if (ah3 == null)
            ah3 = new AdminHelper3();
        return ah3;
    }

    public static src.controller.SubCategoryHelper getInInstanceofSubCategoryHelper()
    {
        if (ach== null)
            ach = new src.controller.SubCategoryHelper();
        return ach;
    }

    public static src.controller.SubCategoryHelper2 getInInstanceofSubCategoryHelper2()
    {
        if (ach2== null)
            ach2 = new src.controller.SubCategoryHelper2();
        return ach2;
    }

    public static UserdbHelper getInInstanceofUserdbHelper()
    {
        if (uh== null)
            uh = new src.controller.UserdbHelper();
        return uh;
    }

    public static  CustomerHelper getInInstanceofCustomerHelper ()
    {
        if (chh == null)
            chh = new CustomerHelper();
        return chh;
    }

    public static  CustomeroverviewHelper getInInstanceofCustomeroverviewHelper ()
    {
        if (coh == null)
            coh= new CustomeroverviewHelper();
        return coh;
    }

    public static display getInInstanceofdisplay()
    {
        if (d == null)
            d= new display();
        return d;
    }

    public static display2 getInInstanceofdisplay2()
    {
        if (d2 == null)
            d2= new display2();
        return d2;
    }

    public static display3 getInInstanceofdisplay3()
    {
        if (d3 == null)
            d3= new display3();
          return d3;
    }
}





