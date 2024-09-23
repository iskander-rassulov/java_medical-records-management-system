package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.data_medical_records;
import com.example.medical_records_management_system.data_patients;
import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class func_table_of_patients {
    private final database_handler dbHandler = new database_handler();

    public ObservableList<data_patients> initializeTable(TableView<data_patients> table_view_patient,
                                                         TableColumn<data_patients, Integer> column_patient_id,
                                                         TableColumn<data_patients, Integer> column_record_id,
                                                         TableColumn<data_patients, String> column_f_name,
                                                         TableColumn<data_patients, String> column_s_name) {

        // Настройка колонок таблицы
        column_patient_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        column_record_id.setCellValueFactory(new PropertyValueFactory<>("medicalRecordId"));
        column_f_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column_s_name.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        column_patient_id.setResizable(false);
        column_record_id.setResizable(false);
        column_f_name.setResizable(false);
        column_s_name.setResizable(false);
        // Загрузка данных для конкретного доктора
        return displayPatientsForDoctor(table_view_patient);
    }

    public ObservableList<data_patients> displayPatientsForDoctor(TableView<data_patients> table_view_search) {
        // Получаем doctor_id из data_doctor
        int doctorId = data_doctor.getDoctorId();

        // Получаем список записей для данного врача
        List<data_patients> patients = dbHandler.getPatientsByDoctorId(doctorId);

        // Создаем ObservableList для таблицы
        ObservableList<data_patients> recordObservableList = FXCollections.observableArrayList(patients);

        // Устанавливаем данные в TableView
        table_view_search.setItems(recordObservableList);

        return recordObservableList;
    }


}
