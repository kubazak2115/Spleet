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
    public ComboBox KategoriaWydatku;

    private MainController mainController;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }

    public void initialize(User WybranyUzytkownik, ObservableList<Group> grupyuzytkownika) {
        ObservableList<String> wydatkiOpcje = FXCollections.observableArrayList(
                "Po równo"

        );
        ObservableList<String> kategorieOpcje = FXCollections.observableArrayList(
                "Inwestycje", "Jedzenie", "Rozrywka", "Transport", "Inne"

        );

        GrupaWydatku.setItems(grupyuzytkownika);
        KategoriaWydatku.setItems(kategorieOpcje);
        PodziaWydatku.setItems(wydatkiOpcje);

    }



    public void onDodajWydatek(ActionEvent actionEvent) {
        LocalDate datum = LocalDate.now();
        double price = 0.0;
        String description = "";
        String category = "";

        try{
            datum = DataWydatku.getValue();
            price = Double.parseDouble(WartoscWydatku.getText());
            description = NazwaWydatku.getText();
            category = KategoriaWydatku.getValue().toString();
        }
        catch(NumberFormatException e){
            pokazBlad("Niepoprawne dane liczbowe. Spróbuj ponownie.");
            return;
        }
        catch (NullPointerException e) {
            pokazBlad("Nie wybrales ktoregos z rozwijanych pól");
            return;
        }

        Group wybranagrupa = (Group) GrupaWydatku.getValue();

        mainController.addExpenseForGroup(datum, wybranagrupa, price, description, category);
        mainController.addExpenseForUser(datum, wybranagrupa, price, description, category);

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
