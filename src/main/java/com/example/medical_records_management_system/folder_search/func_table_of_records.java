package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_data.data_medical_records;
import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class func_table_of_records {

    private final database_handler dbHandler = new database_handler();

    public ObservableList<data_medical_records> initializeTable(TableView<data_medical_records> table_view_search,
                                                                TableColumn<data_medical_records, String> column_date,
                                                                TableColumn<data_medical_records, Integer> column_record,
                                                                TableColumn<data_medical_records, String> column_patient,
                                                                TableColumn<data_medical_records, String> column_diagnosis,
                                                                TableColumn<data_medical_records, String> column_treatment,
                                                                AnchorPane rightPane) {

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

        column_date.setEditable(false);
        column_record.setEditable(false);
        column_patient.setEditable(false);
        column_diagnosis.setEditable(false);
        column_treatment.setEditable(false);

        // Проверяем, что right_pane не равен null
        if (rightPane == null) {
            System.out.println("Error: right_pane is null.");
        }

        table_view_search.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {  // Двойной щелчок
                data_medical_records selectedRecord = table_view_search.getSelectionModel().getSelectedItem();
                if (selectedRecord != null) {
                    System.out.println("Запись выбрана: " + selectedRecord.getMedicalRecordId());  // Проверка
                    func_open_record_info recordInfo = new func_open_record_info();
                    assert rightPane != null;
                    recordInfo.showRecordInfo(rightPane, selectedRecord);  // Передаем right_pane
                }
            }
        });

        // Загрузка данных для конкретного доктора
        return displayRecordsForDoctor(table_view_search);
    }

    public ObservableList<data_medical_records> displayRecordsForDoctor(TableView<data_medical_records> table_view_search) {
        // Получаем doctor_id из data_doctor
        int doctorId = data_doctor.getDoctorId();

        // Получаем список записей для данного врача
        List<data_medical_records> records = dbHandler.getRecordsByDoctorId(doctorId);

        // Создаем ObservableList для таблицы
        ObservableList<data_medical_records> recordObservableList = FXCollections.observableArrayList(records);

        // Устанавливаем данные в TableView
        table_view_search.setItems(recordObservableList);

        return recordObservableList;
    }
}


