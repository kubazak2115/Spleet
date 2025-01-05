package com.example.demo2;

import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddGroupController {

    @FXML
    public Button ZatwierdzButtonG;
    public Button OdrzucButtonG;
    public ComboBox WybierzCzlonkowComboBox;
    public TextField nazwaTextfield;
    @FXML
    public ListView ListaCzlonkow;
    private MainController MainController;

    public void setMainController(MainController newMainController) {
        this.MainController = newMainController;
        updateListView();
        System.out.println(newMainController);

    }
    @FXML
    public void initialize() {
        updateListView();
        ListaCzlonkow.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ListaCzlonkow.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null); // Gdy element jest pusty
                } else {
                    setText(user.getName() + " " + user.getSurname()); // Wyświetlanie imienia i nazwiska
                }
            }
        });

//        System.out.println(MainController);

    }

    @FXML
    public void onZatwierdzButtonG(ActionEvent actionEvent) throws IOException {

        String name = nazwaTextfield.getText();

        if (name.isEmpty()) {
            System.out.println("Nazwa grupy nie może być pusta.");
            return;
        }

        try {
            // Pobranie wybranych użytkowników z ListView

            // Dodanie grupy do kontrolera głównego

            ListaCzlonkow.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            System.out.println("multiple zaincicjowane");
            ObservableList<User> selectedUsers = FXCollections.observableArrayList();
            selectedUsers.addAll(ListaCzlonkow.getSelectionModel().getSelectedItems());
            System.out.println(selectedUsers + "powinno byc 3");


//            User selectedUser= (User) ListaCzlonkow.getSelectionModel().getSelectedItem();
            if(selectedUsers != null) {
                MainController.addGroup(name, selectedUsers);
//                System.out.println(selectedUser);
            }

            // Zamknięcie okna
            Stage stage = (Stage) ZatwierdzButtonG.getScene().getWindow();
            stage.close();
//            System.out.println("Wybrani użytkownicy: " + selectedUsers);

        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }

    }

    @FXML
    public void onOdrzucButtonG(ActionEvent actionEvent) {

        Stage stage = (Stage) OdrzucButtonG.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void onWybierzCzlonkowComboBox(ActionEvent actionEvent) {
    }

    @FXML
    private void updateListView() {

        if (MainController != null) {
            ListaCzlonkow.getItems().clear();
//            System.out.println("dziala");
            ListaCzlonkow.getItems().addAll(MainController.getZnajomi()); // Dodanie użytkowników
        }
    }
}
