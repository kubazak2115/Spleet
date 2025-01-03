package Aplikacja;

public class User {
    private int id;
    private String name;
    private String surname;
    private double balance;

    public User(int id, String name, String surname, double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.balance = balance;

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

}
