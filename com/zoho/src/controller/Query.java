package src.controller;

public class Query{

    public static final String select_enroll_query= "INSERT INTO users (name, email_id, mobile_no) VALUES (?,?,?) returning id";

    public static final String Select_logincredential_query = "INSERT INTO logincredential (username, password, user_id) VALUES (?,?,?)";

    public static final String Delete_status_query = "update logincredential set status = 'inactive' where user_id = ?;";

    public static final String Select_logincredentialtrim_query = "SELECT user_id, password FROM logincredential WHERE TRIM(username) = ? and status = 'active';";

    public static final String Select_users_query = "SELECT * FROM users WHERE id = ?;";

    public static final String Select_user_display_query = "SELECT id, name, email_id, mobile_no FROM users where name != 'Admin' ;";

    public static final String Select_useroverview_query= "SELECT u.id AS user_id, u.name AS user_name, COUNT(e.id) AS expense_count FROM users u LEFT JOIN expenses e ON u.id = e.user_id WHERE u.name != 'Admin' GROUP BY u.id, u.name;";

    public static final String Select_report_query = "SELECT c.category_name, SUM(e.amount) AS total_expenses FROM expenses e JOIN categories c ON      e.category_id = c.id GROUP BY c.category_name;";

    public static final String  Select_viewcategory_query = "SELECT id, category_name FROM categories;";

    public static final String Insert_addcategory_query = "INSERT INTO categories (category_name) VALUES (?);";

    public static final String  Select_subviewcategory_query =  "SELECT s.id AS subcategory_id, c.category_name, s.subcategory_name AS subcategory_name, s.description FROM subcategories s JOIN categories c ON s.category_id = c.id;\n";

    public static final String Select_subaddcategoryID_query = "SELECT id FROM categories WHERE category_name =?;";

    public static final String Insert_subcategory_query = "INSERT INTO subcategories (category_id,subcategory_name, description ) VALUES (?, ?, ?);";

    public static final String Select_editcategoryID_query = "SELECT id FROM categories WHERE category_name =?;";

    public static final String Select_editsubaddcategoryID_query="SELECT id FROM categories WHERE category_name =?;";

    public static final String Select_editsubcategoryName_query ="SELECT id FROM subcategories WHERE category_id = ? AND subcategory_name = ?;";

    public static final String Update_subcategory_query ="UPDATE subcategories SET subcategory_name = ?, description = ? WHERE id =?;";

    public static final String Delete_subcategory_query ="DELETE FROM subcategories WHERE subcategory_name = ?;";

    public static final String Update_category_query  =" UPDATE categories SET category_name = ? WHERE id = ?;";

    public static final String Delete_category_query ="DELETE FROM categories WHERE category_name = ?;";

    public static final String Select_budgetoverview_query = "SELECT user_id, SUM(limit_amount) AS total_budget FROM budget GROUP BY user_id;";

    public static final String Select_accountoverview_query = "SELECT account_type FROM account;";

    public static final String Select_exportdata_query = "SELECT s.id, s.subcategory_name, s.description, c.category_name " +"FROM subcategories s " +"JOIN categories c ON s.category_id = c.id";

    public static final String Insert_incomecategory_query = "INSERT INTO income_categories (incomecategory_name) VALUES (?);";

    public static final String Select_account_types_query ="SELECT DISTINCT account_type FROM account ;";

    public static final String Select_income_categories_query ="SELECT DISTINCT incomecategory_name FROM income_categories;";

    public static final String Insert_income_query =  "INSERT INTO income (user_id, accounttype_id, incomecategory_id, amount, income_date, description) " +"VALUES (?, ?, ?, ?, cast(? as date), ?);";

    public static final String  Select_account_query ="SELECT id FROM account WHERE account_type = ?;";

    public static final String  Select_incomecategory_query = "SELECT id FROM income_categories WHERE lower(incomecategory_name) = lower(?);";

