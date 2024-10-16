package com.rental.repository;

import com.rental.model.Address;
import com.rental.model.Client;
import com.rental.model.ClientType;
import com.rental.model.GoldClientType;
import org.junit.jupiter.api.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private ClientRepository clientRepository;

    @BeforeAll
    public static void init() {
        // Tworzymy EntityManagerFactory dla bazy danych H2 w pamięci
        emf = Persistence.createEntityManagerFactory("test-h2");
    }

    @BeforeEach
    public void setUp() {
        // Tworzymy EntityManager i ClientRepository przed każdym testem
        em = emf.createEntityManager();
        clientRepository = new ClientRepository();
    }

    @AfterEach
    public void tearDown() {
        // Zamykamy EntityManager po każdym teście
        if (em != null) {
            em.close();
        }
    }

    @AfterAll
    public static void close() {
        // Zamykanie EntityManagerFactory po wszystkich testach
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testAddClient() {
        ClientType type = new GoldClientType();
        Address address = new Address("Lodz", "Pilsudskiego", "12");
        Client client = new Client( "John Doe", type, address  );
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Client addedClient = clientRepository.add(client);
        transaction.commit();

        assert(addedClient.getClientId().toString() != null);
        assertEquals("Joe Doe", addedClient.getUsername());
    }

//    @Test
//    public void testGetByID() {
//        UUID clientId = UUID.randomUUID();
//        Client client = new Client(clientId, "Jane", "Doe");
//        EntityTransaction transaction = em.getTransaction();
//
//        transaction.begin();
//        em.persist(client);
//        transaction.commit();
//
//        Client foundClient = clientRepository.getByID(clientId);
//        assertNotNull(foundClient);
//        assertEquals("Jane", foundClient.getFirstName());
//    }
//
//    @Test
//    public void testFindAll() {
//        // Dodanie kilku klientów do bazy danych
//        Client client1 = new Client(UUID.randomUUID(), "John", "Doe");
//        Client client2 = new Client(UUID.randomUUID(), "Jane", "Doe");
//
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(client1);
//        em.persist(client2);
//        transaction.commit();
//
//        List<Client> clients = clientRepository.findAll();
//        assertFalse(clients.isEmpty(), "Clients list should not be empty.");
//        assertEquals(2, clients.size());
//    }
//
//    @Test
//    public void testRemoveClient() {
//        UUID clientId = UUID.randomUUID();
//        Client client = new Client(clientId, "John", "Doe");
//        EntityTransaction transaction = em.getTransaction();
//
//        transaction.begin();
//        em.persist(client);
//        transaction.commit();
//
//        transaction.begin();
//        clientRepository.remove(client);
//        transaction.commit();
//
//        Client removedClient = em.find(Client.class, clientId);
//        assertNull(removedClient, "Client should be removed from the database.");
//    }
//
//    @Test
//    public void testUpdateClient() {
//        UUID clientId = UUID.randomUUID();
//        Client client = new Client(clientId, "John", "Doe");
//        EntityTransaction transaction = em.getTransaction();
//
//        transaction.begin();
//        em.persist(client);
//        transaction.commit();
//
//        // Aktualizacja danych klienta
//        client.setFirstName("Johnny");
//        transaction.begin();
//        clientRepository.update(client);
//        transaction.commit();
//
//        Client updatedClient = em.find(Client.class, clientId);
//        assertEquals("Johnny", updatedClient.getFirstName(), "Client's first name should be updated.");
//    }
}
