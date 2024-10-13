//package com.rental.manager;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Predicate;
//
//public class RentManager {
//    private RentRepository currentRents;
//    private RentRepository archiveRents;
//
//    public RentManager() {
//        this.currentRents = new RentRepository();  // zakładam, że RentRepository ma domyślny konstruktor
//        this.archiveRents = new RentRepository();  // analogicznie tutaj
//    }
//
//    public Rent rentVehicle(Client client, Vehicle vehicle, LocalDateTime beginTime) {
//        if (client.isArchived() || vehicle.isArchived() || getAllClientRents(client).size() >= client.getMaxVehicles()
//                || getVehicleRent(vehicle) != null) {
//            return null;
//        }
//        Rent rent = new Rent(client, vehicle, beginTime);
//        currentRents.addRent(rent);
//        return rent;
//    }
//
//    public void returnVehicle(Vehicle vehicle) {
//        Rent rent = getVehicleRent(vehicle);
//        if (rent != null) {
//            rent.endRent(LocalDateTime.now());
//            archiveRents.addRent(rent);
//            currentRents.removeRent(rent);
//            vehicle.setArchive(true);
//            changeClientType(rent.getClient());
//        }
//    }
//
//    public List<Rent> getAllClientRents(Client client) {
//        return findRents(new ClientRentsPredicate(client));
//    }
//
//    public Rent getVehicleRent(Vehicle vehicle) {
//        List<Rent> rents = findRents(new VehicleRentsPredicate(vehicle));
//        return rents.isEmpty() ? null : rents.get(rents.size() - 1);  // Zwraca ostatni rent
//    }
//
//    public List<Rent> findAllRents() {
//        return findRents(new AllRentsPredicate());
//    }
//
//    public List<Rent> findRents(Predicate<Rent> predicate) {
//        List<Rent> temp = currentRents.findBy(predicate);
//        List<Rent> result = new ArrayList<>();
//
//        for (Rent rent : temp) {
//            result.add(rent);
//        }
//
//        return result;
//    }
//
//    public double checkClientRentBalance(Client client) {
//        List<Rent> rents = archiveRents.findBy(new ClientRentsPredicate(client));
//        double cost = 0.0;
//        for (Rent rent : rents) {
//            cost += rent.getRentCost();
//        }
//        return cost;
//    }
//
//    public void changeClientType(Client client) {
//        double balance = checkClientRentBalance(client);
//        if (balance >= 1600.0) {
//            client.setClientType(new Diamond());
//        } else if (balance >= 800.0) {
//            client.setClientType(new Platinum());
//        } else if (balance >= 400.0) {
//            client.setClientType(new Gold());
//        } else if (balance >= 200.0) {
//            client.setClientType(new Silver());
//        } else if (balance >= 100.0) {
//            client.setClientType(new Bronze());
//        } else {
//            client.setClientType(new Default());
//        }
//    }
//
//    // Klasy do zastąpienia lambd
//
//    static class ClientRentsPredicate implements Predicate<Rent> {
//        private Client client;
//
//        public ClientRentsPredicate(Client client) {
//            this.client = client;
//        }
//
//        @Override
//        public boolean test(Rent rent) {
//            return rent.getClient().equals(client);
//        }
//    }
//
//    static class VehicleRentsPredicate implements Predicate<Rent> {
//        private Vehicle vehicle;
//
//        public VehicleRentsPredicate(Vehicle vehicle) {
//            this.vehicle = vehicle;
//        }
//
//        @Override
//        public boolean test(Rent rent) {
//            return rent.getVehicle().equals(vehicle);
//        }
//    }
//
//    static class AllRentsPredicate implements Predicate<Rent> {
//        @Override
//        public boolean test(Rent rent) {
//            return true;  // Zwraca wszystkie wynajmy
//        }
//    }
//}