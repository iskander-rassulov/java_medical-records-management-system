package com.example.medical_records_management_system;

import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.scene.layout.AnchorPane;

public class AppContext {
    private static AppContext instance = null;
    private controller_main mainController;
    private AnchorPane rightPane;
    private AnchorPane centerPane;

    // Приватный конструктор, чтобы обеспечить Singleton
    private AppContext() {}

    // Получаем единственный экземпляр com.example.medical_records_management_system.AppContext
    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    // Метод для установки controller_main
    public void setMainController(controller_main mainController) {
        this.mainController = mainController;
    }

    // Метод для получения controller_main
    public controller_main getMainController() {
        return mainController;
    }

    // Метод для установки right_pane
    public void setRightPane(AnchorPane rightPane) {
        this.rightPane = rightPane;
    }

    // Метод для получения right_pane
    public AnchorPane getRightPane() {
        return rightPane;
    }

    public void setCenterPane(AnchorPane centerPane) {
        this.centerPane = centerPane; // Add the new pane
    }

    public AnchorPane getCenterPane(){
        return centerPane;
    }

}

