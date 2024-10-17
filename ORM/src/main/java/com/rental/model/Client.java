package com.rental.model;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import jakarta.validation.Valid;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Client")
@Access(AccessType.FIELD)
public class  Client extends AbstractEntity implements Serializable {

    @Column(name = "username")
    @NotNull
    private String username;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_type_id", referencedColumnName = "id")
    private ClientType clientType;

    @Column
    private int activeRents;

    public Client(String username, ClientType clientType) {
        this.username = username;
        this.clientType = clientType;
        this.activeRents = 0;
    }

    public Client() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public Long getClientId() {
        return this.id;
    }

    public int getMaxVehicles() {
        return clientType.getMaxVehicles();
    }

    public double applyDiscount(double price) {
        return clientType.applyDiscount(price);
    }

}
