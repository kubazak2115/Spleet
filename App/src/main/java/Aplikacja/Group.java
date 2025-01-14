package Aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Group {
    private String name;
    private ObservableList<User> members = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private int numberofmembers;
    private Map<User, Double> userBalances = new HashMap<>();
    private double spendings =0;

    public Group(String name) {
        this.name = name;
        this.numberofmembers = members.size();

    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        this.spendings-=expense.getPrice();
    }

    public ObservableList<Expense> getExpenses() {
        return expenses;
    }

    public double getSpendings(){
        return spendings;
    }

    public void addMember(User user) {
        if (!members.contains(user)) {
            members.add(user);
            user.addGroup(this);
            this.numberofmembers = members.size();
            userBalances.put(user, 0.0);

        }
    }

    public void removeMember(User user) {
        if (members.contains(user)) {
            members.remove(user);
            user.removeGroup(this);
            this.numberofmembers = members.size();
            userBalances.remove(user);

        }
    }

    public double getBalance(User user) {
        return userBalances.getOrDefault(user, 0.0);
    }

    public void updateBalance(User user, double newBalance) {
        userBalances.put(user, newBalance);
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return numberofmembers;
    }

    public Collection<User> getMembers() {
        return members;
    }

    public String toString(){
        return getName() ;
    }
}
