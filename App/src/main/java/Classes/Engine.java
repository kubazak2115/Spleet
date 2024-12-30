package Classes;

import java.util.List;

public class Engine {
    private Database db;

    public Engine(Database db) {
        this.db = db;
    }

    public void processTransaction(Transaction transaction) {
        db.saveTransaction(transaction);
    }

    public List<Transaction> fetchTransactions(int userId, String startDate, String endDate) {
        return db.getTransactions(userId, startDate, endDate);
    }
}
