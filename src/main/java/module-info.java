module com.example.medical_records_management_system {
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires java.sql;

    opens com.example.medical_records_management_system to javafx.fxml;
    exports com.example.medical_records_management_system;
    exports com.example.medical_records_management_system.database;
    opens com.example.medical_records_management_system.database to javafx.fxml;
    exports com.example.medical_records_management_system.login_page;
    opens com.example.medical_records_management_system.login_page to javafx.fxml;
}