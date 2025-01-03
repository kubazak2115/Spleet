package Aplikacja;

public class Expense {
    private int userid;
    private String datum;
    private double price;
    private String description;

    public Expense(int userid, String datum, double price, String description) {
        this.userid = userid;
        this.datum = datum;
        this.price = price;
        this.description = description;

    }

    public int getId() {
        return userid;
    }
    public String getDescription() {
        return description;
    }
    public String getDatum() {
        return datum;
    }
    public double getPrice() {
        return price;
    }
}
