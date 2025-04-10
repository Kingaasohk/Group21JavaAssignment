package com.example.pharmacyinventorysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent; // ✅ Correct import

import java.io.IOException;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class HelloController {

    @FXML
    private Label appTitle; // ❗ This should be a field, not a method

    @FXML
    protected void userLoginButton() {
        // optional logic here
    }

    @FXML
    protected void adminLoginButton() {
        // optional logic here
    }

    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        switchScene("user-login-view.fxml");
    }

    @FXML
    public void handleAdminLogin(ActionEvent event) throws IOException {
        switchScene("admin-login-view.fxml");
    }

    @FXML
    public void registerButton() throws IOException {
        switchScene("register-view");
    }

    @FXML
    public void handleRegister(ActionEvent event) throws IOException {
        switchScene("register-view.fxml");

    }
}