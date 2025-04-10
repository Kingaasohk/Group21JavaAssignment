package com.example.pharmacyinventorysystem;

import com.example.pharmacyinventorysystem.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class RegisterView {
    // UI Components from FXML
    @FXML private TextField firstNameField, lastNameField, emailField;
    @FXML private ChoiceBox<String> genderChoiceBox, roleChoiceBox;
    @FXML private TextArea addressField;
    @FXML private PasswordField passwordField, confirmPasswordField;
    @FXML private Label successMessage, firstNameError, lastNameError, emailError, genderError, passwordError, confirmPasswordError, roleError;
    @FXML private Button registerButton;
    @FXML private Button backButton;
    /**
     * Initializes gender options in the ChoiceBox when the registration form loads.
     */
    @FXML
    public void initialize() {
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");
        genderChoiceBox.setValue(null); // make the selection box have no default
        roleChoiceBox.getItems().addAll("Admin", "Pharmacist");
        roleChoiceBox.setValue(null);
    }

    /**
     * Handles user registration when the Register button is clicked.
     */
    @FXML
    private void handleRegister() throws SQLException {

        // clear errors upon retry
        clearErrorMessages();

        // Retrieve user input and trim unnecessary spaces
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String gender = genderChoiceBox.getValue();
        String password = passwordField.getText();
        String userRole = roleChoiceBox.getValue();
        String confirmPassword = confirmPasswordField.getText();
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
        if (userRole == null) {
            roleError.setText("Select role.");
            valid = false;
        }
        if (password.isEmpty()) {
            passwordError.setText("Password is required.");
            valid = false;
        } else if (!isValidPassword(password)) {
            passwordError.setText("Password must be 8+ chars, 1 uppercase, 1 number, 1 special char.");
            valid = false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordError.setText("Passwords do not match.");
            valid = false;
        }
        if (!valid) return;

        if (registerUser(firstName, lastName, email, gender, password, userRole, created_at)) {
            successMessage.setText("User registered successfully.");
            clearFields();
        }else {
            successMessage.setText("User registration failed.");
        }
    }
    private boolean registerUser(String firstName, String lastName, String email, String gender, String password, String userRole, String created_at) {


        // SQL statement to insert user details into the database
        String sql = "INSERT INTO users(first_name, last_name, email, gender, password, role, created_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, gender);
            pstmt.setString(5, password);
            pstmt.setString(6, userRole);
            pstmt.setString(7, created_at);
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    @FXML
    private void handleBackToGreeter() throws Exception {
        switchScene("main-view.fxml");
    }

    private boolean isValidEmail(String email) {
        // regex email validation
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$";
        return Pattern.matches(emailPattern, email);
    }

    /**
     * Validates if a password meets security requirements.
     */
    private boolean isValidPassword(String password) {
        //  8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special character
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    public void clearFields(){
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        addressField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
    private void clearErrorMessages() {
        firstNameError.setText("");
        lastNameError.setText("");
        emailError.setText("");
        genderError.setText("");
        roleError.setText("");
        passwordError.setText("");
        confirmPasswordError.setText("");
    }

}
