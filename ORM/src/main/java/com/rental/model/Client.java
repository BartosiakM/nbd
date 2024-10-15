package com.rental.model;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import jakarta.validation.Valid;


import java.util.UUID;

@Entity
@Valid
@Table(name = "Client")
@Access(AccessType.FIELD)
public class Client extends AbstractEntity {

    @Column(name = "username")
    @NotNull
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Address_ID", referencedColumnName = "id")
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ClientType_ID", referencedColumnName = "id")
    private ClientType clientType;

    @Column
    private int activeRents;

    public Client(String username, ClientType clientType, Address address) {
        this.username = username;
        this.clientType = clientType;
        this.address = address;
        this.activeRents = 0;
    }

    public Client() {

    }

    public int getMaxVehicles() {
        return clientType.getMaxVehicles();
    }

    public double applyDiscount(double price) {
        return clientType.applyDiscount(price);
    }

}
