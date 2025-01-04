package com.example.demo2;

import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddExpenseController {
    public TextField NazwaWydatku;
    public ComboBox GrupaWydatku;
    public DatePicker DataWydatku;
    public TextField WartoscWydatku;
    public ComboBox PodziaWydatku;
    public Button DodajWydatek;
    public Button OdzrucWydatek;

    private MainController mainController;
    ObservableList<String> wydatkiOpcje = FXCollections.observableArrayList(
            "Po równo",
            "Ty wisisz całe siano drugiemu",
            "Drugi wisi całe siano"
    );

    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }

    public void initialize(User WybranyUzytkownik, ObservableList<Group> grupyuzytkownika) {

        PodziaWydatku.setItems(wydatkiOpcje);

        GrupaWydatku.setItems(grupyuzytkownika);

        // Konfiguracja wyświetlania nazw grup w ComboBox
        GrupaWydatku.setCellFactory(param -> new ListCell<Group>() {
            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (empty || group == null) {
                    setText(null);
                } else {
                    setText(group.getName());
                }
            }
        });

        GrupaWydatku.setButtonCell(new ListCell<Group>() {
            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (empty || group == null) {
                    setText(null);
                } else {
                    setText(group.getName());
                }
            }
        });

    }



    public void onDodajWydatek(ActionEvent actionEvent) {
        LocalDate datum = LocalDate.now();
        double price = 666;
        String description = "666";

        try{
            datum = DataWydatku.getValue();
            price = Double.parseDouble(WartoscWydatku.getText());
            description = NazwaWydatku.getText();

        }
        catch(NumberFormatException e){
            pokazBlad("Niepoprawne dane liczbowe. Spróbuj ponownie.");
//            return;
        }

        Group wybranagrupa = (Group) GrupaWydatku.getValue();
        System.out.println(wybranagrupa);
        String OpcjaPodzialu = (String) PodziaWydatku.getValue();
        mainController.addExpenseForGroup(datum, wybranagrupa, price, description);
        mainController.addExpenseForUser(datum, wybranagrupa, price, description);

        Stage stage = (Stage) DodajWydatek.getScene().getWindow();
        stage.close();
    }

    public void onOdrzucWydatek(ActionEvent actionEvent) {
        Stage stage = (Stage) OdzrucWydatek.getScene().getWindow();
        stage.close();

    }

    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait(); }
}
