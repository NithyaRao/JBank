package com.nrdc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by localadmin on 7/28/16.
 */
public class Bank {
    private String id;
    private String name;
    private ArrayList<Client> clients;

    public Bank(String name) {
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.clients = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addClient(String name) {
        Client client = new Client(name);
        clients.add(client);
    }

    public void removeClient(String cId) {
        Optional<Client> client = clients.stream().filter(c -> c.getId().equals(cId)).findFirst();
        client.ifPresent(c -> {
            c.deActivate();
            c.getAccounts().forEach(account -> c.closeAccount(account.getId()));
    });
    }
}
