package com.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("PLATINUM")
public class PlatinumClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 5;
    }

    @Override
    public double applyDiscount(double price) {
        return price * 0.9;
    }

    @Override
    public String getTypeInfo() {
        return "Platinum 5 10%";
    }
}
