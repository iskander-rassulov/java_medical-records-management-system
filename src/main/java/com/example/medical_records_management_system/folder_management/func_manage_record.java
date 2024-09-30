package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_search.func_table_of_records;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class func_manage_record {
    @FXML
    public JFXButton buttonChangeInformation;
    @FXML
    public JFXButton buttonDeleteRecord;
    @FXML
    public Text text_deleted_record;


    public void initialize(){

        buttonChangeInformation.setOnMouseClicked(event -> {
            func_open_modify_record openModifyRecord = new func_open_modify_record();
            openModifyRecord.showModifyRecord(AppContext.getInstance().getCenterPane());
        });

        buttonDeleteRecord.setOnMouseClicked(event -> {
            // Получаем выбранный идентификатор записи
            int recordIdToDelete = func_table_of_records.getChosenRecordId();

            if (recordIdToDelete != 0) {  // Проверяем, что запись выбрана
                text_deleted_record.setVisible(true);
                // Удаляем запись
                func_manage_delete_record.deleteSelectedRecord(recordIdToDelete);
            } else {
                System.out.println("Запись не выбрана.");
            }
        });


    }

}
