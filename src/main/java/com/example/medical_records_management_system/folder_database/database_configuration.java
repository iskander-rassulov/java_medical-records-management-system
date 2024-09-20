package com.example.medical_records_management_system.folder_database;

public class database_configuration {
    // Database configuration parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/hospital";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private static final String PORT = "5432";

    public static String getUrl() {
        return URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getPort(){
        return PORT;
    }
}

