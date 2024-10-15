package com.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("GOLD")
public class GoldClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 4;
    }

    @Override
    public double applyDiscount(double price) {
        return price * 0.95;
    }

    @Override
    public String getTypeInfo() {
        return "Gold 4 5%";
    }
}
