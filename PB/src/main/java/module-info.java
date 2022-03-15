module com.example.zadanie1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.zadanie1 to javafx.fxml;
    exports com.example.zadanie1;
}