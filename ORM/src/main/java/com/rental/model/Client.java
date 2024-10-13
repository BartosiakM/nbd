package com.rental.model;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import jakarta.validation.Valid;


import java.util.UUID;

@Entity
@Valid
@Table(name = "Client")
@Access(AccessType.FIELD)
public class Client extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "Client_ID")
    private UUID ID;

    @Column(nullable = false, unique = true)
    @NotNull
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Address_ID")
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ClientType_ID")
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
