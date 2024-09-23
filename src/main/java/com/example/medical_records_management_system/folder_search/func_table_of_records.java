package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.data_medical_records;
import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class func_table_of_records {

    private final database_handler dbHandler = new database_handler();

    public void initializeTable(TableView<data_medical_records> table_view_search, TableColumn<data_medical_records, String> column_date, TableColumn<data_medical_records, Integer> column_record,
                                TableColumn<data_medical_records, String> column_patient, TableColumn<data_medical_records, String> column_diagnosis,
                                TableColumn<data_medical_records, String> column_treatment) {

        // Настройка колонок таблицы
        column_date.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        column_record.setCellValueFactory(new PropertyValueFactory<>("medicalRecordId"));
        column_patient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        column_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        column_treatment.setCellValueFactory(new PropertyValueFactory<>("treatmentPlan"));

        column_date.setResizable(false);
        column_record.setResizable(false);
        column_patient.setResizable(false);
        column_diagnosis.setResizable(false);
        column_treatment.setResizable(false);

        // Загрузка данных для конкретного доктора
        displayRecordsForDoctor(table_view_search);

        // Добавляем возможность клика на строки
        table_view_search.setRowFactory(tv -> {
            TableRow<data_medical_records> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    data_medical_records selectedRecord = row.getItem();
                    System.out.println("Selected record: " + selectedRecord);
                    // Добавь логику для обработки выбранной записи
                }
            });
            return row;
        });
    }

    public void displayRecordsForDoctor(TableView<data_medical_records> table_view_search) {
        // Получаем doctor_id из data_doctor
        int doctorId = data_doctor.getDoctorId();

        // Получаем список записей для данного врача
        List<data_medical_records> records = dbHandler.getRecordsByDoctorId(doctorId);

        // Создаем ObservableList для таблицы
        ObservableList<data_medical_records> recordObservableList = FXCollections.observableArrayList(records);

        // Устанавливаем данные в TableView
        table_view_search.setItems(recordObservableList);
    }
}

