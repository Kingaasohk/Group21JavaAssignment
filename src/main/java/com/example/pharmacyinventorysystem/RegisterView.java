package com.example.pharmacyinventorysystem;

import com.example.pharmacyinventorysystem.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RegisterView {
    // UI Components from FXML
    @FXML private TextField firstNameField, lastNameField, emailField, phoneNumberField, dpNumberField;
    @FXML private ChoiceBox<String> genderChoiceBox;
    @FXML private TextArea addressField;
    @FXML private PasswordField passwordField, confirmPasswordField;
    @FXML private Label successMessage, firstNameError, lastNameError, emailError, genderError, addressError, passwordError, confirmPasswordError;

    /**
     * Initializes gender options in the ChoiceBox when the registration form loads.
     */
    @FXML
    public void initialize() {
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");
        genderChoiceBox.setValue(null); // Ensure no default selection
    }

    /**
     * Handles user registration when the Register button is clicked.
     */
    @FXML
    private void handleRegister() throws SQLException {
        // Retrieve user input and trim unnecessary spaces
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String dpNumber = dpNumberField.getText().trim();
        String gender = genderChoiceBox.getValue();
        String address = addressField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String userRole = "Guest"; // Default user role
        String createdAt = LocalDateTime.now().toString(); // Timestamp for account creation

        // SQL statement to insert user details into the database
        String sql = "INSERT INTO users(first_name, last_name, email, phone_number, dp_number, gender, address, password, role, created_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, dpNumber);
            pstmt.setString(6, gender);
            pstmt.setString(7, address);
            pstmt.setString(8, password);
            pstmt.setString(9, userRole);
            pstmt.setString(10, createdAt);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
