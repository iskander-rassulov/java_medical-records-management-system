package com.example.medical_records_management_system;

import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class AppContext {
    private static AppContext instance = null;
    private controller_main mainController;
    private AnchorPane rightPane;
    private AnchorPane centerPane;

    private ImageView imageView;
    private Text fullName;
    private Text speciality;

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

    public void setImageIcon(ImageView imageIcon){
        this.imageView = imageIcon;
    }

    public ImageView getImageIcon(){
        return imageView;
    }

    public void setTextFullName(Text fullName){
        this.fullName = fullName;
    }

    public Text getFullName(){
        return fullName;
    }

    public void setTextSpeciality(Text speciality){
        this.speciality = speciality;
    }

    public Text getSpeciality(){
        return speciality;
    }

}

