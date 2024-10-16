package com.rental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public abstract class Vehicle extends AbstractEntity implements Serializable {

    @Column(name = "plateNumber")
    @NotNull
    private String plateNumber;

    @Column(name = "basePrice")
    private int basePrice;

    @Column(name = "archived")
    private boolean archive;

    public Vehicle(String plateNumber, int basePrice) {
        this.plateNumber = plateNumber;
        this.basePrice = basePrice;
        this.archive = false;
    }

    public UUID getVehicleId() {
        return this.id;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public abstract double getActualRentalPrice();

    public boolean isArchived() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
}
