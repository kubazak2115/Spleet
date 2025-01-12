package com.example.demo2;

import Aplikacja.Expense;
import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.WebSocket;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
   public Button DodajWydatek;
   public ComboBox WybierzGrupeComboBox;
   public TextField GroupBalanceTextField;
   public TextField YourBanalceTextField;


   //tabela 1
   @FXML
   public TableView<Expense> tabelaGrupy;


   @FXML
   private TableColumn<Expense, String> kolumnaNazwa;
   @FXML
   private TableColumn<Expense, Double> kolumnaWartosc;
   @FXML
   private TableColumn<Expense, String> kolumnaGrupa;
   @FXML
   private TableColumn<Expense, String> kolumnaAutor;
   @FXML
   private TableColumn<Expense, LocalDate> kolumnaData;
   @FXML
   private TableColumn<Expense, LocalDate> kolumnaKategoria;

   //tabela 2
   @FXML
   public TableView<Expense> tabelaUzytkownika;


   @FXML
   private TableColumn<Expense, String> kolumnaNazwa2;
   @FXML
   private TableColumn<Expense, Double> kolumnaWartosc2;
   @FXML
   private TableColumn<Expense, String> kolumnaGrupa2;
   @FXML
   private TableColumn<Expense, String> kolumnaAutor2;
   @FXML
   private TableColumn<Expense, LocalDate> kolumnaData2;
   @FXML
   private TableColumn<Expense, LocalDate> kolumnaKategoria2;

   @FXML
   private BarChart BarChart;

   @FXML
   private PieChart PieChart;

   private ObservableList<Group> Groups = FXCollections.observableArrayList();
   private ObservableList<User> Users = FXCollections.observableArrayList();
   private ObservableList<User> znajomi = FXCollections.observableArrayList();
//   private FilteredList<User> znajomi;
   private ObservableList<Group> grupyUzytkownika = FXCollections.observableArrayList();
//   private FilteredList<Group> grupyUzytkownika;
   private User WybranyUzytkownik;
