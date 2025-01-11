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

    // Konstruktor dla wydatku użytkownika z kategorią
    public Expense(/*int id,*/ User author, LocalDate datum, double price, String description, String category) {
//        this.id = id;
        this.author = author;
//        this.authorName = author.getName()+" "+author.getSurname();
        this.datum = datum;
        this.price = price;
        this.description = description;
        this.category = category;
        this.expenseGroup = null; // Nie przypisujemy grupy, bo to jest wydatek użytkownika
//        this.groupName = null;
    }

    // Konstruktor dla wydatku użytkownika bez kategorii
    public Expense(/*int id,*/ User author, LocalDate datum, double price, String description) {
        this(/*id,*/ author, datum, price, description, null); // Wywołanie głównego konstruktora z kategorią ustawioną na null
    }

    // Konstruktor dla wydatku grupy z kategorią
    public Expense(/*int id,*/ Group group, LocalDate datum, double price, String description, User author, String category) {
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

    // Konstruktor dla wydatku grupy bez kategorii
    public Expense(/*int id,*/ Group group, LocalDate datum, double price, String description, User author) {
        this(/*id,*/ group, datum, price, description, author, null); // Wywołanie głównego konstruktora z kategorią ustawioną na null
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
