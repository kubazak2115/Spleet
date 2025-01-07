package com.example.demo2;

import Aplikacja.Expense;
import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class MainController {

   public Button AddUser;
   public Button UtworzGrupe;
   public ListView ListaUzytkownikow;
   public ComboBox WybierzUzytkownika;
   public TextField TwojeImie;
   public TextField TwojeNazwisko;
   public TextField TwojeSaldo;
   public Button UsunUzytkownika;
   public ListView ListaGrup;
   public Button DodajWydatek;
   public ListView ListaWydatkowGupy;
   public ListView ListaTwoichWydatkow;
   public ComboBox WybierzGrupeComboBox;
   public TextField GroupBalanceTextField;
   public TextField YourBanalceTextField;
   public TextField WybranaGrupatextfield;
   public TextField YourAllMoney;
   public TextField GroupAllMoney;
   public TextField KategoriaWydatku;

   private ObservableList<Group> Groups = FXCollections.observableArrayList();
   private ObservableList<User> Users = FXCollections.observableArrayList();
   private FilteredList<User> znajomi;
   private FilteredList<Group> grupyUzytkownika;
   private User WybranyUzytkownik;
   private Stack<Integer> wolneId = new Stack<>();
   private int nextId = 0;
   private Group WybranaGrupa;


   public void initialize() throws IOException {

      addUser("tom", "ford", 23423);
      addUser("jan", "pawel", 343);
      ObservableList<User> dogrupy = FXCollections.observableArrayList();
      dogrupy.addAll(Users);
      addGroup("koty", dogrupy);
      addUser("seba", "mila", 23423);
      ObservableList<User> dogrupy2 = FXCollections.observableArrayList();
      dogrupy2.addAll(Users);
      addGroup("psy", dogrupy2);

      addUser("rober", "lewy", 32);
      LocalDate LocalDate = null;
      addExpenseForGroup(LocalDate, Groups.get(0),34, "kot");
      addExpenseForUser(LocalDate, Groups.get(0),34, "kot", "obiat");


      refresh();


//-------------------------------------------1ekranpokazywanielizstyznajomych vvvvvvvvvvvvvv-----------------------------------------

      ListaUzytkownikow.setItems(Users);  // Ustawiamy ListView na naszą listę użytkowników


      // Ustawiamy CellFactory, aby wyświetlać dane w formacie: Imię, Nazwisko, Saldo
      ListaUzytkownikow.setCellFactory(param -> new ListCell<User>() {
         @Override
         protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            if (empty || user == null) {
               setText(null); // Gdy nie ma elementu, wyczyść tekst
            } else {
               // Ustawiamy tekst z podstawowymi danymi użytkownika
               setText(user.getName() + " " + user.getSurname() + " - Saldo: " + user.getBalance());
            }
         }
      });

//-------------------------------------------1ekranpokazywanielizstyznajomych ^^^^^^^^^^^^^^^^-----------------------------------------


//-------------------------------------------1ekranwybieranieuzytkownika vvvvvvvvvvvvvv-----------------------------------------

      WybierzUzytkownika.setItems(Users); // Powiązanie ComboBox z listą użytkowników
      WybierzUzytkownika.setCellFactory(param -> new ListCell<User>() {
         @Override
         protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            if (empty || user == null) {
               setText(null);
            } else {
               setText(user.getName() + " " + user.getSurname());
            }
         }
      });

      WybierzUzytkownika.setButtonCell(new ListCell<User>() {
         @Override
         protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            if (empty || user == null) {
               setText(null);
            } else {
               setText(user.getName() + " " + user.getSurname());
            }
         }
      });


      // Obsługa zmiany wybranego użytkownika
      WybierzUzytkownika.getSelectionModel().selectedItemProperty().addListener((obs, oldUser, newUser) -> {
         WybranyUzytkownik = (User) newUser;
         if (newUser != null) {
            aktualizujListeZnajomych((User) newUser);
            aktualizujListeGrup((User) newUser);

//            WybierzGrupeComboBox.getSelectionModel().clearSelection(); // Ustawienie na brak wybranej wartości
//            WybranaGrupa=null;

            aktualizujWydatkiGrupy(null);
         }

         refresh();
      });
