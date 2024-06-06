package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class ClientRepository extends AbstractRepository<Client> {
    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }

    public Client findById(String id) {
        return getEm().find(Client.class, id);
    }

    public void addClient(Client client) {
        try {
            beginTransaction();
            create(client);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateClient(Client client) {
        try {
            beginTransaction();
            update(client);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteClient(Client client) {
        try {
            beginTransaction();
            delete(client);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}