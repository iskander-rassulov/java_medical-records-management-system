package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_search.func_table_of_records;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class func_manage_modify_record {

    @FXML
    public TextField valDiagnosis;
    @FXML
    public TextArea valTreatmentPlan;
    @FXML
    public JFXButton buttonSave;
    @FXML
    public Text text_modified_record;
    @FXML
    public DatePicker valVisitDate;

    public void initialize() {
        buttonSave.setOnMouseClicked(event -> {
            int chosenRecordId = func_table_of_records.getChosenRecordId();

            // Получаем данные из текстовых полей
            LocalDate visitDateStr = valVisitDate.getValue();
            String diagnosis = valDiagnosis.getText();
            String treatmentPlan = valTreatmentPlan.getText();

            // Проверяем, что идентификатор выбранной записи не равен 0
            if (chosenRecordId != 0) {
                updateRecord(chosenRecordId, visitDateStr, diagnosis, treatmentPlan);
                text_modified_record.setVisible(true);  // Показываем текст после успешного изменения
            } else {
                System.out.println("Запись не выбрана.");
            }
        });
    }

    private void updateRecord(int recordId, LocalDate visitDateStr, String diagnosis, String treatmentPlan) {
        StringBuilder updateSQL = new StringBuilder("UPDATE medical_records SET ");
        boolean firstField = true;

        if (visitDateStr != null) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("visit_date = ?");
            firstField = false;
        }
        if (diagnosis != null && !diagnosis.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("diagnosis = ?");
            firstField = false;
        }
        if (treatmentPlan != null && !treatmentPlan.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("treatment_plan = ?");
        }

        updateSQL.append(" WHERE medical_record_id = ?");

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL.toString())) {

            int paramIndex = 1;

            if (visitDateStr != null) {
                preparedStatement.setDate(paramIndex++, java.sql.Date.valueOf(visitDateStr)); // Преобразуем LocalDate в SQL Date
            }
            if (diagnosis != null && !diagnosis.isEmpty()) {
                preparedStatement.setString(paramIndex++, diagnosis);
            }
            if (treatmentPlan != null && !treatmentPlan.isEmpty()) {
                preparedStatement.setString(paramIndex++, treatmentPlan);
            }

            preparedStatement.setInt(paramIndex, recordId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Запись успешно обновлена.");
            } else {
                System.out.println("Не удалось обновить запись.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = format.parse(dateString);
        return new java.sql.Date(parsedDate.getTime());
    }


}
