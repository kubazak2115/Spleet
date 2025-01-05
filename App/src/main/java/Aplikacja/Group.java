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
//        if (expense.getPrice() > 0 && members.contains(expense.getAuthor())) {
//            // Split expense equally among members
//            double splitAmount = expense.getPrice() / members.size();
//            for (User member : members) {
//                if (member.equals(expense.getAuthor())) {
//                    // Author's balance decreases by the full amount
//                    updateBalance(member, getBalance(member) - expense.getPrice());
//                } else {
//                    // Other members' balances increase by their share
//                    updateBalance(member, getBalance(member) + splitAmount);
//                }
//            }
//
//        }

        this.spendings-=expense.getPrice();
    }

//    public void removeExpense(Expense expense) {
//        expenses.remove(expense);
//        if (expense.getPrice() > 0 && members.contains(expense.getAuthor())) {
//            // Revert balance adjustments for the removed expense
//            double splitAmount = expense.getPrice() / members.size();
//            for (User member : members) {
//                if (member.equals(expense.getAuthor())) {
//                    // Author's balance increases by the full amount
//                    updateBalance(member, getBalance(member) + expense.getPrice());
//                } else {
//                    // Other members' balances decrease by their share
//                    updateBalance(member, getBalance(member) - splitAmount);
//                }
//            }
//        }
//    }

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
            userBalances.put(user, 0.0); // Initialize balance for the new member

        }
    }

    public void removeMember(User user) {
        if (members.contains(user)) {
            members.remove(user);
            user.removeGroup(this);
            this.numberofmembers = members.size();
            userBalances.remove(user); // Remove the balance for this member

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

    public void updateBalances(Expense expense) {
        ObservableList<User> members = (ObservableList<User>) getMembers();
        double splitAmount = expense.getPrice() / members.size();

        for (User member : members) {
            Balance groupBalance = member.getBalanceForGroup(this);
            if (groupBalance == null) {
                groupBalance = new Balance(member, this, 0.0);
                member.getBalances().add(groupBalance);
            }

            if (expense.getAuthor() != null && expense.getAuthor().equals(member)) {
                groupBalance.updateBalance(expense.getPrice() - splitAmount);
            } else {
                groupBalance.updateBalance(-splitAmount);
            }
        }
    }
}
