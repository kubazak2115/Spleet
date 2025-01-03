package com.example.demo2;

import Aplikacja.Group;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGroupController {


    public Button ZatwierdzButtonG;
    public Button OdrzucButtonG;
    public ComboBox WybierzCzlonkowComboBox;
    public TextField nazwaTextfield;
    private MainController MainController;

    public void setMainController(MainController newMainController) {
        this.MainController = newMainController;
    }

    public void initialize() {

    }


    public void onZatwierdzButtonG(ActionEvent actionEvent) {

        String name;


        try{
            name = nazwaTextfield.getText();


        }
        catch(NumberFormatException e){
            System.out.println("Niepoprawne dane liczbowe. Spróbuj ponownie.");
        }
        catch(ClassCastException e){
            System.out.println("Niepoprawny format danych. Spróbuj ponownie.");
        }



        Stage stage = (Stage) ZatwierdzButtonG.getScene().getWindow();
        stage.close();

    }

    public void onOdrzucButtonG(ActionEvent actionEvent) {

        Stage stage = (Stage) OdrzucButtonG.getScene().getWindow();
        stage.close();


    }

    public void onWybierzCzlonkowComboBox(ActionEvent actionEvent) {
    }
}
