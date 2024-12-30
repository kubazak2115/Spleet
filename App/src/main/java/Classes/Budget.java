package Classes;

public class Budget {
    private int userId;
    private double monthlyLimit;

    // Konstruktor
    public Budget(int userId, double monthlyLimit) {
        this.userId = userId;
        this.monthlyLimit = monthlyLimit;
    }

    // Gettery i settery
    public int getUserId() { return userId; }
    public double getMonthlyLimit() { return monthlyLimit; }
}
