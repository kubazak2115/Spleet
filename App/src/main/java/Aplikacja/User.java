package Aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private String name;
    private String surname;
    private double balance;
    private double spendings =0;
    private double allspendings =0;
    private ObservableList<Group> groups = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();

    public User(/*int id,*/ String name, String surname, double balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);

    }

    public void addSpending(double money){
        this.spendings-=money;
    }
    public double getSpending(){
        return spendings;
    }
    public void addAllSpending(double money){
        this.allspendings-=money;
    }
    public double getAllSpending(){
        return allspendings;
    }

    public ObservableList<Expense> getExpenses() {
        return expenses;
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
    public void setBalance(double update) {
        this.balance+=update;
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


    public String toString(){

        return getName() + " " + getSurname();
    }


}
