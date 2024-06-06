package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Adresa.Adresa;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class AdresaRepository extends AbstractRepository<Adresa> {
    @Override
    protected Class<Adresa> getEntityClass() {
        return Adresa.class;
    }

    public Adresa findById(String id) {
        return getEm().find(Adresa.class, id);
    }

    public void addAdresa(Adresa adresa) {
        try {
            beginTransaction();
            create(adresa);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateAdresa(Adresa adresa) {
        try {
            beginTransaction();
            update(adresa);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteAdresa(Adresa adresa) {
        try {
            beginTransaction();
            delete(adresa);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
