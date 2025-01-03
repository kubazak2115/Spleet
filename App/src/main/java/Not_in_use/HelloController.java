package Not_in_use;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloController extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Tworzenie głównego kontenera zakładek
        TabPane tabPane = new TabPane();

        // Zakładka 1
        Tab tab1 = new Tab("Zakładka 1");
        StackPane content1 = new StackPane(); // Główna zawartość zakładki
        content1.getChildren().add(new javafx.scene.control.Label("Tutaj treść zakładki 1"));
        tab1.setContent(content1);

        // Zakładka 2
        Tab tab2 = new Tab("Zakładka 2");
        StackPane content2 = new StackPane();
        content2.getChildren().add(new javafx.scene.control.Label("Tutaj treść zakładki 2"));
        tab2.setContent(content2);

        // Zakładka 3
        Tab tab3 = new Tab("Zakładka 3");
        StackPane content3 = new StackPane();
        content3.getChildren().add(new javafx.scene.control.Label("Tutaj treść zakładki 3"));
        tab3.setContent(content3);

        // Dodawanie zakładek do kontenera
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        // Wyłącz możliwość zamykania zakładek (opcjonalnie)
        tabPane.getTabs().forEach(tab -> tab.setClosable(false));

        // Tworzenie sceny
        Scene scene = new Scene(tabPane, 800, 600);

        // Ustawienie sceny na stage
        primaryStage.setTitle("Przykład z zakładkami");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
