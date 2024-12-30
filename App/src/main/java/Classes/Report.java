package Classes;

import java.util.List;

public class Report {
    private int userId;
    private String startDate;
    private String endDate;
    private List<Transaction> transactions;

    // Konstruktor
    public Report(int userId, String startDate, String endDate, List<Transaction> transactions) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactions = transactions;
    }

    public void generateReport() {
        // Logika generowania raportu
    }
}