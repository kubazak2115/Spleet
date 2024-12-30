package Classes;

public class Category {
    private String name;
    private String description;

    // Konstruktor
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Gettery i settery
    public String getName() { return name; }
    public String getDescription() { return description; }
}
