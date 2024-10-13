//package com.rental.model;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//public class Rent {
//    private final String id;
//    private final Client client;
//    private final Vehicle vehicle;
//    private final LocalDateTime beginTime;
//    private LocalDateTime endTime;
//    private double rentCost = 0.0;
//
//    public Rent(Client client, Vehicle vehicle, LocalDateTime beginTime) {
//        this.id = UUID.randomUUID().toString();
//        this.client = client;
//        this.vehicle = vehicle;
//        this.beginTime = beginTime != null ? beginTime : LocalDateTime.now();
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public String getRentInfo() {
//        String info = id + " " + client.getClientInfo() + " " + vehicle.getVehicleInfo() + " " + beginTime;
//        if (endTime != null) {
//            info += " " + endTime;
//        }
//        return info;
//    }
//
//    public int getRentDays() {
//        if (endTime == null) return 0;
//        return (int) java.time.Duration.between(beginTime, endTime).toDays() + 1;
//    }
//
//    public LocalDateTime getBeginTime() {
//        return beginTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void endRent(LocalDateTime endTime) {
//        if (this.endTime != null) {
//            throw new IllegalStateException("Rent has already ended");
//        }
//        if (endTime.isBefore(beginTime)) {
//            throw new IllegalArgumentException("End time cannot be before begin time");
//        }
//        this.endTime = endTime != null ? endTime : LocalDateTime.now();
//        this.rentCost = client.applyDiscount(getRentDays() * vehicle.getBasePrice());
//    }
//
//    public double getRentCost() {
//        return rentCost;
//    }
//}
