package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_patient.func_table_of_patients;
import com.example.medical_records_management_system.folder_search.func_table_of_records;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class func_manage_patient {
    @FXML
    public JFXButton buttonChangeInformation;
    @FXML
    public JFXButton buttonDeletePatient;
    @FXML
    public Text text_deleted_patient;



    public void initialize(){
        buttonChangeInformation.setOnMouseClicked(event ->{
            func_open_modify_patient openModifyPatient = new func_open_modify_patient();
            openModifyPatient.showModifyPatient(AppContext.getInstance().getCenterPane());
        });

        buttonDeletePatient.setOnMouseClicked(event -> {
            // Получаем выбранный идентификатор записи
            int patientIdToDelete = func_table_of_patients.getChosenPatientId();

            if (patientIdToDelete != 0) {  // Проверяем, что запись выбрана
                text_deleted_patient.setVisible(true);
                // Удаляем запись
                func_manage_delete_patient.deleteSelectedPatient(patientIdToDelete);
            } else {
                System.out.println("Запись не выбрана.");
            }
        });

    }
}
