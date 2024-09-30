package com.example.medical_records_management_system.folder_calendar;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_data.data_medical_records;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class func_calendar {

    @FXML
    private final func_table_of_records_by_date tableOfRecordsByDate = new func_table_of_records_by_date();

    @FXML
    private TableView<data_medical_records> table_view_calendar;
    @FXML
    public DatePicker date_picker;
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

    public void initialize() {
        ObservableList<data_medical_records> records = tableOfRecordsByDate.initializeTable(table_view_calendar, column_date, column_record, column_patient, column_diagnosis, column_treatment, AppContext.getInstance().getRightPane());


        // Получаем текущую дату при инициализации
        LocalDate currentDate = LocalDate.now();
        // Инициализация таблицы с записями на текущую дату
        loadRecordsForDate(currentDate);

        // Устанавливаем обработчик для обновления таблицы при выборе даты
        date_picker.setOnAction(event -> {
            LocalDate selectedDate = date_picker.getValue();
            if (selectedDate != null) {
                loadRecordsForDate(selectedDate);
            }
        });
    }

    // Метод для загрузки записей по дате
    private void loadRecordsForDate(LocalDate date) {
        ObservableList<data_medical_records> records = tableOfRecordsByDate.getRecordsByDate(date);
        table_view_calendar.setItems(records);
    }
}

