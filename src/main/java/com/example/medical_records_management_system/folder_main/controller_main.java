package com.example.medical_records_management_system.folder_main;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.folder_calendar.func_open_calendar_view;
import com.example.medical_records_management_system.folder_make_record.func_open_view_make_record;
import com.example.medical_records_management_system.folder_patient.func_open_patient_view;
import com.example.medical_records_management_system.folder_search.func_open_search_view;
import com.example.medical_records_management_system.func_current_date;
import com.example.medical_records_management_system.func_user_profile;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
    @FXML
    public AnchorPane bottom_pane;
    @FXML
    public AnchorPane center_pane;
    @FXML
    public AnchorPane left_pane;
    @FXML
    public AnchorPane right_pane;

    //Buttons
    public JFXButton button_search_records;
    public JFXButton button_patient_card;
    public JFXButton button_calendar;
    public JFXButton button_log_out;
    public JFXButton button_setting;
    public JFXButton button_make_record;


    //Time
    public Text text_current_date;

    //Doctor Data
    public TextFlow text_flow_full_name;
    public Text text_full_name;
    public ImageView image_icon;
    public Text text_speciality;


    public void initialize(){

        // Устанавливаем controller_main и right_pane в com.example.medical_records_management_system.AppContext
        AppContext.getInstance().setMainController(this);
        AppContext.getInstance().setRightPane(right_pane);


        System.out.println("right_pane: " + right_pane);  // Добавьте это для проверки
        if (right_pane == null) {
            System.out.println("right_pane is not initialized");
        }

        //Инициализация даты
        funcCurrentDate.setText_current_date(text_current_date);

        button_log_out.setOnAction(event -> {
            func_log_out logOut = new func_log_out();
            Stage stage = (Stage) button_log_out.getScene().getWindow();
            logOut.logOut(stage);
        });

        // Привязываем действие к кнопке календаря
        button_calendar.setOnAction(event -> {
            func_open_calendar_view openCalendarView = new func_open_calendar_view();
            openCalendarView.showCalendar(center_pane);  // Передаем center_pane
        });

        // Привязываем действие к кнопке search
        button_search_records.setOnAction(event -> {
            func_open_search_view openSearchView = new func_open_search_view();
            openSearchView.showSearch(center_pane);  // Передаем center_pane
        });

        // Привязываем действие к кнопке patient
        button_patient_card.setOnAction(event -> {
            func_open_patient_view openPatients = new func_open_patient_view();
            openPatients.showPatients(center_pane); // Передаем center_pane
        });

        button_make_record.setOnAction(event ->{
            func_open_view_make_record openMake = new func_open_view_make_record();
            openMake.showMakeRecord(center_pane);
        });

    }

    // Метод для передачи данных о докторе из login
    public void setDoctorData(data_doctor doctorData) {
        this.doctorData = doctorData;
        // Вызов метода обновления профиля из func_user_profile
        userProfile.updateProfile(doctorData, text_full_name, image_icon, text_speciality);

    }


}
