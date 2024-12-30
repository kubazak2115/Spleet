package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldAmount;

    @FXML
    private DatePicker fieldDate;

    @FXML
    private ChoiceBox<String> choiceCategory;

    @FXML
    private ChoiceBox<String> choiceType;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableView<Object> tableView;

    @FXML
    private TableColumn<Object, String> columnId;

    @FXML
    private TableColumn<Object, String> columnData;

    @FXML
    private TableColumn<Object, String> columnKategoria;

    @FXML
    private TableColumn<Object, String> columnWydatek;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Dodanie opcji do ChoiceBox
        choiceCategory.getItems().addAll("Kategoria 1", "Kategoria 2");
        choiceType.getItems().addAll("Przychód", "Wydatek");

        // Dodanie danych do PieChart
        pieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Kategoria 1", 40),
                new PieChart.Data("Kategoria 2", 60)
        ));

        // Ustawienie danych tabeli
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        columnWydatek.setCellValueFactory(new PropertyValueFactory<>("wydatek"));
    }
}
