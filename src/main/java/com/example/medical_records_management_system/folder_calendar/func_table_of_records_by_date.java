package com.example.medical_records_management_system.folder_calendar;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_data.data_medical_records;
import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_search.func_open_record_info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class func_table_of_records_by_date {
    private final database_handler dbHandler = new database_handler();
    public static int chosenRecordId;

    public ObservableList<data_medical_records> initializeTable(TableView<data_medical_records> table_view_calendar,
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

        table_view_calendar.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {  // Двойной щелчок
                data_medical_records selectedRecord = table_view_calendar.getSelectionModel().getSelectedItem();
                if (selectedRecord != null) {
                    System.out.println("Запись выбрана: " + selectedRecord.getMedicalRecordId());// Проверка
                    func_open_record_info recordInfo = new func_open_record_info();
                    assert rightPane != null;
                    recordInfo.showRecordInfo(rightPane, selectedRecord);  // Передаем right_pane
                    chosenRecordId = selectedRecord.getMedicalRecordId();
                }
            }
        });

        // Загрузка данных для конкретного доктора
        return displayRecordsForDoctor(table_view_calendar);
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

    public static int getChosenRecordId(){
        return chosenRecordId;
    }

    public ObservableList<data_medical_records> getRecordsByDate(LocalDate date) {
        ObservableList<data_medical_records> recordList = FXCollections.observableArrayList();

        try {
            // Получаем doctor_id из data_doctor (предполагая, что doctor_id уже сохранен после авторизации)
            int doctorId = data_doctor.getDoctorId();

            // Преобразуем LocalDate в Date для работы с базой данных
            Date sqlDate = Date.valueOf(date);
            System.out.println(sqlDate);

            // SQL-запрос для получения записей по выбранной дате и doctor_id
            String query = "SELECT * FROM medical_records WHERE visit_date = ? AND doctor_id = ?";

            // Подготовка запроса
            PreparedStatement preparedStatement = database_handler.getConnection().prepareStatement(query);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2, doctorId); // Устанавливаем doctor_id в запрос

            ResultSet resultSet = preparedStatement.executeQuery();

            // Заполняем список результатами запроса
            while (resultSet.next()) {
                data_medical_records record = new data_medical_records();
                record.setMedicalRecordId(resultSet.getInt("medical_record_id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setDoctorId(resultSet.getInt("doctor_id"));
                record.setVisitDate(resultSet.getString("visit_date"));
                record.setDiagnosis(resultSet.getString("diagnosis"));
                record.setTreatmentPlan(resultSet.getString("treatment_plan"));

                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordList;
    }



}
