package Aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private int id;
    private String name;
    private String surname;
    private double balance;
    private ObservableList<Group> groups = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();


    public User(int id, String name, String surname, double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.balance = balance;

    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public ObservableList<Expense> getExpenses() {
        return expenses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getBalance() {
        return balance;
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }
}