//   private Stack<Integer> wolneId = new Stack<>();
//   private int nextId = 0;
   private Group WybranaGrupa;


   public void initialize() throws IOException {


      kolumnaNazwa.setCellValueFactory(new PropertyValueFactory<>("description"));
      kolumnaWartosc.setCellValueFactory(new PropertyValueFactory<>("price"));
      kolumnaGrupa.setCellValueFactory(new PropertyValueFactory<>("expenseGroup"));
      kolumnaAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
      kolumnaData.setCellValueFactory(new PropertyValueFactory<>("datum"));
      kolumnaKategoria.setCellValueFactory(new PropertyValueFactory<>("category"));


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
      LocalDate datka = LocalDate.now();
      addExpenseForGroup(datka, Groups.get(0),34, "kot", "Jedzenie");
      addExpenseForUser(datka, Groups.get(0),34, "kot", "Jedzenie");

      kolumnaNazwa2.setCellValueFactory(new PropertyValueFactory<>("description"));
      kolumnaWartosc2.setCellValueFactory(new PropertyValueFactory<>("price"));
      kolumnaGrupa2.setCellValueFactory(new PropertyValueFactory<>("expenseGroup"));
      kolumnaAutor2.setCellValueFactory(new PropertyValueFactory<>("author"));
      kolumnaData2.setCellValueFactory(new PropertyValueFactory<>("datum"));
      kolumnaKategoria2.setCellValueFactory(new PropertyValueFactory<>("category"));



      refresh();


//-------------------------------------------1ekranpokazywanielizstyznajomych vvvvvvvvvvvvvv-----------------------------------------

      ListaUzytkownikow.setItems(Users);  // Ustawiamy ListView na naszą listę użytkowników


      // Ustawiamy CellFactory, aby wyświetlać dane w formacie: Imię, Nazwisko, Saldo
//      ListaUzytkownikow.setCellFactory(param -> new ListCell<User>() {
//         @Override
//         protected void updateItem(User user, boolean empty) {
//            super.updateItem(user, empty);
//            if (empty || user == null) {
//               setText(null); // Gdy nie ma elementu, wyczyść tekst
//            } else {
//               // Ustawiamy tekst z podstawowymi danymi użytkownika
//               setText(user.getName() + " " + user.getSurname() + " - Saldo: " + user.getBalance());
//            }
//         }
//      });

//-------------------------------------------1ekranpokazywanielizstyznajomych ^^^^^^^^^^^^^^^^-----------------------------------------


//-------------------------------------------1ekranwybieranieuzytkownika vvvvvvvvvvvvvv-----------------------------------------

      WybierzUzytkownika.setItems(Users); // Powiązanie ComboBox z listą użytkowników
//      WybierzUzytkownika.setCellFactory(param -> new ListCell<User>() {
//         @Override
//         protected void updateItem(User user, boolean empty) {
//            super.updateItem(user, empty);
//            if (empty || user == null) {
//               setText(null);
//            } else {
//               setText(user.getName() + " " + user.getSurname());
//            }
//         }
//      });
//
//      WybierzUzytkownika.setButtonCell(new ListCell<User>() {
//         @Override
//         protected void updateItem(User user, boolean empty) {
//            super.updateItem(user, empty);
//            if (empty || user == null) {
//               setText(null);
//            } else {
//               setText(user.getName() + " " + user.getSurname());
//            }
//         }
//      });


      // Obsługa zmiany wybranego użytkownika
      WybierzUzytkownika.getSelectionModel().selectedItemProperty().addListener((obs, oldUser, newUser) -> {
         WybranyUzytkownik = (User) newUser;
         if (newUser != null) {
            aktualizujListeZnajomych((User) newUser);
            aktualizujListeGrup((User) newUser);
            WybierzGrupeComboBox.setItems(grupyUzytkownika);
            aktualizujWydatkiGrupy(WybranaGrupa);
            aktualizujWykres();
            aktualizujWykresPieChart();
            //System.out.println(WybranaGrupa.getName() + " restwybranagrupalsitenr");
            if (!WybranyUzytkownik.getGroups().contains(WybranaGrupa)) {
               WybranaGrupa = null;
            }
//            WybranaGrupa = null;
//            aktualizujWydatkiGrupy(wybranagrupa);

//            WybierzGrupeComboBox.getSelectionModel().clearSelection(); // Ustawienie na brak wybranej wartości
//            WybranaGrupa=null;

//            aktualizujWydatkiGrupy(null);
         }
         refresh();

      });
//-------------------------------------------1ekranwybieranieuzytkownika ^^^^^^^^^^^^^^^^^^-----------------------------------------


//-------------------------------------------1ekranpokazywaniegrup vvvvvvvvvvvvvv-----------------------------------------
//      ListaGrup.setCellFactory(param -> new ListCell<Group>() {
//         @Override
//         protected void updateItem(Group group, boolean empty) {
//            super.updateItem(group, empty);
//            if (empty || group == null) {
//               setText(null);
//            } else {
//               setText(group.getName());
//            }
//         }
//      });
//-------------------------------------------1ekranpokazywaniegrup ^^^^^^^^^^^-----------------------------------------


// -------------------------------------------2ekranwybieraniegrupy vvvvvv----------------------------------------------
      // Ustawienie elementów dla ComboBox
//      aktualizujListeGrup(WybranyUzytkownik);
//      WybierzGrupeComboBox.setItems(grupyUzytkownika);
//      WybranaGrupa = (Group) WybierzGrupeComboBox.getSelectionModel().getSelectedItem();


//// Ustawienie fabryki komórek dla rozwijanej listy
//      WybierzGrupeComboBox.setCellFactory(param -> new ListCell<Group>() {
//         @Override
//         protected void updateItem(Group group, boolean empty) {
//            super.updateItem(group, empty);
//            if (empty || group == null) {
//               setText(null); // Komórka pusta
//            } else {
//               setText(group.getName()); // Wyświetlanie nazwy grupy w rozwijanej liście
//            }
//         }
//      });
//
//// Ustawienie ButtonCell, która wyświetla wybraną wartość w ComboBox
//      WybierzGrupeComboBox.setButtonCell(new ListCell<Group>() {
//         @Override
//         protected void updateItem(Group group, boolean empty) {
//            super.updateItem(group, empty);
//            if (empty || group == null) {
//               setText("Wybierz grupę"); // Domyślny tekst, gdy nic nie wybrano
//            } else {
//               setText(group.getName()); // Wyświetlanie nazwy wybranej grupy
//            }
//         }
//      });







      WybierzGrupeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         if (newValue != null) { // Upewnij się, że wybrano coś (newValue != null)
            WybranaGrupa = (Group) newValue;;
            if (WybranaGrupa != null) {
               System.out.println("Wybrano grupę lsitener wybierz grupe : " + ((Group) newValue).getName());
               // Aktualizuj listę wydatków dla wybranej grupy
               aktualizujWydatkiGrupy(WybranaGrupa);
            } else {
               System.out.println("Brak wybranej grupy.");

               // Jeśli brak wybranej grupy, wyczyść listę wydatków
               aktualizujWydatkiGrupy(null);
//               wybranagrupa = WybierzGrupeComboBox.getSelectionModel().getSelectedItem();
            }
            refresh();
         }
      }); // nie pokazuja sie wydatki po zakometnowacniu wydatki grup[y w sensie

