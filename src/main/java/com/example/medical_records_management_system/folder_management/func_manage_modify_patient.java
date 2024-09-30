package com.example.medical_records_management_system.folder_management;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class func_manage_modify_patient {
    @FXML
    public TextField valFirstName;
    @FXML
    public TextField valDateOfBirth;
    @FXML
    public TextField valSecondName;
    @FXML
    public TextField valGender;
    @FXML
    public TextField valPhoneNumber;
    @FXML
    public TextField valEmail;
    @FXML
    public TextField valAddress;
    @FXML
    public JFXButton buttonSave;
    @FXML
    public Text text_modified_patients;

    public void initialize(){
        
        buttonSave.setOnMouseClicked(even->{


            text_modified_patients.setVisible(true);
        });
        
    }

}
