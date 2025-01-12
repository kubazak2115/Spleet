package Aplikacja;

import java.time.LocalDate;

public class Expense {
    private LocalDate datum;
    private double price;
    private String description;
    private User author;
    private Group expenseGroup;
    private String category;


    public Expense( User author, Group group, LocalDate datum, double price, String description, String category) {

        this.author = author;
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.category = category;
        this.expenseGroup = group;
    }

    public Expense( Group group, LocalDate datum, double price, String description, User author, String category) {
        this.expenseGroup = group;
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.author = author;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
