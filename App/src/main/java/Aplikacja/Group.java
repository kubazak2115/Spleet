package Aplikacja;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<User> users;

    public Group(String name) {
        this.name = name;
        users = new ArrayList<User>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public String getName() {
        return name;
    }
}
