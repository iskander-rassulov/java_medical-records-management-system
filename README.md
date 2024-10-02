# Medical Records Management System

## Project Description

**Medical Records Management System** is a Java application for managing electronic medical records. The system allows doctors to manage patient records, including diagnoses, treatment plans, and visits. Patients can schedule appointments and upload their medical documents. The application provides a user-friendly graphical interface using JavaFX.

## Main Features

- **Doctor Authentication**: Login using a username and password.
- **Viewing and Editing Medical Records**: Doctors can manage patient records.
- **Appointment Scheduling**: Patients can book appointments with doctors.
- **Medical Document Management**: Ability to upload and view patients' medical documents.
- **Electronic Prescriptions**: Doctors can issue prescriptions through the system.

## Technologies

The project uses the following technologies:

- **Java**: Programming language for implementing the application's main logic.
- **JavaFX**: Tool for creating the graphical user interface.
- **Maven**: Tool for managing dependencies and building the project.
- **PostgreSQL**: Database for storing information.

## Project Structure

The project is organized into the following main directories and files:

### 1. **`src/main/java`**
   
   - **`controller/`**: Folder containing controllers for managing different screens and application functions.
     - `controller_login.java`: Controller for handling user login.
     - `controller_main.java`: Controller for managing the main interface of the application.
   
   - **`functions/`**: Folder containing the application logic.
     - `func_check_login_data.java`: Logic for verifying login data.
     - `func_open_main_view.java`: Logic for opening the main interface of the program.
     - `func_sign_up.java`: Logic for registering new doctors.
     - `func_record_info.java`: Logic for displaying medical record information.

   - **`models/`**: Folder with classes that handle data.
     - `data_doctor.java`: Model for storing doctor information.
     - `data_patient.java`: Model for storing patient information.
     - `data_medical_record.java`: Model for storing medical records.

### 2. **`src/main/resources`**

   - **`views/`**: Folder containing FXML files describing the graphical interface.
     - `view_login.fxml`: Template for the login screen.
     - `view_main.fxml`: Template for the main interface.
     - `view_sign_up.fxml`: Template for the doctor registration form.

   - **`css/`**: Folder containing style files used for styling the application.
     - `style.css`: Main stylesheet for the application.

### 3. **`src/main/resources/images/`**

   - Folder for storing images used in the interface, such as doctor avatars.

### 4. **`src/test/java`**

   - Folder for tests if needed for testing various project functions.
     
### 5. **`src/main/java/utils/`**

   - Folder for utility classes and helper methods used throughout the application.
     - `database_configuration.java`: Configuration for connecting to the database (if required).
     - `helper_methods.java`: Utility methods for data processing or simplifying operations in other classes.
     - `image_handler.java`: Logic for handling images, such as uploading and displaying doctor avatars.

### 6. **`src/main/java/exceptions/`**

   - Folder for custom exceptions that may arise during application operation.
     - `InvalidLoginException.java`: Exception for handling errors when incorrect login data is entered.
     - `RecordNotFoundException.java`: Exception for handling cases when a medical record is not found.

### 7. **`src/main/resources/logs/`**

   - Folder for storing logs if logging is implemented in the application. Logs may track user activity and errors.
     - `app.log`: Main file for recording application logs.

### 8. **`src/main/resources/config/`**

   - Folder for application configuration files.
     - `app_settings.properties`: File for storing global application settings such as theme, language, and other parameters.
     - `log_config.xml`: Log configuration if a logging library is used (e.g., Log4j).

## Example Project Structure Tree

```bash
java_medical-records-management-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   ├── functions/
│   │   │   ├── models/
│   │   │   ├── utils/
│   │   │   └── exceptions/
│   │   ├── resources/
│   │   │   ├── views/
│   │   │   ├── css/
│   │   │   ├── images/
│   │   │   ├── logs/
│   │   │   └── config/
│   ├── test/
│   │   └── java/
│   │
├── pom.xml (if using Maven)
├── README.md
└── .gitignore

