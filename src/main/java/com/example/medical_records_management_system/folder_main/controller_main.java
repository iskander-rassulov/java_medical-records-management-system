package com.example.medical_records_management_system.folder_main;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.func_current_date;
import com.example.medical_records_management_system.func_user_profile;
import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class controller_main {

    func_current_date funcCurrentDate = new func_current_date();
    private data_doctor doctorData;
    private final func_user_profile userProfile = new func_user_profile();

    //Panes
    public AnchorPane bottom_pane;
    public AnchorPane center_pane;
    public AnchorPane left_pane;
    public AnchorPane right_pane;

    //Buttons
    public JFXButton button_search_records;
    public JFXButton button_patient_card;
    public JFXButton button_calendar;
    public JFXButton button_manage_records;
    public JFXButton button_log_out;

    //Time
    public Text text_current_date;

    //Doctor Data
    public TextFlow text_flow_full_name;
    public Text text_full_name;
    public ImageView image_icon;
    public Text text_speciality;


    public void initialize(){
        //Инициализация даты
        funcCurrentDate.setText_current_date(text_current_date);

        button_log_out.setOnAction(event -> {
            func_log_out logOut = new func_log_out();
            Stage stage = (Stage) button_log_out.getScene().getWindow();
            logOut.logOut(stage);
        });

    }

    // Метод для передачи данных о докторе из login
    public void setDoctorData(data_doctor doctorData) {
        this.doctorData = doctorData;
        // Вызов метода обновления профиля из func_user_profile
        userProfile.updateProfile(doctorData, text_full_name, image_icon, text_speciality);
    }



}