    public static final String Select_budget_query = "INSERT INTO budget (user_id, category_id, subcategory_id,  budget_date,limit_amount,description) " +"VALUES (?, ?, ?,cast(? as date), ?,?);";

    public static final String Select_categories_query = "SELECT id, category_name FROM categories;";

    public static final String Select_categories_query2 = "SELECT id FROM categories where  category_name = ?;";

    public static final String Select_subcategories_by_category_query = "SELECT id FROM subcategories WHERE subcategory_name = ?;";
    public static final String Select_subcategories_by_category_query2 = "SELECT subcategory_name FROM subcategories WHERE category_id = ?;";

    public static final String Insert_expense_query = "INSERT INTO expenses (user_id, account_type, category_id, subcategory_id, amount, expense_date, description) VALUES (?, ?, ?, ?, ?, cast(? as date), ?);";

    public static final String Select_total_income_query = "SELECT SUM(amount) AS total_income FROM income WHERE user_id = ?;";

    public static final String Select_total_expense_query = "SELECT SUM(amount) AS total_expense FROM expenses WHERE user_id = ?";

    public static final String Update_deduct_balance_query = "UPDATE transfer SET balance = balance - ? WHERE user_id = ? AND account_type_id = ?;";

    public static final String Select_totalspendbudget_query = "SELECT COALESCE(SUM(b.limit_amount), 0) AS total_budget, " +
            "COALESCE(SUM(b.current_spent), 0) AS total_spent " +
            "FROM budget b WHERE b.user_id = ?";

    public static final String Select_categorywise_query = "SELECT c.category_name, b.limit_amount, b.current_spent, " + "(b.limit_amount - b.current_spent) AS remaining_amount " +"FROM budget b JOIN categories c ON b.category_id = c.id " + "WHERE b.user_id = ?";


    static final String Update_add_balance_query ="UPDATE transfer SET balance = balance + ? WHERE user_id = ? AND account_type_id = ?;";

    public static final String Select_trasferaccount_query = "SELECT amount FROM income WHERE user_id = ? AND accounttype_id = ?";

    public static final String Update_accountset_query = "UPDATE income  SET amount = amount + ? WHERE user_id = ? AND accounttype_id = ?";

    public static final String Insert_transfer_query = "INSERT INTO transfer (user_id, from_account_type_id, to_account_type_id, amount) VALUES (?, ?, ?, ?)";

    public static final String Select_expenseoverviewanalysis_query = "SELECT c.category_name, COALESCE(SUM(e.amount), 0) AS total_expenses " +"FROM expenses e JOIN categories c ON e.category_id = c.id " +"WHERE e.user_id = ? GROUP BY c.category_name";

    public static final String Select_incomeoverviewanalysis_query=  "SELECT ic.incomecategory_name, COALESCE(SUM(i.amount), 0) AS total_income " +"FROM income i JOIN income_categories ic ON i.incomecategory_id = ic.id " +"WHERE i.user_id = ? AND i.amount > 0 " + "GROUP BY ic.incomecategory_name";

    public static final String Select_incomeflowanalysis_query= "SELECT income_date, COALESCE(SUM(amount), 0) AS total_income " +"FROM income WHERE user_id = ? GROUP BY income_date ORDER BY income_date";

    public static final String Select_accountanalysis_query = " SELECT a.account_type, COALESCE(SUM(i.amount), 0) AS total_income FROM account a LEFT JOIN income i ON a.id = i.accounttype_id AND i.user_id = ? GROUP BY a.account_type;";

    public static final String Select_datawisehistory_query = "SELECT e.expense_date, c.category_name, s.subcategory_name, e.amount, e.description FROM expenses e LEFT JOIN categories c ON e.category_id = c.id  LEFT JOIN subcategories s ON e.subcategory_id = s.id WHERE e.user_id = ?;";

    public static final String UPDATE_spendamount_query= "UPDATE budget SET current_spent = current_spent + ? WHERE category_id = ? AND user_id = ?";

    public static final String Select_userprofile_query = "SELECT name, email_id, mobile_no FROM users WHERE id = ?";




}








