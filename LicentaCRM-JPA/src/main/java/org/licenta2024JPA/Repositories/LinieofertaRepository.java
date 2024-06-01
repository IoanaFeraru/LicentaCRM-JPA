package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linieoferta.Linieoferta;
import org.licenta2024JPA.Entities.Linieoferta.LinieofertaId;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class LinieofertaRepository extends AbstractRepository<Linieoferta> {
    @Override
    protected Class<Linieoferta> getEntityClass() {
        return Linieoferta.class;
    }

    public Linieoferta findById(LinieofertaId id) {
        return getEm().find(Linieoferta.class, id);
    }

    public void addLinieoferta(Linieoferta linieoferta) {
        try {
            beginTransaction();
            create(linieoferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateLinieoferta(Linieoferta linieoferta) {
        try {
            beginTransaction();
            update(linieoferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteLinieoferta(Linieoferta linieoferta) {
        try {
            beginTransaction();
            delete(linieoferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}