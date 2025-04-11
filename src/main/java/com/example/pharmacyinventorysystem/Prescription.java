package com.example.pharmacyinventorysystem;

import java.time.LocalDateTime;

public class Prescription {
    private int id;
    private int drugId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String medicalCondition;
    private LocalDateTime createdAt;

    public Prescription(int id, int drugId, String firstName, String lastName, String email, String gender, String medicalCondition, LocalDateTime createdAt) {
        this.id = id;
        this.drugId = drugId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getDrugId() { return drugId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getMedicalCondition() { return medicalCondition; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
