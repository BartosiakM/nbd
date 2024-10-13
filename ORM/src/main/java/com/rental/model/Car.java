package com.rental.model;

// Car.java
public class Car extends MotorVehicle {
    public enum SegmentType { A, B, C, D, E }

    private SegmentType segment;

    public Car(String plateNumber, int basePrice, int engineDisplacement, SegmentType segment) {
        super(plateNumber, basePrice, engineDisplacement);
        this.segment = segment;
    }

    public SegmentType getSegment() {
        return segment;
    }

    public void setSegment(SegmentType segment) {
        this.segment = segment;
    }

    @Override
    public double getActualRentalPrice() {
        return getBasePrice() * (segment.ordinal() + 1) / 10.0;
    }

    @Override
    public String getVehicleInfo() {
        return super.getVehicleInfo() + " " + segment;
    }
}
