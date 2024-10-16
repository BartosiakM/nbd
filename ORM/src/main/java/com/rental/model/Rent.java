package com.rental.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Rent")
@Access(AccessType.FIELD)
public class Rent extends AbstractEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    @NotNull
    private final Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    @NotNull
    private final Vehicle vehicle;

    @Column(name = "begine_time")
    private final LocalDateTime beginTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "rent_cost")
    private double rentCost = 0.0;

    public Rent(Client client, Vehicle vehicle, LocalDateTime beginTime) {
        this.client = client;
        this.vehicle = vehicle;
        this.beginTime = beginTime != null ? beginTime : LocalDateTime.now();
    }



    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }



    public int getRentDays() {
        if (endTime == null) return 0;
        return (int) java.time.Duration.between(beginTime, endTime).toDays() + 1;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void endRent(LocalDateTime endTime) {
        if (this.endTime != null) {
            throw new IllegalStateException("Rent has already ended");
        }
        if (endTime.isBefore(beginTime)) {
            throw new IllegalArgumentException("End time cannot be before begin time");
        }
        this.endTime = endTime != null ? endTime : LocalDateTime.now();
        this.rentCost = client.applyDiscount(getRentDays() * vehicle.getActualRentalPrice());
    }

    public double getRentCost() {
        return rentCost;
    }
}
