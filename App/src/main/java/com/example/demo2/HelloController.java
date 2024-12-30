package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Wydatek;

public class HelloController {

    @FXML
    private TableView<Wydatek> tableView;

    @FXML
    private TableColumn<Wydatek, Integer> columnId;

    @FXML
    private TableColumn<Wydatek, String> columnData;

    @FXML
    private TableColumn<Wydatek, String> columnKategoria;

    @FXML
    private TableColumn<Wydatek, Double> columnWydatek;

    private final ObservableList<Wydatek> wydatki = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Powiązanie kolumn z właściwościami w modelu danych
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        columnWydatek.setCellValueFactory(new PropertyValueFactory<>("wydatek"));

        // Dodanie przykładowych danych do tabeli
        wydatki.add(new Wydatek(1, "2024-12-30", "Jedzenie", 150.50));
        wydatki.add(new Wydatek(2, "2024-12-29", "Transport", 50.00));
        wydatki.add(new Wydatek(3, "2024-12-28", "Rozrywka", 120.75));

        // Przypisanie danych do TableView
        tableView.setItems(wydatki);
    }
}
