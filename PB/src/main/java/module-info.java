module com.example.zadanie1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zadanie1 to javafx.fxml;
    exports com.example.zadanie1;
}