//-------------------------------------------1ekranwybieranieuzytkownika ^^^^^^^^^^^^^^^^^^-----------------------------------------


//-------------------------------------------1ekranpokazywaniegrup vvvvvvvvvvvvvv-----------------------------------------
      ListaGrup.setCellFactory(param -> new ListCell<Group>() {
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
//-------------------------------------------1ekranpokazywaniegrup ^^^^^^^^^^^-----------------------------------------


// -------------------------------------------2ekranwybieraniegrupy vvvvvv----------------------------------------------
      // Ustawienie elementów dla ComboBox
      WybierzGrupeComboBox.setItems(grupyUzytkownika);

// Ustawienie fabryki komórek dla rozwijanej listy
      WybierzGrupeComboBox.setCellFactory(param -> new ListCell<Group>() {
         @Override
         protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);
            if (empty || group == null) {
               setText(null); // Komórka pusta
            } else {
               setText(group.getName()); // Wyświetlanie nazwy grupy w rozwijanej liście
            }
         }
      });

// Ustawienie ButtonCell, która wyświetla wybraną wartość w ComboBox
      WybierzGrupeComboBox.setButtonCell(new ListCell<Group>() {
         @Override
         protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);
            if (empty || group == null) {
               setText("Wybierz grupę"); // Domyślny tekst, gdy nic nie wybrano
            } else {
               setText(group.getName()); // Wyświetlanie nazwy wybranej grupy
            }
         }
      });







      WybierzGrupeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         if (newValue != null) { // Upewnij się, że wybrano coś (newValue != null)
            Group wybranagrupa = (Group) newValue;;
            if (wybranagrupa != null) {
               System.out.println("Wybrano grupę: " + ((Group) newValue).getName());
               // Aktualizuj listę wydatków dla wybranej grupy
               aktualizujWydatkiGrupy(wybranagrupa);
            } else {
               System.out.println("Brak wybranej grupy.");

               // Jeśli brak wybranej grupy, wyczyść listę wydatków
               aktualizujWydatkiGrupy(null);
            }
            refresh();
         }
      });

//-------------------------------------------2ekranwybieraniegrupy ^^^^^^^^^^^-----------------------------------


//-------------------------------------------2ekranwpokazywaniewydatkow vvvvvvvvvvvv-----------------------------------


      ListaWydatkowGupy.setCellFactory(param -> new ListCell<Expense>() {
         @Override
         protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);
            if (empty || expense == null) {
               setText(null); // Komórka pusta
            } else {
               // Ustawienie niestandardowego tekstu dla komórki
               setText("WYDATEK: " + expense.getDescription() + " Z GRUPY " + expense.getExpenseGroup().getName() +
                       " O WARTOSCI : " + expense.getPrice() + " DOKONAL " + expense.getAuthor().getName() + " "+
                       expense.getAuthor().getSurname() + " DNIA " + expense.getDatum()  );
            }
         }
      });


//-------------------------------------------2ekranwpokazywaniewydatkow ^^^^^^^^^^^^^^^-----------------------------------

