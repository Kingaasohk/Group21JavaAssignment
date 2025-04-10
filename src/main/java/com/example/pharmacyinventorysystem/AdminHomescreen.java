package com.example.pharmacyinventorysystem;
import com.example.pharmacyinventorysystem.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;
public class AdminHomescreen {
    @FXML private TextField drugName, drugAmount;
    @FXML private TextArea drugDescription;
    @FXML private Button backButton, addButton;
    @FXML private Label drugErrorMessage, amountErrorMessage, descriptionErrorMessage;

    @FXML
    private void handleStockAdd() throws SQLException {
        // clear errors upon retry
        clearErrorMessages();
    }


    private void clearErrorMessages() {}

    @FXML
    private void handleBackToGreeter() throws IOException {
        switchScene("main-view.fxml");
    }
}