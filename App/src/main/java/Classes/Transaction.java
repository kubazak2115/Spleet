package Classes;

public class Transaction {
    private int transactionId;
    private int userId;
    private String category;
    private double amount;
    private String description;
    private String date;

    // Konstruktor
    public Transaction(int transactionId, int userId, String category, double amount, String description, String date) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // Gettery i settery
    public int getTransactionId() { return transactionId; }
    public int getUserId() { return userId; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getDate() { return date; }

}