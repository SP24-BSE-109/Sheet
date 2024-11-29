module com.sheet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.sheet to javafx.fxml;
    exports com.sheet;
}
