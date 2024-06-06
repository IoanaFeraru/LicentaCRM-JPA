package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class ComunicareRepository extends AbstractRepository<Comunicare> {
    @Override
    protected Class<Comunicare> getEntityClass() {
        return Comunicare.class;
    }

    public Comunicare findById(Integer id) {
        return getEm().find(Comunicare.class, id);
    }

    public void addComunicare(Comunicare comunicare) {
        try {
            beginTransaction();
            create(comunicare);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateComunicare(Comunicare comunicare) {
        try {
            beginTransaction();
            update(comunicare);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteComunicare(Comunicare comunicare) {
        try {
            beginTransaction();
            delete(comunicare);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}