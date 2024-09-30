package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_data.data_patients;
import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class func_table_of_patients {
    private final database_handler dbHandler = new database_handler();
    private final func_open_patient_info patientInfoOpener = new func_open_patient_info();
    public static int chosenPatientId;



    public ObservableList<data_patients> initializeTable(TableView<data_patients> table_view_patient,
                                                         TableColumn<data_patients, Integer> column_patient_id,
                                                         TableColumn<data_patients, Integer> column_record_id,
                                                         TableColumn<data_patients, String> column_f_name,
                                                         TableColumn<data_patients, String> column_s_name,
                                                         AnchorPane rightPane) {

        // Настройка колонок таблицы
        column_patient_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        column_record_id.setCellValueFactory(new PropertyValueFactory<>("medicalRecordId"));
        column_f_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column_s_name.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        column_patient_id.setResizable(false);
        column_record_id.setResizable(false);
        column_f_name.setResizable(false);
        column_s_name.setResizable(false);

        column_patient_id.setEditable(false);
        column_record_id.setEditable(false);
        column_f_name.setEditable(false);
        column_s_name.setEditable(false);

        // Проверяем, что right_pane не равен null
        if (rightPane == null) {
            System.out.println("Error: right_pane is null.");
        }

        table_view_patient.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {  // Двойной щелчок
                data_patients selectedPatient = table_view_patient.getSelectionModel().getSelectedItem();
                if (selectedPatient != null) {
                    System.out.println("Пациент выбран: " + selectedPatient.getFirstName());  // Проверка
                    func_open_patient_info patientInfoOpener = new func_open_patient_info();
                    assert rightPane != null;
                    patientInfoOpener.showPatientInfo(rightPane, selectedPatient);  // Передаем right_pane
                    chosenPatientId = selectedPatient.getPatientId();
                }
            }
        });


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

    public static int getChosenPatientId(){
        return chosenPatientId;
    }

}
