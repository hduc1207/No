package com.pethotel.dto;

public class PetDTO {
    private int petId;
    private String petName;
    private String type;   // Chó/Mèo
    private String breed;  // Giống
    private double weight;
    private String healthStatus;
    private int customerId; // Khóa ngoại trỏ về chủ

    public PetDTO() {}

    // Constructor đầy đủ
    public PetDTO(int petId, String petName, String type, String breed, double weight, String healthStatus, int customerId) {
        this.petId = petId;
        this.petName = petName;
        this.type = type;
        this.breed = breed;
        this.weight = weight;
        this.healthStatus = healthStatus;
        this.customerId = customerId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}