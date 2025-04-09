//RegisterController.java
//        package com.example. pharmacyinventorysystem;
//import com.example.pharmacyinventorysystem.database.Database;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//public class RegisterController {
//    // UI Components from FXML
//    @FXML private TextField firstNameField, lastNameField, emailField, phoneNumberField, dpNumberField; @FXML private ChoiceBox&lt;String&gt; genderChoiceBox;
//    @FXML private TextArea addressField;
//    @FXML private PasswordField passwordField, confirmPasswordField;
//    @FXML private Label successMessage, firstNameError, LastNameError, emailError, phoneNumberError, dpNumberError, genderError, addressError, passwordError, confirmPasswordError;
//}
///**
// * Initializes gender options in the ChoiceBox when the registration form loads.
// */
//@FXML
//public void initialize(){
//    genderChoiceBox.getItems().addAll(...es: &quot;Male&quot;, &quot;Female&quot;); // Add gender options genderChoiceBox.setValue(null); // Ensure no default selection
//}
///**
// *
// Handles user registration when the Register button is clicked.
// */
//@FXML
//private void handleRegister() {
////
//    Retrieve user input and trim unnecessary spaces String firstName = firstNameField.getText().trim();
//    String lastName = lastNameField.getText().trim();
//    String email = emailField.getText().trim();
//    String phoneNumber = phoneNumberField.getText().trim();
//    String dpNumber = dpNumberField.getText().trim();
//    String gender = genderChoiceBox.getValue();
//    String address = addressField.getText().trim();
//    String password = passwordField.getText();
//    String confirmPassword = confirm PasswordField.getText();
//    String userRole = &quot;Guest&quot;; //▼ Default user role
//    String createdAt = LocalDateTime.now().toString(); //▼ Timestamp for account creation
//// SQL statement to insert user details into the database
//    String sql = &quot;INSERT INTO users (first_name, last_name, email, phone_number, dp_number, gender, address, password, role, created_at) &quot; + &quot;VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)&quot;;
//// Connect to the database and execute the insert query
//    try (Connection conn = Database.connect();
//         PreparedStatement pstmt = conn.prepareStatement(sql)) {
//// Assign values to the SQL query placeholders
//        pstmt.setString( parameterIndex: 1, firstName); pstmt.setString( parameterIndex: 2, LastName);
//        pstmt.setString( parameterIndex: 3, email);
//        pstmt.setString( parameterIndex: 4, phoneNumber);
//        pstmt.setString( parameterIndex: 5, dpNumber);
//        pstmt.setString( parameterIndex: 6, gender);
//        pstmt.setString( parameterIndex: 7, address);
//        pstmt.setString( parameterIndex: 8, password); // A Password is stored in plaintext (will be updated with hashing)