package com.rental.manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;
import com.rental.model.Client;
import com.rental.model.ClientType;
import com.rental.model.Address;
import com.rental.repository.ClientRepository;

public class ClientManager {
    private ClientRepository clientRepository;
    private final EntityManager em;
    private EntityTransaction et;

    public ClientManager(EntityManager entityManager) {
        this.em = entityManager;
        this.clientRepository = new ClientRepository(entityManager);  // zakładam domyślny konstruktor repozytorium
    }

    public void removeClient(UUID ID) {
        clientRepository.remove(clientRepository.getByID(ID));
    }

    public List<Client> findAll() {
        List<Client> list = clientRepository.findAll();
        return list;
    }

    public Client addClient(String Username, ClientType Type, Address Address) {
        Client client = new Client(Username, Type, Address);
        clientRepository.add(client);
        return client;
    }
}
