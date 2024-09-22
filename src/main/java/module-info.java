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
    requires org.postgresql.jdbc;

    opens com.example.medical_records_management_system to javafx.fxml;
    exports com.example.medical_records_management_system;
    exports com.example.medical_records_management_system.folder_database;
    opens com.example.medical_records_management_system.folder_database to javafx.fxml;
    exports com.example.medical_records_management_system.folder_login_page;
    opens com.example.medical_records_management_system.folder_login_page to javafx.fxml;
    exports com.example.medical_records_management_system.folder_calendar;
    opens com.example.medical_records_management_system.folder_calendar to javafx.fxml;
    exports com.example.medical_records_management_system.folder_management;
    opens com.example.medical_records_management_system.folder_management to javafx.fxml;
    exports com.example.medical_records_management_system.folder_main;
    opens com.example.medical_records_management_system.folder_main to javafx.fxml;
}