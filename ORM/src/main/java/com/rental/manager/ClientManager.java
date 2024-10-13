package com.rental.manager;

import com.rental.model.Address;
import com.rental.model.Client;
import com.rental.repository.ClientRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ClientManager {
    private ClientRepository clientRepository;

    public ClientManager() {
        this.clientRepository = new ClientRepository();  // zakładam domyślny konstruktor repozytorium
    }

    public Client getClient(String id) {
        return clientRepository.findByPersonalId(id);
    }

    public Client registerClient(String firstName, String lastName, String id, Address address) {
        Client temp = getClient(id);
        if (temp != null) {
            return temp;
        }
        Client newClient = new Client(firstName, lastName, id, address);
        clientRepository.addClient(newClient);
        return newClient;
    }

    public void unregisterClient(String id) {
        Client client = getClient(id);
        if (client != null) {
            client.setArchive(true);
        }
    }

    // Prosta metoda zamiast lambdy
    public List<Client> findAllClients() {
        return findClients(new AllClientsPredicate());
    }

    // Inna prosta metoda zamiast lambdy do filtrowania
    public List<Client> findClients(Predicate<Client> predicate) {
        List<Client> temp = clientRepository.findBy(predicate);
        List<Client> result = new ArrayList<>();

        for (Client client : temp) {
            if (!client.isArchived()) {
                result.add(client);
            }
        }

        return result;
    }

    // Klasa, która zastępuje lambdę w findAllClients
    static class AllClientsPredicate implements Predicate<Client> {
        @Override
        public boolean test(Client client) {
            return true; // Zwraca wszystkich klientów
        }
    }
}
