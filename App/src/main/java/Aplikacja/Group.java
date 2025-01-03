package Aplikacja;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Group {
    private String name;
    private ObservableList<User> Members = FXCollections.observableArrayList();

    public Group(String name) {
        this.name = name;

    }

    public void addMember(User user) {
        if (!Members.contains(user)) {
            Members.add(user);
            user.addGroup(this);
        }
    }

    public void removeMember(User user) {
        if (Members.contains(user)) {
            Members.remove(user);
            user.removeGroup(this);
        }
    }


    public String getName() {
        return name;
    }

    public Collection<User> getMembers() {
        return Members;
    }
}
