module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;

    opens com.example.demo1.model to javafx.base;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.view;
    opens com.example.demo1.view to javafx.fxml;
}