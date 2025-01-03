package com.example.demo2;

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
import java.security.PublicKey;
import java.util.Stack;

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
   private ObservableList<Group> Groups = FXCollections.observableArrayList();
   private ObservableList<User> Users = FXCollections.observableArrayList();
   private FilteredList<User> znajomi;
   private FilteredList<Group> grupyUzytkownika;
   private User WybranyUzytkownik;
   private Stack<Integer> wolneId = new Stack<>();
   private int nextId = 0;




   public void initialize(){


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
         }
         refresh();
      });

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
      
   }

   private void aktualizujListeZnajomych(User wybranyUser) {
      znajomi = new FilteredList<>(Users, user -> !user.equals(wybranyUser));
      ListaUzytkownikow.setItems(znajomi);
   }

   private void aktualizujListeGrup(User wybranyUser) {
      grupyUzytkownika = new FilteredList<>(Groups, group -> group.getMembers().contains(wybranyUser));
      ListaGrup.setItems(grupyUzytkownika);
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddGroupStage.fxml"));
      Stage stage = new Stage();
      stage.setScene(new Scene(loader.load()));
      stage.setTitle("Dodaj nowego grupa");

      AddGroupController Groupcontroller = loader.getController();
      Groupcontroller.setMainController(this);

      stage.show();
   }

   public void addGroup(String name, User chosenUser) throws IOException {
      Group newgroup = new Group(name);
      Groups.add(newgroup);
      newgroup.addMember(WybranyUzytkownik);
      newgroup.addMember(chosenUser);
      aktualizujListeGrup(WybranyUzytkownik);
   }

   public void addUser( String name, String surname, double balance) throws IOException {
      int id = getAvailableId();
      User newuser = new User(id, name, surname, balance);
      Users.add(newuser);
      WybranyUzytkownik = newuser;
      WybierzUzytkownika.getSelectionModel().select(WybranyUzytkownik);

   }

   public void onUsunUzytkownika() {

      if (WybranyUzytkownik != null && Users.size()!=1) {
         Users.remove(WybranyUzytkownik);
         returnId(WybranyUzytkownik.getId()); // Zwrócenie ID do puli wolnych ID
      }
   }

//   public void removeUser(User user) {
//   }

   public ObservableList<User> getUsers() {
       System.out.println(Users.size());
      return Users;
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
      TwojeSaldo.setText(String.valueOf(WybranyUzytkownik.getBalance()));
      aktualizujListeGrup(WybranyUzytkownik);
   }


}
