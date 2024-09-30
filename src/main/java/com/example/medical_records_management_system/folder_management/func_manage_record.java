package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.AppContext;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class func_manage_record {
    @FXML
    public JFXButton buttonChangeInformation;
    @FXML
    public JFXButton buttonDeleteRecord;

    public void initialize(){

        buttonChangeInformation.setOnMouseClicked(event -> {
            func_open_modify_record openModifyRecord = new func_open_modify_record();
            openModifyRecord.showModifyRecord(AppContext.getInstance().getCenterPane());
        });

    }

}
