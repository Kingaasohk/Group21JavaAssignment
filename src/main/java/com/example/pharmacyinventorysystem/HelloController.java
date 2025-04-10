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
    }


    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        switchScene("user-login-view.fxml");
    }

    @FXML
    public void registerButton() throws IOException {
    }

    @FXML
    public void handleRegister(ActionEvent event) throws IOException {
        switchScene("register-view.fxml");

    }
}