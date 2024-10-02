# Medical Records Management System

## Project Description
Medical Records Management System is a simplified system for managing electronic medical records. The project enables physicians to store and view information about patients, their diagnoses and appointments. Patients can make medical appointments and upload medical records.

## Technologies
The following technologies are used in the project:
- **Java**: the main programming language for the logic of the application.
- **JavaFX**: for creating user interface.
- **PostgreSQL**: database for storing information about patients, doctors and medical records.

## Main Functions
- Doctors registration and authorisation
- Manage electronic medical records
- Appointment scheduling and management of patient treatment plans

## Video
| Video Demonstration |
|:------------------:|
| ![Screenshot](https://github.com/user-attachments/assets/f80eaf04-1467-4202-999c-2fa812774ba5)|
| [Watch on YouTube](https://www.youtube.com/watch?v=80VjwO4nTTg) |


## Project structure
- **folder_login_page**: includes codes for login page:
  - `controller_login.java`: login and autologin processing.
  - `func_check_login_data.java`: check login and autologin data.
  - `func_open_main_view.java`: switch to the main window.

- **folder_logged_in**: includes codes to manage autologin:
  - `func_keep_logged_in.java`: stores user data for autologin and deletes it if necessary.
  - `func_check_if_logged_in.java`: checks the status of autologin.

- **folder_main**: includes codes for the main application interface:
  - `controller_main.java`: controller of the main application window with functionality to display user data and navigate through sections.
  - `func_current_date.java`: displays the current date in the interface.
  - `func_log_out.java`: controls user logout.
  - `func_user_profile.java`: updates the user profile and displays user data.

- **folder_database**: includes codes to work with the database:
  - `database_check_in.java`: outputs data of all doctors from the database.
  - `database_configuration.java`: contains parameters for database configuration.
  - `database_handler.java`: handles database connections and table interactions.

- **folder_data**: includes data models for the application:
  - `data_doctor.java`: contains information about the doctor (ID, first name, last name, login, password, image, specialty).
  - `data_medical_records.java`: contains information about the medical record (record ID, patient ID, doctor ID, date of visit, diagnosis, treatment plan).
  - `data_patients.java`: contains information about patients (ID, first name, last name, date of birth, gender, contact information, medical record ID).

- **folder_search**: includes codes to search and display medical records:
  - `func_open_record_info.java`: opens and displays information about the selected medical record.
  - `func_open_search_view.java`: loads and displays the search interface.
  - `func_record_info.java`: controls the display of medical record data.
  - `func_search.java`: implements basic record search logic.
  - `func_search_bar.java`: is responsible for the operation of the search bar.
  - `func_table_of_records.java`: displays a list of records in a table and handles record selection.

- **folder_patient**: includes codes for working with patient information:
  - `func_open_patient_info.java`: opens and displays information about the selected patient.
  - `func_open_open_patient_view.java`: loads an interface with a list of patients.
  - `func_patient.java`: responsible for processing patient data.
  - `func_patient_info.java`: controls the display of detailed patient information.
  - `func_search_bar_patients.java`: search bar for patient handling.
  - `func_table_of_patients.java`: displays a list of patients in a table and handles patient selection.

- **folder_calendar**: includes codes to handle calendar and entries by date:
  - `func_calendar.java`: controls the display of entries for the selected date in the calendar.
  - `func_open_calendar_view.java`: opens the calendar interface.
  - `func_table_of_records_by_date.java`: loads and displays medical records on the selected date.

- **folder_make_record**: includes codes to create medical records:
  - `func_make_record.java`: handles the creation of a new medical record with information about the patient, visit date, diagnosis, and treatment plan.
  - `func_open_view_make_record.java`: loads and displays the interface for creating a new record.

- **folder_management**: includes codes for record and patient management:
  - `func_manage_delete_record.java`: deletes the selected medical record and associated patient information.
  - `func_manage_delete_patient.java`: deletes the selected patient and their medical records.
  - `func_manage_modify_patient.java`: manages changes to patient data.
  - `func_manage_modify_record.java`: manages changes to medical records.
  - `func_manage_patient.java`: manages the processing of patient data.
  - `func_manage_manage_record.java`: manages medical record data processing.
  - `func_open_manage_manage_patient.java`: opens the patient management interface.
  - `func_open_open_manage_manage_record.java`: opens the records management interface.
  - `func_open_modify_patient.java`: opens the interface to modify patient data.
  - `func_open_modify_record.java`: opens the interface to modify medical record data.

- **folder_settings**: includes codes to control profile settings:
  - `func_file_selector.java`: implements image selection for a profile via a file selector.
  - `func_open_settings.java`: loads and displays the settings interface.
  - `func_settings.java`: handles updating user data such as username, profile image and password.

- **folder_sign_up**: includes codes for registering new users:
  - `func_open_sign_up.java`: loads and displays the registration interface.
  - `func_open_sign_up_done.java`: displays the registration completion screen.
  - `func_sign_up.java`: handles the registration process, including validating the data and saving the new user.
  - `func_sign_up_done.java`: displays the new user's data after successful registration (name, image).

- **AppContext.java**: Singleton class that stores the overall context of an application. Used to pass data between different parts of the application, such as `controller_main`, right and center panels, and profile data (name, specialty, image).
  
- **Main.java**: The main application class that runs the JavaFX interface. It loads the `view_login_page.fxml` file, sets the scene for the main window, and runs the basic validation of doctor data.