//---------------------------------------pokazwyanie twoichwydatkowvvvvvvv----------------------------------------

      // Konfiguracja wyświetlania opisu wydatku w ListView
      ListaTwoichWydatkow.setCellFactory(param -> new ListCell<Expense>() {
         @Override
         protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);
            if (empty || expense == null) {
               setText(null);
            } else {
               setText("WYDATEK: " + expense.getDescription() + " Z GRUPY " + expense.getExpenseGroup().getName() +
                       " O WARTOSCI : " + expense.getPrice() + " DOKONAL " + expense.getAuthor().getName() + " "+ expense.getAuthor().getSurname() +
                       " DNIA " + expense.getDatum() );
            }
         }
      });



   }






   private void aktualizujListeZnajomych(User wybranyUser) {
      znajomi = new FilteredList<>(Users, user -> !user.equals(wybranyUser));
      ListaUzytkownikow.setItems(znajomi);
   }

   private void aktualizujWydatkiGrupy(Group WybranaGrupa) {
      if (WybranaGrupa == null) {
         ListaWydatkowGupy.setItems(FXCollections.observableArrayList()); // Pusta lista
      } else {
         // Pobierz wydatki dla wybranej grupy
         ListaWydatkowGupy.setItems(FXCollections.observableArrayList(WybranaGrupa.getExpenses()));
      }
   }

   private void aktualizujListeGrup(User wybranyUser) {
      grupyUzytkownika = new FilteredList<>(Groups, group -> group.getMembers().contains(wybranyUser));
      ListaGrup.setItems(grupyUzytkownika);
      Group temp = (Group) WybierzGrupeComboBox.getValue();
      WybierzGrupeComboBox.getSelectionModel().clearSelection();
      WybierzGrupeComboBox.setItems(grupyUzytkownika);
//      WybierzGrupeComboBox.getSelectionModel().select();
      WybranaGrupa = temp;
   }

   private void aktualizujListewWydatkow() {
      ListaTwoichWydatkow.setItems(WybranyUzytkownik.getExpenses());
   }


   public void onAddUser(ActionEvent actionEvent) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUserStage.fxml"));
      Stage stage = new Stage();
      stage.setScene(new Scene(loader.load()));
      stage.setTitle("Dodaj nowego użytkownika");

      AddUserController Usercontroller = loader.getController();
      Usercontroller.setMainController(this);

      stage.show();
   }

   public void onUtworzGrupe(ActionEvent actionEvent) throws IOException {
      if(Users.size()>1) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddGroupStage.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(loader.load()));
         stage.setTitle("Dodaj nowego grupa");

         AddGroupController Groupcontroller = loader.getController();
         Groupcontroller.setMainController(this);

         stage.show();
      }
      else{
         pokazBlad("Masz za malo uzytkownikow na grupe");
      }
   }

   public void onDodajWydatek(ActionEvent actionEvent) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddExpenseStage.fxml"));
      Stage stage = new Stage();
      stage.setScene(new Scene(loader.load()));
      stage.setTitle("Dodaj nowy wydatek");

      AddExpenseController ExpenseController = loader.getController();
      ExpenseController.initialize(WybranyUzytkownik, grupyUzytkownika);
      ExpenseController.setMainController(this);

      stage.show();
