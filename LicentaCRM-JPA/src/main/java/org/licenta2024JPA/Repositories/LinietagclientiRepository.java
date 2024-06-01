package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linietagclienti.Linietagclienti;
import org.licenta2024JPA.Entities.Linietagclienti.LinietagclientiId;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class LinietagclientiRepository extends AbstractRepository<Linietagclienti> {
    @Override
    protected Class<Linietagclienti> getEntityClass() {
        return Linietagclienti.class;
    }

    public Linietagclienti findById(LinietagclientiId id) {
        return getEm().find(Linietagclienti.class, id);
    }

    public void addLinietagclienti(Linietagclienti linietagclienti) {
        try {
            beginTransaction();
            create(linietagclienti);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateLinietagclienti(Linietagclienti linietagclienti) {
        try {
            beginTransaction();
            update(linietagclienti);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteLinietagclienti(Linietagclienti linietagclienti) {
        try {
            beginTransaction();
            delete(linietagclienti);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
