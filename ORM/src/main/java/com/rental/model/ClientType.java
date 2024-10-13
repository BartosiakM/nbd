package com.rental.model;

public abstract class ClientType {
    public abstract int getMaxVehicles();
    public abstract double applyDiscount(double price);
    public abstract String getTypeInfo();
}

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

class BronzeClientType extends ClientType {
    @Override
    public int getMaxVehicles() {
        return 2;
    }

    @Override
    public double applyDiscount(double price) {
        double discount = 3.0;
        return (price > discount) ? price - discount : price;
    }

    @Override
    public String getTypeInfo() {
        return "Bronze 2 $3.00";
    }
}

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
