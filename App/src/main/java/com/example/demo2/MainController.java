package com.example.demo2;

import Aplikacja.Expense;
import Aplikacja.Group;
import Aplikacja.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MainController {

   public Button addUser;
   public Button utworzGrupe;
   public ListView listaUzytkownikow;
   public ComboBox wybierzUzytkownikaComboBox;
   public TextField twojeImie;
   public TextField twojeNazwisko;
   public TextField twojeSaldo;
   public Button usunUzytkownika;
   public ListView listaGrup;
   public Button dodajWydatek;
   public ComboBox wybierzGrupeComboBox;
   public TextField groupBalanceTextField;
   public TextField yourBanalceTextField;


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
   private ImageView obrazek;

   @FXML
   private BarChart BarChart;

   @FXML
   private PieChart PieChart;

   private ObservableList<Group> groups = FXCollections.observableArrayList();
   private ObservableList<User> users = FXCollections.observableArrayList();
   private ObservableList<User> znajomi = FXCollections.observableArrayList();
   private ObservableList<Group> grupyUzytkownika = FXCollections.observableArrayList();
   private User wybranyUzytkownik;
   private Group wybranaGrupa;


   public void initialize() throws IOException {

      Image obrazekspleet = new Image(getClass().getResource("/images/spleet.png").toExternalForm());
      if (obrazekspleet != null) {
         obrazek.setImage(obrazekspleet);
      }
      obrazek.setFitWidth(121);
      obrazek.setFitHeight(191);


      kolumnaNazwa.setCellValueFactory(new PropertyValueFactory<>("description"));
      kolumnaWartosc.setCellValueFactory(new PropertyValueFactory<>("price"));
      kolumnaGrupa.setCellValueFactory(new PropertyValueFactory<>("expenseGroup"));
      kolumnaAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
      kolumnaData.setCellValueFactory(new PropertyValueFactory<>("datum"));
      kolumnaKategoria.setCellValueFactory(new PropertyValueFactory<>("category"));


      addUser("Harisson", "Ford", 2000);
      addUser("Rafał", "Brzoska", 13000);
      ObservableList<User> dogrupy = FXCollections.observableArrayList();
      dogrupy.addAll(users);
      addGroup("Wyjazd w Góry", dogrupy);
      addUser("Seba", "Mila", 1500);
      addUser("rober", "lewy", 8000);
      ObservableList<User> dogrupy2 = FXCollections.observableArrayList();
      dogrupy2.addAll(users);
      addGroup("Kraków - wydatki", dogrupy2);


      LocalDate datka = LocalDate.now();
      addExpenseForGroup(datka, groups.get(1),34, "Kraków - jedzenie", "Jedzenie");
      addExpenseForUser(datka, groups.get(1),34, "Kraków - jedzenie", "Jedzenie");

      addExpenseForGroup(datka.minusDays(1), groups.get(1),45, "bus", "Transport");
      addExpenseForUser(datka.minusDays(1), groups.get(1),45, "bus", "Transport");

      addExpenseForGroup(datka.minusDays(2), groups.get(1),200, "casino", "Rozrywka");
      addExpenseForUser(datka.minusDays(2), groups.get(1),200, "casino", "Rozrywka");

      addExpenseForGroup(datka.minusDays(3), groups.get(1),100, "opłata za parking", "Inne");
      addExpenseForUser(datka.minusDays(3), groups.get(1),100, "opłata za parking", "Inne");

      addExpenseForGroup(datka.minusDays(4), groups.get(1),143, "Magulon", "Jedzenie");
      addExpenseForUser(datka.minusDays(4), groups.get(1),143, "Magulon", "Jedzenie");

      addExpenseForGroup(datka.minusDays(5), groups.get(1),700, "Samolot z Warszawy", "Transport");
      addExpenseForUser(datka.minusDays(5), groups.get(1),700, "Samolot z Warszawy", "Transport");

      addExpenseForGroup(datka.minusDays(6), groups.get(1),200, "Kraków - casino", "Rozrywka");
      addExpenseForUser(datka.minusDays(6), groups.get(1),200, "Kraków - casino", "Rozrywka");

      addExpenseForGroup(datka.minusDays(1), groups.get(0),70, "Pociąg do Zakopanego", "Jedzenie");
      addExpenseForUser(datka.minusDays(1), groups.get(0),70, "Pociąg do Zakopanego", "Jedzenie");

      kolumnaNazwa2.setCellValueFactory(new PropertyValueFactory<>("description"));
      kolumnaWartosc2.setCellValueFactory(new PropertyValueFactory<>("price"));
      kolumnaGrupa2.setCellValueFactory(new PropertyValueFactory<>("expenseGroup"));
      kolumnaAutor2.setCellValueFactory(new PropertyValueFactory<>("author"));
      kolumnaData2.setCellValueFactory(new PropertyValueFactory<>("datum"));
      kolumnaKategoria2.setCellValueFactory(new PropertyValueFactory<>("category"));


      if(wybranyUzytkownik != null) {
         wybierzGrupeComboBox.setItems(grupyUzytkownika);
         wybierzUzytkownikaComboBox.setItems(users);
         listaUzytkownikow.setItems(users);
      }

      refresh();

      // Obsługa zmiany wybranego użytkownika
      wybierzUzytkownikaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldUser, newUser) -> {
         wybranyUzytkownik = (User) newUser;
         if (newUser != null) {
            aktualizujListeZnajomych((User) newUser);
            aktualizujListeGrup((User) newUser);
            wybierzGrupeComboBox.setItems(grupyUzytkownika);
            aktualizujWydatkiGrupy(wybranaGrupa);
            aktualizujWykres();
            aktualizujWykresPieChart();
            if (!wybranyUzytkownik.getGroups().contains(wybranaGrupa)) {
               wybranaGrupa = null;
            }
         }
         refresh();

      });


      wybierzGrupeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         if (newValue != null) {
            wybranaGrupa = (Group) newValue;;
            if (wybranaGrupa != null) {
               aktualizujWydatkiGrupy(wybranaGrupa);
            } else {

               aktualizujWydatkiGrupy(null);
            }
            refresh();
         }
      });


   }

   public void aktualizujWykres() {
      if(wybranyUzytkownik != null) {
      BarChart.getData().clear();

      LocalDate datka = LocalDate.now();
      XYChart.Series<String, Number> series = new XYChart.Series<>();
      series.setName(String.valueOf(wybranyUzytkownik));

      for (int i = 6; i >=0; i--) {
         double expenseSum = 0;
         for (Expense expense : wybranyUzytkownik.getExpenses()) {
            if (expense.getDatum().equals(datka.minusDays(i))) {
               expenseSum += expense.getPrice() / expense.getExpenseGroup().getSize();
            }
         }
         series.getData().add(new XYChart.Data<>(datka.minusDays(i).toString(), expenseSum));
      }
      BarChart.getData().add(series);
      }
   }

   public void aktualizujWykresPieChart() {
      PieChart.getData().clear();

      Map<String, Double> wydatkiNaKategorie = new HashMap<>();

      for (Expense expense : wybranyUzytkownik.getExpenses()) {
         String kategoria = expense.getCategory().toString();
         double cena = expense.getPrice() / expense.getExpenseGroup().getSize();

         wydatkiNaKategorie.put(kategoria, wydatkiNaKategorie.getOrDefault(kategoria, 0.0) + cena);
      }

      for (Map.Entry<String, Double> entry : wydatkiNaKategorie.entrySet()) {
         PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
         PieChart.getData().add(data);
      }
   }

   private void aktualizujListeZnajomych(User wybranyUser) {
      ObservableList<User> nowiuserzy = FXCollections.observableArrayList();

      for (User user : users) {
         if (!user.equals(wybranyUser)) {
            nowiuserzy.add(user);
         }
      }
      znajomi = nowiuserzy;
      listaUzytkownikow.setItems(znajomi);


   }

   private void aktualizujWydatkiGrupy(Group WybranaGrupa) {
      if (WybranaGrupa != null) {
         tabelaGrupy.setItems(WybranaGrupa.getExpenses());
      }
   }

   private void aktualizujListeGrup(User wybranyUser) {
      ObservableList<Group> Nowagrupa = FXCollections.observableArrayList();
      for (Group grupa : groups) {
         if(grupa.getMembers().contains(wybranyUser)) {
            Nowagrupa.add(grupa);
         }
      }
      grupyUzytkownika = Nowagrupa;
      listaGrup.setItems(grupyUzytkownika);
   }

   private void aktualizujListewWydatkow() {
      if(wybranyUzytkownik != null) {
         tabelaUzytkownika.setItems(wybranyUzytkownik.getExpenses());
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

      if(users.size()>1) {

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

      if (grupyUzytkownika.size() > 0) {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddExpenseStage.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(loader.load()));
         stage.setTitle("Dodaj nowy wydatek");

         AddExpenseController ExpenseController = loader.getController();
         ExpenseController.initialize(wybranyUzytkownik, grupyUzytkownika);
         ExpenseController.setMainController(this);

         stage.show();
         refresh();
      }
      else{
         pokazBlad("To aplikacja do dzielenia się wydatkami, najpierw musisz być w grupie, żeby dodać wydatek");
      }
   }


   public void addGroup(String name, ObservableList <User>  chosenUsers) throws IOException {
      Group newgroup = new Group(name);
      groups.add(newgroup);
      newgroup.addMember(wybranyUzytkownik);
      for(User user : chosenUsers) {
         newgroup.addMember(user);
      }
      aktualizujListeGrup(wybranyUzytkownik);
   }

   public void addUser( String name, String surname, double balance) throws IOException {
      User newuser = new User(name, surname, balance);
      users.add(newuser);
      wybranyUzytkownik = newuser;

      wybierzUzytkownikaComboBox.getSelectionModel().select(wybranyUzytkownik);
   }

   public void addExpenseForUser(LocalDate datum, Group group, double price, String description, String category){
      Expense newexpenseforuser = new Expense(group, datum, price, description, wybranyUzytkownik, category);
      for(User user : group.getMembers()) {
         user.addExpense(newexpenseforuser);
         user.addSpending(newexpenseforuser.getPrice()/(double)group.getSize());
      }
      aktualizujWykresPieChart();
      aktualizujWykres();
      aktualizujListewWydatkow();
      refresh();

   }
   public void addExpenseForGroup(LocalDate datum, Group group, double price, String description, String category){
      Expense newexpenseforgroup = new Expense(wybranyUzytkownik,group, datum, price, description, category);
      group.addExpense(newexpenseforgroup);
      aktualizujWydatkiGrupy(group);

   }

   public void onUsunUzytkownika() {

      if (wybranyUzytkownik != null && users.size()!=1) {
         ObservableList <Group> dousuniecia = FXCollections.observableArrayList(wybranyUzytkownik.getGroups());
         for (Group group : dousuniecia) {
            group.removeMember(wybranyUzytkownik);
         }
         users.remove(wybranyUzytkownik);
      }
   }

   public ObservableList<User> getZnajomi() {
      return znajomi;
   }

   public void refresh(){
      if(wybranyUzytkownik != null) {
         twojeImie.setText(wybranyUzytkownik.getName());
         twojeNazwisko.setText(wybranyUzytkownik.getSurname());
         twojeSaldo.setText(String.valueOf(wybranyUzytkownik.getBalance() + wybranyUzytkownik.getSpending()));
         aktualizujListeGrup(wybranyUzytkownik);
         aktualizujListeZnajomych(wybranyUzytkownik);
         wybranyUzytkownik = (User) wybierzUzytkownikaComboBox.getValue();
         yourBanalceTextField.setText(String.valueOf(wybranyUzytkownik.getSpending()));
         aktualizujListewWydatkow();
      }
      if(wybranaGrupa !=null) {
         System.out.println(wybranaGrupa.getName());
         groupBalanceTextField.setText(String.valueOf(wybranaGrupa.getBalance(wybranyUzytkownik)));
         groupBalanceTextField.setText(String.valueOf(wybranaGrupa.getSpendings()/(double) wybranaGrupa.getSize()));
      }
      else{
         tabelaGrupy.setItems(null);
         groupBalanceTextField.setText("Nie wybrales grupy");
      }
   }

   public void pokazBlad(String wiadomosc) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Błąd");
      alert.setHeaderText(null);
      alert.setContentText(wiadomosc);
      alert.showAndWait();
   }

}
