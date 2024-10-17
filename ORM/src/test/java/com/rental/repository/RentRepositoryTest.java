package com.rental.repository;

import com.rental.model.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private RentRepository rentRepository;
    private ClientRepository clientRepository;
    private VehicleRepository vehicleRepository;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("NBDUnit");
    }

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        rentRepository = new RentRepository();
        clientRepository = new ClientRepository();
        vehicleRepository = new VehicleRepository();
    }

    @AfterEach
    public void tearDown() {
        if (em != null) {
            em.close();
        }
    }

    @AfterAll
    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testAddRent() {
        ClientType type = new GoldClientType();
        Client client = new Client( "Joe Doe", type);
        Vehicle vehicle = new Car("El7v200", 800, 2, "A");
        Rent rent = new Rent(client, vehicle);

        Client addedClient = clientRepository.add(client);
        Vehicle addedVehicle = vehicleRepository.add(vehicle);
        Rent addedRent = rentRepository.add(rent);

        assertNotNull(addedRent.getRentId(), "Rent ID should not be null after being added.");
        assertEquals(vehicle.getPlateNumber(), addedRent.getVehicle().getPlateNumber());
        assertEquals(client.getUsername(), addedRent.getClient().getUsername());
    }

    @Test
    public void testGetByID() {
        ClientType type = new GoldClientType();
        Client client = new Client( "Joe Doe", type);
        Vehicle vehicle = new Car("El7v200", 800, 2, "A");
        Rent rent = new Rent(client, vehicle);

        Client addedClient = clientRepository.add(client);
        Vehicle addedVehicle = vehicleRepository.add(vehicle);
        rentRepository.add(rent);

        Rent foundRent = rentRepository.getByID(rent.getRentId());
        assertNotNull(foundRent, "Rent should not be null when fetched by ID.");
        assertEquals(vehicle.getPlateNumber(), foundRent.getVehicle().getPlateNumber());
        assertEquals(client.getUsername(), foundRent.getClient().getUsername());
    }

    @Test
    public void testFindAll() {
        ClientType type1 = new GoldClientType();
        Client client1 = new Client( "Joe Doe", type1);
        Vehicle vehicle1 = new Car("El7v200", 800, 2, "A");
        Rent rent1 = new Rent(client1, vehicle1);

        ClientType type2 = new DiamondClientType();
        Client client2 = new Client( "Johhny Doeski", type2);
        Vehicle vehicle2 = new Bicycle("El1234", 600);
        Rent rent2 = new Rent(client2, vehicle2);

        Client addedClient1 = clientRepository.add(client1);
        Vehicle addedVehicle1 = vehicleRepository.add(vehicle1);
        Client addedClient2 = clientRepository.add(client2);
        Vehicle addedVehicle2 = vehicleRepository.add(vehicle2);
        rentRepository.add(rent1);
        rentRepository.add(rent2);

        List<Rent> rents = rentRepository.findAll();
        assertFalse(rents.isEmpty(), "Rents list should not be empty.");
        assertEquals(2, rents.size());
    }

    @Test
    public void testRemoveRent() {
        ClientType type = new GoldClientType();
        Client client = new Client( "Joe Doe", type);
        Vehicle vehicle = new Car("El7v200", 800, 2, "A");
        Rent rent = new Rent(client, vehicle);

        Client addedClient = clientRepository.add(client);
        Vehicle addedVehicle = vehicleRepository.add(vehicle);
        rentRepository.add(rent);
        rentRepository.remove(rent);

        Rent removedRent = rentRepository.getByID(rent.getRentId());
        assertNull(removedRent, "Rent should be removed from the database.");
    }
}
