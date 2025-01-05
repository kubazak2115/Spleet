package Aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private int id;
    private String name;
    private String surname;
    private double balance;
    private double spendings =0;
    private double allspendings =0;
    private ObservableList<Group> groups = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private ObservableList<Balance> balances = FXCollections.observableArrayList();


    public User(int id, String name, String surname, double balance) {
        this.id = id;
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

    public ObservableList<Balance> getBalances() {
        return balances;
    }

    public Balance getBalanceForGroup(Group group) {
        return balances.stream()
                .filter(balance -> group.equals(balance.getGroup()))
                .findFirst()
                .orElse(null);
    }

    public Balance getOverallBalance() {
        return balances.stream()
                .filter(balance -> balance.getGroup() == null)
                .findFirst()
                .orElse(null);
    }

}
