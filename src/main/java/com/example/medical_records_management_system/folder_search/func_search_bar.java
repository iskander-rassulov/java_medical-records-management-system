package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.folder_data.data_medical_records;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class func_search_bar {

    public void setupSearchBar(TextField searchBar, TableView<data_medical_records> tableView, ObservableList<data_medical_records> records) {
        // Добавляем слушатель изменений текста в поисковом поле
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Если поле не пустое, фильтруем данные
                if (newValue != null && !newValue.isEmpty()) {
                    ObservableList<data_medical_records> filteredRecords = FXCollections.observableArrayList();
                    for (data_medical_records record : records) {
                        // Фильтрация по любым полям
                        if (String.valueOf(record.getPatientId()).contains(newValue.toLowerCase()) ||
                                record.getDiagnosis().toLowerCase().contains(newValue.toLowerCase()) ||
                                record.getTreatmentPlan().toLowerCase().contains(newValue.toLowerCase()) ||
                                record.getVisitDate().toLowerCase().contains(newValue.toLowerCase())) {
                            filteredRecords.add(record);
                        }
                    }
                    tableView.setItems(filteredRecords);
                } else {
                    // Если поле пустое, показываем все записи
                    tableView.setItems(records);
                }
            }
        });
    }
}

