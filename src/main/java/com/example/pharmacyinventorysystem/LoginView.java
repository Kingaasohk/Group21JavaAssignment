package com.example.pharmacyinventorysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.example.pharmacyinventorysystem.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class LoginView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    private void handleLogin() throws IOException {
        String email = usernameField.getText();
        String password = passwordField.getText();
        String userRole = isValidUser(email, password);
        // Get the user input
        if (userRole != null) {
            if (userRole.equals("Pharmacist")) {
                System.out.println("Valid username and password");
                switchScene("user-homescreen.fxml");
                // switch to the userscreen if the login is valid
                }
            else if (userRole.equals("Admin")) {
                System.out.println("Valid Admin login");
                switchScene("admin-homescreen.fxml");
            }
        }else {
            System.out.println("Invalid username or password");
            errorMessage.setText("Password  or E-mail incorrect, try again!");
            // inform user they have the incorrect password
        }


    }

    // function to check data base records to run against user input.
    private String isValidUser(String email, String password) {
        String sql = "SELECT role FROM users WHERE email = ? AND password = ?";
        try( Connection conn = Database.connect();
              PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, email);
            pStatement.setString(2, password);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()){
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @FXML
    private void handleBackToGreeter() throws IOException {
        switchScene("main-view.fxml");
    }
    // send user back to main menu
}
