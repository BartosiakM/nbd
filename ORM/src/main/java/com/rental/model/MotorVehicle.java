package com.rental.model;

public abstract class MotorVehicle extends Vehicle {
    private int engineDisplacement;

    public MotorVehicle(String plateNumber, int basePrice, int engineDisplacement) {
        super(plateNumber, basePrice);
        this.engineDisplacement = engineDisplacement;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public double getActualRentalPrice() {
        double rentalPrice = getBasePrice();
        if (engineDisplacement > 2000) {
            rentalPrice *= 1.5;
        } else if (engineDisplacement > 1000) {
            rentalPrice *= (engineDisplacement * 0.5) / 1000.0 + 0.5;
        }
        return rentalPrice;
    }

    @Override
    public String getVehicleInfo() {
        return super.getVehicleInfo() + " " + engineDisplacement;
    }
}
