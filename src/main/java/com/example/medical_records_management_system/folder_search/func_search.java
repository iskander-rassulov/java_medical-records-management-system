package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.data_medical_records;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class func_search {

    public JFXButton button_click_search;
    public TextField search_bar;

    private final func_table_of_records tableOfRecords = new func_table_of_records();
    private final func_search_bar searchBarHandler = new func_search_bar();

    @FXML
    private TableView<data_medical_records> table_view_search;
    @FXML
    private TableColumn<data_medical_records, String> column_date;
    @FXML
    private TableColumn<data_medical_records, Integer> column_record;
    @FXML
    private TableColumn<data_medical_records, String> column_patient;
    @FXML
    private TableColumn<data_medical_records, String> column_diagnosis;
    @FXML
    private TableColumn<data_medical_records, String> column_treatment;

    private ObservableList<data_medical_records> records; // Хранение всех данных

    public void initialize() {
        // Инициализация таблицы через новый класс
        records = tableOfRecords.initializeTable(table_view_search, column_date, column_record, column_patient, column_diagnosis, column_treatment);

        // Подключаем поисковую строку с инициализированным списком записей
        searchBarHandler.setupSearchBar(search_bar, table_view_search, records);
    }
}
