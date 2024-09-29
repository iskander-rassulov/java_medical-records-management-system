package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_data.data_patients;
import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class func_patient {
    @FXML
    private TableView<data_patients> table_view_patient;
    @FXML
    private TableColumn<data_patients, Integer> column_patient_id;
    @FXML
    private TableColumn<data_patients, Integer> column_record_id;
    @FXML
    private TableColumn<data_patients, String> column_f_name;
    @FXML
    private TableColumn<data_patients, String> column_s_name;
    public TextField search_bar_patients;

    private final func_table_of_patients tableOfPatients = new func_table_of_patients();
    private final func_search_bar_patients searchBarPatients = new func_search_bar_patients();
    private controller_main controllerMain;


    public void initialize() {
        // Инициализация таблицы через новый класс

        // Хранение всех данных
        ObservableList<data_patients> patients = tableOfPatients.initializeTable(table_view_patient, column_patient_id, column_record_id, column_f_name, column_s_name, AppContext.getInstance().getRightPane());

        // Подключаем поисковую строку с инициализированным списком записей
        searchBarPatients.setupSearchBar(search_bar_patients, table_view_patient, patients);
    }

}
