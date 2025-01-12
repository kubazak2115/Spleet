package Aplikacja;

import java.time.LocalDate;

public class Expense {
//    private int id;
    private LocalDate datum;
    private double price;
    private String description;
    private User author;
//    private String authorName;
    private Group expenseGroup;
//    private String groupName;
    private String category; // Kategoria wydatku


    public Expense( User author, Group group, LocalDate datum, double price, String description, String category) {
//
        this.author = author;
//        this.authorName = author.getName()+" "+author.getSurname();
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.category = category;
        this.expenseGroup = group;
    }

    public Expense( Group group, LocalDate datum, double price, String description, User author, String category) {
//        this.id = id;
        this.expenseGroup = group;
//        this.groupName = group.getName();
//        this.authorName = author.getName()+" "+author.getSurname();
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.author = author;
        this.category = category;
    }


    // Gettery i settery
//    public int getId() {
//        return id;
//    }

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

//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }

    public void setCategory(String category) {
        this.category = category;
    }
}
