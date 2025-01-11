module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
    exports Not_in_use;
    opens Not_in_use to javafx.fxml;
    opens Aplikacja to javafx.base;
    exports Aplikacja;

}