package com.example.medical_records_management_system.folder_logged_in;

import com.example.medical_records_management_system.data_doctor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class func_keep_logged_in {

    // Метод для сохранения данных пользователя (он уже есть)
    public static void saveUserData(data_doctor doctorData, boolean keepLoggedIn) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("user_data.txt"))) {
            writer.println("username=" + doctorData.getUsername());
            writer.println("password=" + doctorData.getPassword());
            writer.println("keep_logged_in=" + keepLoggedIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для "забвения" данных пользователя
    public static void forgetUserData() {
        File file = new File("user_data.txt");
        if (file.exists()) {
            file.delete();  // Удаляем файл с данными пользователя
        }
    }
}

