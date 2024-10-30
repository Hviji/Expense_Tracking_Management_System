package src.model;

public class Expenses {
    private int expenseId;
    private int userId;
    private int categoryId;
    private double amount;
    private String description;
    private String expenseDate;

    public Expenses(int expenseId, int userId, int categoryId, double amount, String description, String expenseDate) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.expenseDate = expenseDate;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getExpenseDate() {
        return expenseDate;
    }
}


