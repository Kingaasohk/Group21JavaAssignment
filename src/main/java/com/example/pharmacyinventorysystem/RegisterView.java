package com.example.pharmacyinventorysystem;

import com.example.pharmacyinventorysystem.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class RegisterView {
    // UI Components from FXML
    @FXML private TextField firstNameField, lastNameField, emailField;
    @FXML private ChoiceBox<String> genderChoiceBox, roleChoiceBox;
    @FXML private TextArea addressField;
    @FXML private PasswordField passwordField, confirmPasswordField;
    @FXML private Label successMessage, firstNameError, lastNameError, emailError, genderError, addressError, passwordError, confirmPasswordError;
    @FXML private Button registerButton;
    @FXML private Button backButton;
    /**
     * Initializes gender options in the ChoiceBox when the registration form loads.
     */
    @FXML
    public void initialize() {
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");
        genderChoiceBox.setValue(null); // Ensure no default selection
        roleChoiceBox.getItems().addAll("Admin", "Pharmacist");
        roleChoiceBox.setValue(null);
    }

    /**
     * Handles user registration when the Register button is clicked.
     *
     * @return
     */
    @FXML
    private boolean handleRegister() throws SQLException {
        // Retrieve user input and trim unnecessary spaces
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String gender = genderChoiceBox.getValue();
        String password = passwordField.getText();
        String userRole = roleChoiceBox.getValue();
        String confirmPassword = confirmPasswordField.getText();
        String created_at = LocalDateTime.now().toString(); // Timestamp for account creation

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


}
