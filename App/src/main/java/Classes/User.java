package Classes;

public class User {
    private int userId;
    private String username;
    private String role; // "User1", "User2", "Admin"

    // Konstruktor
    public User(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // Gettery i settery
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}