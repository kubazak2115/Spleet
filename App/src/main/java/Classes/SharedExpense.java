package Classes;

public class SharedExpense extends Transaction {
    private int sharedWithUserId;

    public SharedExpense(int transactionId, int userId, String category, double amount, String description, String date, int sharedWithUserId) {
        super(transactionId, userId, category, amount, description, date);
        this.sharedWithUserId = sharedWithUserId;
    }

    public int getSharedWithUserId() { return sharedWithUserId; }
}
