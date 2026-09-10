module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires java.logging;

    opens org.example.viewController to javafx.fxml;

    exports org.example;
}