package com.rental.model;

// Client.java
public class Client {
    private String firstName;
    private String lastName;
    private String personalId;
    private Address address;
    private ClientType clientType;
    private boolean archive;

    public Client(String firstName, String lastName, String id, Address address) {
        if (firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("First name is required");
        if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Last name is required");
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = id;
        this.address = address;
        this.archive = false;
        this.clientType = new DefaultClientType();  // Assuming DefaultClientType is a subclass of ClientType
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("First name is required");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Last name is required");
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) throw new IllegalArgumentException("Address is required");
        this.address = address;
    }

    public String getClientInfo() {
        return firstName + " " + lastName + " " + personalId + " " + address.getAddressInfo() + " " + clientType.getTypeInfo();
    }

    public int getMaxVehicles() {
        return clientType.getMaxVehicles();
    }

    public double applyDiscount(double price) {
        return clientType.applyDiscount(price);
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public boolean isArchived() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
}
