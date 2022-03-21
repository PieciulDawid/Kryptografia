module bb.dd.dp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires com.google.common;

    opens bb.dd.dp.zadanie1 to javafx.fxml;
    exports bb.dd.dp.zadanie1;
}