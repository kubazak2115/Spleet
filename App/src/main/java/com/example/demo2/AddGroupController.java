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
    public TextField nazwaTextfield;
    @FXML
    public ListView ListaCzlonkow;
    private MainController MainController;

    public void setMainController(MainController newMainController) {
        this.MainController = newMainController;
        updateListView();
    }
    @FXML
    public void initialize() {
        updateListView();
        ListaCzlonkow.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    public void onZatwierdzButtonG(ActionEvent actionEvent) throws IOException {

        String name = nazwaTextfield.getText();

        if (name.isEmpty()) {
            System.out.println("Nazwa grupy nie może być pusta.");
            return;
        }

        try {
            ListaCzlonkow.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            ObservableList<User> selectedUsers = FXCollections.observableArrayList();
            selectedUsers.addAll(ListaCzlonkow.getSelectionModel().getSelectedItems());
            if(selectedUsers != null) {
                MainController.addGroup(name, selectedUsers);
            }

            Stage stage = (Stage) ZatwierdzButtonG.getScene().getWindow();
            stage.close();
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
            ListaCzlonkow.getItems().addAll(MainController.getZnajomi()); // Dodanie użytkowników
        }
    }
}
