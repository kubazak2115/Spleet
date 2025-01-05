package Aplikacja;

import java.time.LocalDate;

public class Expense {
    private int id;
    private LocalDate datum;
    private double price;
    private String description;
    private User author;
    private Group expenseGroup;

    public Expense(int id, User author, LocalDate datum, double price, String description) {
        this.id = id;
        this.author = author;
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.expenseGroup = null; // Nie przypisujemy grupy, bo to jest wydatek użytkownika
    }

    public Expense(int id, Group group, LocalDate datum, double price, String description, User Author) {
        this.id = id;
        this.expenseGroup = group;
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.author = Author; // Nie przypisujemy użytkownika, bo to jest wydatek grupy
    }

    public int getId() {
        return id;
    }

    public Group getExpenseGroup() {
        return expenseGroup;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public double getPrice() {
        return price;
    }

    public User getAuthor() {
        return author;
    }

}
