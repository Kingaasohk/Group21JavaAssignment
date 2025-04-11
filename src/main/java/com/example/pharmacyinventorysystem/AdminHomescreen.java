package com.example.pharmacyinventorysystem;
import com.example.pharmacyinventorysystem.database.Database;
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
    @FXML private TextField amountField, drugNameField;
    @FXML private TextArea drugDescriptionField;
    @FXML private Button backButton, addButton;
    @FXML private Label drugErrorMessage, amountErrorMessage, descriptionErrorMessage, successMessage;

    @FXML
    private void handleStockAdd() throws SQLException {
        // clear errors upon retry
        clearErrorMessages();
        String drugName = drugNameField.getText().trim();
        String description = drugDescriptionField.getText().trim();
        String amount = amountField.getText().trim();
        boolean valid = true;
        if(drugName.isEmpty()){
            drugErrorMessage.setText("Drug name required!");
            valid = false;
        }
        if (amount.isEmpty()){
            amountErrorMessage.setText("Amount required!");
            valid = false;
        }
        if (description.isEmpty()){
            descriptionErrorMessage.setText("Description required!");
            valid = false;
        }
        if (!isValidInteger(amount)){
            amountErrorMessage.setText("Invalid amount!");
            valid = false;
        }
        if (!valid) return;

        if (addStock(drugName, description, amount)){
            successMessage.setText("Successfully added stock");
            clearFields();
        }
        else{
            successMessage.setText("Failed to add stock");
        }

    }

    private boolean addStock(String drugName, String description, String amount) {
        String sql = "INSERT INTO inventory(drug_name, description, stock, created_at)" +
        "VALUES(?, ?, ?, ?)" ;

        try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql))
              {
                  pstmt.setString(1, drugName);
                  pstmt.setString(2, description);
                  pstmt.setString(3, amount);
                  pstmt.setString(4, LocalDateTime.now().toString());
                  return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public void clearFields(){
        drugNameField.clear();
        drugDescriptionField.clear();
        amountField.clear();
    }
    private void clearErrorMessages() {
        drugErrorMessage.setText("");
        amountErrorMessage.setText("");
        descriptionErrorMessage.setText("");
    }

    @FXML
    private void handleBackToGreeter() throws IOException {
        switchScene("main-view.fxml");
    }
    private boolean isValidInteger(String amount) {
        // Checks if the input contains only digits (positive whole number)
        String integerPattern = "^\\d+$";
        return Pattern.matches(integerPattern, amount);
    }
}