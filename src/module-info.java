module DSProject4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens application to javafx.graphics, javafx.fxml, javafx.base;
}