//      refresh();
   }


   public void addGroup(String name, ObservableList <User>  chosenUsers) throws IOException {
      Group newgroup = new Group(name);
      Groups.add(newgroup);
      newgroup.addMember(WybranyUzytkownik);
      for(User user : chosenUsers) {
         newgroup.addMember(user);
      }
      aktualizujListeGrup(WybranyUzytkownik);
   }

   public void addUser( String name, String surname, double balance) throws IOException {
      int id = getAvailableId();
      User newuser = new User(id, name, surname, balance);
      Users.add(newuser);
      WybranyUzytkownik = newuser;
      WybierzUzytkownika.getSelectionModel().select(WybranyUzytkownik);

   }

   public void addExpenseForUser(LocalDate datum, Group group, double price, String description, String category){
      Expense newexpenseforuser = new Expense(WybranyUzytkownik.getId(), group, datum, price, description,WybranyUzytkownik);
      for(User user : group.getMembers()) {
         user.addExpense(newexpenseforuser);
         user.addSpending(newexpenseforuser.getPrice()/(double)group.getSize());
         user.addAllSpending(newexpenseforuser.getPrice());
      }
      aktualizujListewWydatkow();
      WybranaGrupatextfield.setText(group.getName());
      YourBanalceTextField.setText(String.valueOf(WybranyUzytkownik.getSpending()));
      YourAllMoney.setText(String.valueOf(WybranyUzytkownik.getAllSpending()));

//      WybranaGrupatextfield.setText(WybranaGrupa.getName());




   }
   public void addExpenseForGroup(LocalDate datum, Group group, double price, String description){
      Expense newexpenseforgroup = new Expense(WybranyUzytkownik.getId(), group, datum, price, description, WybranyUzytkownik);
      group.addExpense(newexpenseforgroup);
      ListaWydatkowGupy.setItems(group.getExpenses()); // Przypisanie listy wydatków
      System.out.println(group.getExpenses());
      group.updateBalances(newexpenseforgroup);
      WybranaGrupa=group;
      GroupAllMoney.setText(String.valueOf(group.getSpendings()));
      GroupBalanceTextField.setText(String.valueOf(group.getSpendings()/(double)group.getSize()));

   }

   public void onUsunUzytkownika() {

      if (WybranyUzytkownik != null && Users.size()!=1) {
         ObservableList <Group> dousuniecia = FXCollections.observableArrayList(WybranyUzytkownik.getGroups());
         for (Group group : dousuniecia) {
            group.removeMember(WybranyUzytkownik);
         }
         returnId(WybranyUzytkownik.getId()); // Zwrócenie ID do puli wolnych ID
         Users.remove(WybranyUzytkownik);
      }
   }
//   public void removeUser(User user) {

//   }

   public ObservableList<User> getUsers() {
//       System.out.println(Users.size());
      return Users;
   }

   public ObservableList<User> getZnajomi() {
      return znajomi;
   }


   private int getAvailableId() {
      if (!wolneId.isEmpty()) {
         return wolneId.pop(); // Pobierz ID ze stosu wolnych ID
      } else {
         return nextId++; // Wygeneruj nowe ID
      }
   }

   private void returnId(int id) {
      if (id >= 0 && id < 10) { // Ograniczenie puli ID do 0-9
         wolneId.push(id);
      }
   }

   public void refresh(){
      TwojeImie.setText(WybranyUzytkownik.getName());
      TwojeNazwisko.setText(WybranyUzytkownik.getSurname());
      TwojeSaldo.setText(String.valueOf(WybranyUzytkownik.getBalance() - WybranyUzytkownik.getSpending()));
      aktualizujListeGrup(WybranyUzytkownik);
      aktualizujListeZnajomych(WybranyUzytkownik);
      WybranyUzytkownik = (User) WybierzUzytkownika.getValue();
      if(WybranaGrupa!=null) {
         System.out.println(WybranaGrupa.getName());
         WybranaGrupatextfield.setText(WybranaGrupa.getName());
         GroupBalanceTextField.setText(String.valueOf(WybranaGrupa.getBalance(WybranyUzytkownik)));
         GroupAllMoney.setText(String.valueOf(WybranaGrupa.getSpendings()));
         GroupBalanceTextField.setText(String.valueOf(WybranaGrupa.getSpendings()/(double)WybranaGrupa.getSize()));
      }
      else{
         ListaWydatkowGupy.setItems(null);
         WybranaGrupatextfield.setText("Nie wybrales grupy");
         GroupAllMoney.setText("Nie wybrales grupy");
         GroupBalanceTextField.setText("Nie wybrales grupy");
      }
      YourBanalceTextField.setText(String.valueOf(WybranyUzytkownik.getSpending()));
      YourAllMoney.setText(String.valueOf(WybranyUzytkownik.getAllSpending()));
      aktualizujListewWydatkow();
//      YourBanalceTextField.setText(String.valueOf(WybranyUzytkownik.getOverallBalance()));
   }

   public void pokazBlad(String wiadomosc) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Błąd");
      alert.setHeaderText(null);
      alert.setContentText(wiadomosc);
      alert.showAndWait(); }





}
