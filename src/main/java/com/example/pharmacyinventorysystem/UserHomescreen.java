package com.example.pharmacyinventorysystem;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class UserHomescreen {

        @FXML
        private void handlePerscriptionAdd() throws IOException {
            switchScene("add-prescription-view.fxml");
        }


        @FXML
        private void handleUserLogout() throws IOException {
            switchScene("main-view.fxml");
        }

}
