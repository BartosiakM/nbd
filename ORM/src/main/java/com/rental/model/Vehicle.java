package com.rental.model;

public abstract class Vehicle {
    private String plateNumber;
    private int basePrice;
    private boolean archive;

    public Vehicle(String plateNumber, int basePrice) {
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("Plate number must be provided");
        }
        this.plateNumber = plateNumber;
        this.basePrice = basePrice;
        this.archive = false;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("Plate number must be provided");
        }
        this.plateNumber = plateNumber;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public abstract double getActualRentalPrice();

    public String getVehicleInfo() {
        return plateNumber + " " + basePrice;
    }

    public boolean isArchived() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
}
