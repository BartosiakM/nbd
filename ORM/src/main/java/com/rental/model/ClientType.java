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
