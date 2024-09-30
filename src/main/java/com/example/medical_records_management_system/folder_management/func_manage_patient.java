package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.AppContext;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class func_manage_patient {
    @FXML
    public JFXButton buttonChangeInformation;
    @FXML
    public JFXButton buttonDeletePatient;

    public void initialize(){
        buttonChangeInformation.setOnMouseClicked(event ->{
            func_open_modify_patient openModifyPatient = new func_open_modify_patient();
            openModifyPatient.showModifyPatient(AppContext.getInstance().getCenterPane());
        });
    }
}
