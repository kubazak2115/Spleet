package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {

    public TextField ImieTextfield;
    public TextField NazwiskoTextField;
    public TextField saldoTextField;
    public Button ZatwierdzButton;
    public Button OdrzucButton;
    private MainController MainController;

    public void setMainController(MainController newMainController) {
        this.MainController = newMainController;
    }

    public void initialize() {

    }


    public void onZatwierdzButton(ActionEvent actionEvent) {

        String name;
        String surname;
        double balance;

        try{
            name = ImieTextfield.getText();
            surname = NazwiskoTextField.getText();
            balance = Double.parseDouble(saldoTextField.getText());

        }
        catch(NumberFormatException e){
            System.out.println("Niepoprawne dane liczbowe. Spróbuj ponownie.");
//            return;
        }
        catch(ClassCastException e){
            System.out.println("Niepoprawny format danych. Spróbuj ponownie.");
        }

        Stage stage = (Stage) ZatwierdzButton.getScene().getWindow();
        stage.close();

    }

    public void onOdrzucButton(ActionEvent actionEvent) {

        Stage stage = (Stage) OdrzucButton.getScene().getWindow();
        stage.close();

    }
}
