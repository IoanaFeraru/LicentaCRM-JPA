package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linietagproduse.Linietagproduse;
import org.licenta2024JPA.Entities.Linietagproduse.LinietagproduseId;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class LinietagproduseRepository extends AbstractRepository<Linietagproduse> {
    @Override
    protected Class<Linietagproduse> getEntityClass() {
        return Linietagproduse.class;
    }

    public Linietagproduse findById(LinietagproduseId id) {
        return getEm().find(Linietagproduse.class, id);
    }

    public void addLinietagproduse(Linietagproduse linietagproduse) {
        try {
            beginTransaction();
            create(linietagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateLinietagproduse(Linietagproduse linietagproduse) {
        try {
            beginTransaction();
            update(linietagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteLinietagproduse(Linietagproduse linietagproduse) {
        try {
            beginTransaction();
            delete(linietagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}