package com.example.demo2;

import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddGroupController {


    public Button ZatwierdzButtonG;
    public Button OdrzucButtonG;
    public ComboBox WybierzCzlonkowComboBox;
    public TextField nazwaTextfield;
    public ListView ListaCzlonkow;
    private MainController MainController;

    public void setMainController(MainController newMainController) {
        this.MainController = newMainController;
        updateListView();
        System.out.println(newMainController);

    }

    public void initialize() {
        ListaCzlonkow.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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

        updateListView();
        System.out.println(MainController);

    }


    public void onZatwierdzButtonG(ActionEvent actionEvent) throws IOException {

        String name = nazwaTextfield.getText();

        if (name.isEmpty()) {
            System.out.println("Nazwa grupy nie może być pusta.");
            return;
        }

        try {
            // Pobranie wybranych użytkowników z ListView

            // Dodanie grupy do kontrolera głównego

            User selectedUser= (User) ListaCzlonkow.getSelectionModel().getSelectedItem();

            MainController.addGroup(name, selectedUser);

            // Zamknięcie okna
            Stage stage = (Stage) ZatwierdzButtonG.getScene().getWindow();
            stage.close();
            System.out.println("Wybrani użytkownicy: " + selectedUser);

        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }

    }

    public void onOdrzucButtonG(ActionEvent actionEvent) {

        Stage stage = (Stage) OdrzucButtonG.getScene().getWindow();
        stage.close();

    }

    public void onWybierzCzlonkowComboBox(ActionEvent actionEvent) {
    }

    private void updateListView() {

        if (MainController != null) {
            ListaCzlonkow.getItems().clear();
//            System.out.println("dziala");
            ListaCzlonkow.getItems().addAll(MainController.getUsers()); // Dodanie użytkowników
        }
    }
}
