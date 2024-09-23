package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.data_medical_records;
import com.example.medical_records_management_system.data_patients;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class func_search_bar_patients {

    public void setupSearchBar(TextField searchBar, TableView<data_patients> tableView, ObservableList<data_patients> patients) {
        // Добавляем слушатель изменений текста в поисковом поле
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Если поле не пустое, фильтруем данные
                if (newValue != null && !newValue.isEmpty()) {
                    ObservableList<data_patients> filteredRecords = FXCollections.observableArrayList();
                    for (data_patients patient : patients) {
                        // Фильтрация по любым полям
                        if (String.valueOf(patient.getPatientId()).contains(newValue.toLowerCase()) ||
                                patient.getFirstName().toLowerCase().contains(newValue.toLowerCase()) ||
                                patient.getSecondName().toLowerCase().contains(newValue.toLowerCase()) ||
                                String.valueOf(patient.getMedicalRecordId()).contains(newValue.toLowerCase())) {
                            filteredRecords.add(patient);
                        }
                    }
                    tableView.setItems(filteredRecords);
                } else {
                    // Если поле пустое, показываем все записи
                    tableView.setItems(patients);
                }
            }
        });
    }

}
