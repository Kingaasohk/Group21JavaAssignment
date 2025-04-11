package com.example.pharmacyinventorysystem;

import com.example.pharmacyinventorysystem.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

import static com.example.pharmacyinventorysystem.HelloApplication.switchScene;

public class UserHomescreen {

    @FXML
    private TableView<Prescription> prescriptionTable;

    @FXML
    private TableColumn<Prescription, Integer> idCol;
    @FXML
    private TableColumn<Prescription, Integer> drugIdCol;
    @FXML
    private TableColumn<Prescription, String> firstNameCol;
    @FXML
    private TableColumn<Prescription, String> lastNameCol;
    @FXML
    private TableColumn<Prescription, String> emailCol;
    @FXML
    private TableColumn<Prescription, String> genderCol;
    @FXML
    private TableColumn<Prescription, String> medicalConditionCol;
    @FXML
    private TableColumn<Prescription, LocalDateTime> createdAtCol;

    private final ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        drugIdCol.setCellValueFactory(new PropertyValueFactory<>("drugId"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        medicalConditionCol.setCellValueFactory(new PropertyValueFactory<>("medicalCondition"));
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        loadDataFromDatabase();
    }



    private void loadDataFromDatabase() {
        String query = "SELECT * FROM prescriptions";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String createdAtStr = rs.getString("created_at");
                LocalDateTime createdAt = LocalDateTime.parse(createdAtStr); // ISO 8601 support

                Prescription prescription = new Prescription(
                        rs.getInt("id"),
                        rs.getInt("drug_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("medical_condition"),
                        createdAt
                );
                prescriptionList.add(prescription);
            }

            prescriptionTable.setItems(prescriptionList);

        } catch (Exception e) {
            System.out.println("Error loading prescriptions: " + e.getMessage());
        }
    }




    @FXML
    private void handlePerscriptionAdd() throws IOException {
        switchScene("add-prescription-view.fxml");
    }

    @FXML
    private void handleUserLogout() throws IOException {
        switchScene("main-view.fxml");
    }
}
