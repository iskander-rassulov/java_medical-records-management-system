package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.folder_database.database_handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class func_manage_delete_patient {

    public static void deleteSelectedPatient(int patientId) {
        String deletePatientSQL = "DELETE FROM patients WHERE patient_id = ?";
        String deleteRecordSQL = "DELETE FROM medical_records WHERE patient_id = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement patientStmt = connection.prepareStatement(deletePatientSQL);
             PreparedStatement recordStmt = connection.prepareStatement(deleteRecordSQL)) {

            // Удаляем связанные записи в таблице patients
            patientStmt.setInt(1, patientId);
            patientStmt.executeUpdate();

            // Удаляем запись из таблицы medical_records
            recordStmt.setInt(1, patientId);
            int affectedRows = recordStmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Запись успешно удалена.");
            } else {
                System.out.println("Запись не найдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
