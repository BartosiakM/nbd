//package com.rental.manager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Predicate;
//
//public class VehicleManager {
//    private VehicleRepository vehicleRepository;
//
//    public VehicleManager() {
//        this.vehicleRepository = new VehicleRepository();  // Zakładamy, że VehicleRepository ma domyślny konstruktor
//    }
//
//    public Vehicle getVehicle(String plateNumber) {
//        return vehicleRepository.findByPlateNumber(plateNumber);
//    }
//
//    public Vehicle registerBicycle(String plateNumber, int basePrice) {
//        Vehicle temp = getVehicle(plateNumber);
//        if (temp != null) {
//            return temp;
//        }
//        Vehicle newVehicle = new Bicycle(plateNumber, basePrice);
//        vehicleRepository.addVehicle(newVehicle);
//        return newVehicle;
//    }
//
//    public Vehicle registerMoped(String plateNumber, int basePrice, int engineDisplacement) {
//        Vehicle temp = getVehicle(plateNumber);
//        if (temp != null) {
//            return temp;
//        }
//        Vehicle newVehicle = new Moped(plateNumber, basePrice, engineDisplacement);
//        vehicleRepository.addVehicle(newVehicle);
//        return newVehicle;
//    }
//
//    public Vehicle registerCar(String plateNumber, int basePrice, int engineDisplacement, SegmentType segment) {
//        Vehicle temp = getVehicle(plateNumber);
//        if (temp != null) {
//            return temp;
//        }
//        Vehicle newVehicle = new Car(plateNumber, basePrice, engineDisplacement, segment);
//        vehicleRepository.addVehicle(newVehicle);
//        return newVehicle;
//    }
//
//    public void unregisterVehicle(String plateNumber) {
//        Vehicle vehicle = getVehicle(plateNumber);
//        if (vehicle != null) {
//            vehicle.setArchive(true);
//        }
//    }
//
//    public List<Vehicle> findAllVehicles() {
//        return findVehicles(new AllVehiclesPredicate());
//    }
//
//    public List<Vehicle> findVehicles(Predicate<Vehicle> predicate) {
//        List<Vehicle> temp = vehicleRepository.findBy(predicate);
//        List<Vehicle> result = new ArrayList<>();
//
//        for (Vehicle vehicle : temp) {
//            if (!vehicle.isArchived()) {
//                result.add(vehicle);
//            }
//        }
//
//        return result;
//    }
//
//    // Klasa, która zastępuje lambdę w `findAllVehicles`
//    static class AllVehiclesPredicate implements Predicate<Vehicle> {
//        @Override
//        public boolean test(Vehicle vehicle) {
//            return true;  // Zwraca wszystkie pojazdy
//        }
//    }
//}