package com.rental.model;

// Bicycle.java
public class Bicycle extends Vehicle {

    public Bicycle(String plateNumber, int basePrice) {
        super(plateNumber, basePrice);
    }

    @Override
    public double getActualRentalPrice() {
        return getBasePrice();
    }
}