//-------------------------------------------2ekranwybieraniegrupy ^^^^^^^^^^^-----------------------------------


//-------------------------------------------2ekranwpokazywaniewydatkow vvvvvvvvvvvv-----------------------------------





//-------------------------------------------2ekranwpokazywaniewydatkow ^^^^^^^^^^^^^^^-----------------------------------

//---------------------------------------pokazwyanie twoichwydatkowvvvvvvv----------------------------------------
//--------------------------------------------to bedzie do wyrzucenia--------------------------------------
      // Konfiguracja wyświetlania opisu wydatku w ListView




   }

   public void aktualizujWykres() {
      if(WybranyUzytkownik != null) {
      BarChart.getData().clear(); // Czyszczenie wykresu

      LocalDate datka = LocalDate.now();
         XYChart.Series<String, Number> series = new XYChart.Series<>();
         series.setName(String.valueOf(WybranyUzytkownik));

         // Tworzenie danych dla każdego dnia w tygodniu
         for (int i = 6; i >=0; i--) {
            double expenseSum = 0;
            for (Expense expense : WybranyUzytkownik.getExpenses()) {
               if (expense.getDatum().equals(datka.minusDays(i))) {
                  expenseSum += expense.getPrice() / expense.getExpenseGroup().getSize();
               }
            }
            // Dodanie danych do serii
            series.getData().add(new XYChart.Data<>(datka.minusDays(i).toString(), expenseSum));
         }
         BarChart.getData().add(series); // Dodanie serii do wykresu
      }
   }

   public void aktualizujWykresPieChart() {
      PieChart.getData().clear();  // Czyszczenie wykresu

      Map<String, Double> wydatkiNaKategorie = new HashMap<>();

      // Iterujemy przez wydatki użytkownika
      for (Expense expense : WybranyUzytkownik.getExpenses()) {
         String kategoria = expense.getCategory().toString();
         double cena = expense.getPrice() / expense.getExpenseGroup().getSize();

         // Dodajemy wydatki do odpowiedniej kategorii
         wydatkiNaKategorie.put(kategoria, wydatkiNaKategorie.getOrDefault(kategoria, 0.0) + cena);
      }

      // Dodanie danych do wykresu piechart
      for (Map.Entry<String, Double> entry : wydatkiNaKategorie.entrySet()) {
         PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
         PieChart.getData().add(data);
      }
   }








   private void aktualizujListeZnajomych(User wybranyUser) {
      ObservableList<User> nowiuserzy = FXCollections.observableArrayList();

      for (User user : Users) {
         if (!user.equals(wybranyUser)) {
            nowiuserzy.add(user);
         }
      }
      znajomi = nowiuserzy;
      ListaUzytkownikow.setItems(znajomi);

      //do wyrzucenia ponizej chyba
//      znajomi = new FilteredList<>(Users, user -> !user.equals(wybranyUser));
   }

   private void aktualizujWydatkiGrupy(Group WybranaGrupa) {
      if (WybranaGrupa != null) {
         tabelaGrupy.setItems(WybranaGrupa.getExpenses());
      }
   }

   private void aktualizujListeGrup(User wybranyUser) {
      ObservableList<Group> Nowagrupa = FXCollections.observableArrayList();
      for (Group grupa : Groups) {
         if(grupa.getMembers().contains(wybranyUser)) {
            Nowagrupa.add(grupa);
         }
      }
      grupyUzytkownika = Nowagrupa;

   }

   private void aktualizujListewWydatkow() {
      if(WybranyUzytkownik != null) {
         tabelaUzytkownika.setItems(WybranyUzytkownik.getExpenses());
      }
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
      refresh();
   }


   public void addGroup(String name, ObservableList <User>  chosenUsers) throws IOException {
      Group newgroup = new Group(name);
      Groups.add(newgroup);
      newgroup.addMember(WybranyUzytkownik);
      for(User user : chosenUsers) {
         newgroup.addMember(user);
      }
      aktualizujListeGrup(WybranyUzytkownik);
//      WybierzGrupeComboBox.setItems(grupyUzytkownika);
   }

   public void addUser( String name, String surname, double balance) throws IOException {
//      int id = getAvailableId();
      User newuser = new User(/*id,*/ name, surname, balance);
      Users.add(newuser);
      WybranyUzytkownik = newuser;

      WybierzUzytkownika.getSelectionModel().select(WybranyUzytkownik);
//      refresh2();

   }

   public void addExpenseForUser(LocalDate datum, Group group, double price, String description, String category){
      Expense newexpenseforuser = new Expense(/*WybranyUzytkownik.getId(),*/ group, datum, price, description,  WybranyUzytkownik, category);
      for(User user : group.getMembers()) {
         user.addExpense(newexpenseforuser);
         user.addSpending(newexpenseforuser.getPrice()/(double)group.getSize());
         user.addAllSpending(newexpenseforuser.getPrice());
      }
      aktualizujListewWydatkow();
      refresh();






   }
   public void addExpenseForGroup(LocalDate datum, Group group, double price, String description, String category){
      Expense newexpenseforgroup = new Expense( WybranyUzytkownik ,group, datum, price, description, category);
      group.addExpense(newexpenseforgroup);
      aktualizujWydatkiGrupy(group);

      //ponizje chyba do usuniecia
//      ListaWydatkowGupy.setItems(group.getExpenses()); // Przypisanie listy wydatków
//      System.out.println(group.getExpenses());
//      group.updateBalances(newexpenseforgroup);
//      WybranaGrupa=group;
//      GroupAllMoney.setText(String.valueOf(group.getSpendings()));
//      GroupBalanceTextField.setText(String.valueOf(group.getSpendings()/(double)group.getSize()));

   }

   public void onUsunUzytkownika() {

      if (WybranyUzytkownik != null && Users.size()!=1) {
         ObservableList <Group> dousuniecia = FXCollections.observableArrayList(WybranyUzytkownik.getGroups());
         for (Group group : dousuniecia) {
            group.removeMember(WybranyUzytkownik);
         }
//         returnId(WybranyUzytkownik.getId()); // Zwrócenie ID do puli wolnych ID
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


//   private int getAvailableId() {
//      if (!wolneId.isEmpty()) {
//         return wolneId.pop(); // Pobierz ID ze stosu wolnych ID
//      } else {
//         return nextId++; // Wygeneruj nowe ID
//      }
//   }

//   private void returnId(int id) {
//      if (id >= 0 && id < 10) { // Ograniczenie puli ID do 0-9
//         wolneId.push(id);
//      }
//   }

   public void refresh(){
      if(WybranyUzytkownik != null) {
         TwojeImie.setText(WybranyUzytkownik.getName());
         TwojeNazwisko.setText(WybranyUzytkownik.getSurname());
         TwojeSaldo.setText(String.valueOf(WybranyUzytkownik.getBalance() - WybranyUzytkownik.getSpending()));
         aktualizujListeGrup(WybranyUzytkownik);
         aktualizujListeZnajomych(WybranyUzytkownik);
         WybranyUzytkownik = (User) WybierzUzytkownika.getValue();
         YourBanalceTextField.setText(String.valueOf(WybranyUzytkownik.getSpending()));
         aktualizujListewWydatkow();
         aktualizujWykres();
      }
      else{

      }
      if(WybranaGrupa!=null) {
         System.out.println(WybranaGrupa.getName());
         GroupBalanceTextField.setText(String.valueOf(WybranaGrupa.getBalance(WybranyUzytkownik)));
         GroupBalanceTextField.setText(String.valueOf(WybranaGrupa.getSpendings()/(double)WybranaGrupa.getSize()));
      }
      else{
         tabelaGrupy.setItems(null);
         GroupBalanceTextField.setText("Nie wybrales grupy");
      }
//      YourBanalceTextField.setText(String.valueOf(WybranyUzytkownik.getOverallBalance()));
   }

   public void pokazBlad(String wiadomosc) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Błąd");
      alert.setHeaderText(null);
      alert.setContentText(wiadomosc);
      alert.showAndWait();
   }






}
