module com.example.paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires junit;


    opens com.example.paint to javafx.fxml;
    exports com.example.paint;
}