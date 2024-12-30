package Classes;

import java.util.List;

public class GUI {
    private Engine engine;

    public GUI(Engine engine) {
        this.engine = engine;
    }

    public void addTransaction(Transaction transaction) {
        engine.processTransaction(transaction);
        System.out.println("Transakcja dodana.");
    }

    public void generateReport(int userId, String startDate, String endDate) {
        List<Transaction> transactions = engine.fetchTransactions(userId, startDate, endDate);
        // Logika wyświetlania raportu
    }
}