package com.example.pharmacyinventorysystem;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class LoginView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {}

    @FXML
    private void handleBackToGreeter() throws IOException {
        switchScene("main-view.fxml");
    }
}
