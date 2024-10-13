package com.rental.model;

import jakarta.persistence.*;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "client_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ClientType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract int getMaxVehicles();
    public abstract double applyDiscount(double price);
    public abstract String getTypeInfo();
}

@Entity
@DiscriminatorValue("DEFAULT")
class DefaultClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 1;
    }

    @Override
    public double applyDiscount(double price) {
        return price;
    }

    @Override
    public String getTypeInfo() {
        return "Default 1 $0.00";
    }
}


@Entity
@DiscriminatorValue("SILVER")
class SilverClientType extends ClientType {
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

@Entity
@DiscriminatorValue("GOLD")
class GoldClientType extends ClientType {
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

@Entity
@DiscriminatorValue("PLATINUM")
class PlatinumClientType extends ClientType {
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

@Entity
@DiscriminatorValue("DIAMOND")
class DiamondClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 10;
    }

    @Override
    public double applyDiscount(double price) {
        if (price > 500.0) return 0.6 * price;
        else if (price > 250.0) return 0.7 * price;
        else if (price > 125.0) return 0.8 * price;
        else return 0.9 * price;
    }

    @Override
    public String getTypeInfo() {
        return "Diamond 10 10%/20%/30%/40%";
    }
}
