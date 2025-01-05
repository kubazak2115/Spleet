package Aplikacja;

public class Balance {
    private User user;
    private Group group;
    private double balance = 0;

    public Balance(User user, Group group, double money) {
        this.user = user;
        this.group = group;
        this.balance = balance;

    }

    public User getUser() {
        return user;
    }

    public Group getGroup() {
        return group;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

}
