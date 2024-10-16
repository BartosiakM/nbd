package com.rental.manager;

import java.io.Serializable;

import java.util.UUID;
import com.rental.model.Client;
import com.rental.model.ClientType;
import com.rental.model.Address;
import com.rental.repository.ClientRepository;

public class ClientManager implements Serializable {
    private ClientRepository clientRepository;

    public ClientManager(ClientRepository clientRepository) {
          if (clientRepository == null) {
              throw new NullPointerException("clientRepository is null");
          }
          this.clientRepository = clientRepository;
    }

    public void removeClient(UUID ID) {
        clientRepository.remove(clientRepository.getByID(ID));
    }


    public Client addClient(String username, ClientType type, Address address) {
        Client client = new Client(username, type, address);
        if(clientRepository.getByID(client.getClientId())!= null) {
            throw new IllegalArgumentException("Client already exists");
        }
        return clientRepository.add(client);

    }
}
