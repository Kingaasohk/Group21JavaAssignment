package com.example.pharmacyinventorysystem;

import com.example.pharmacyinventorysystem.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class AddPrescriptionView {
    // UI Components from FXML
    @FXML private TextField firstNameField, lastNameField, emailField;
    @FXML private ChoiceBox<String> genderChoiceBox;
    @FXML private ChoiceBox<String> drugChoiceBox;
    @FXML private TextArea medicalCondition;
    @FXML private Label successMessage, firstNameError, lastNameError, emailError, genderError;
    @FXML private Button registerButton;
    @FXML private Button backButton;
    /**
     * Initializes gender options in the ChoiceBox when the registration form loads.
     */
    @FXML
    public void initialize() throws SQLException {
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");
        genderChoiceBox.setValue(null); // make the selection box have no default
        loadDrugsIntoChoiceBox();
    }

    private void loadDrugsIntoChoiceBox() throws SQLException {
        String sql = "SELECT id FROM inventory WHERE stock > 0";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                int drug_id = rs.getInt("id");
                drugChoiceBox.getItems().add(String.valueOf(drug_id));
            }
        }
    }


    /**
     * Handles user registration when the Register button is clicked.
     */
    @FXML
    private void handleRegister() throws SQLException {

        // clear errors upon retry
        clearErrorMessages();

        // Retrieve user input and trim unnecessary spaces
        int drug_id = Integer.parseInt(drugChoiceBox.getValue());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String gender = genderChoiceBox.getValue();
        String medical_condition = medicalCondition.getText().trim();
        String created_at = LocalDateTime.now().toString(); // Timestamp for account creation
        boolean valid = true;

        if (firstName.isEmpty()) {
            firstNameError.setText("First Name is required");
            valid = false;
        }
        if (lastName.isEmpty()) {
            lastNameError.setText("Last name is required.");
            valid = false;
        }
        if (!isValidEmail(email)) {
            emailError.setText("Invalid email format.");
            valid = false;
        }

        if (gender == null) {
            genderError.setText("Select gender.");
            valid = false;
        }
        if (!valid) return;

        if (registerUser(drug_id,firstName, lastName, email, gender,medical_condition,created_at)) {
            successMessage.setText("Prescription added successfully.");
            clearFields();
        }else {
            successMessage.setText("User registration failed.");
        }
    }
    private boolean registerUser(int drug_id,String firstName, String lastName, String email, String gender,String medical_condition, String created_at) {


        // SQL statement to insert user details into the database
        String sql = "INSERT INTO prescriptions(drug_id,first_name, last_name, email, gender, medical_condition, created_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drug_id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, gender);
            pstmt.setString(6, medical_condition);
            pstmt.setString(7, created_at);
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    @FXML
    private void handleBackToMain() throws Exception {
        switchScene("user-homescreen.fxml");
    }

    private boolean isValidEmail(String email) {
        // regex email validation
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$";
        return Pattern.matches(emailPattern, email);
    }



    public void clearFields(){
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        genderChoiceBox.setValue(null);

    }
    private void clearErrorMessages() {
        firstNameError.setText("");
        lastNameError.setText("");
        emailError.setText("");
        genderError.setText("");

    }

}
