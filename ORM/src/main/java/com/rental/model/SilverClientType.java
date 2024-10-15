package com.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("SILVER")
public class SilverClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 3;
    }

    @Override
    public double applyDiscount(double price) {
        double discount = 6.0;
        return (price > discount) ? price - discount : price;
    }

    @Override
    public String getTypeInfo() {
        return "Silver 3 $6.00";
    }
}
