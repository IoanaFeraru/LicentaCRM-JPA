package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class TagproduseRepository extends AbstractRepository<Tagproduse> {
    @Override
    protected Class<Tagproduse> getEntityClass() {
        return Tagproduse.class;
    }

    public Tagproduse findById(Integer id) {
        return getEm().find(Tagproduse.class, id);
    }

    public void addTagproduse(Tagproduse tagproduse) {
        try {
            beginTransaction();
            create(tagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateTagproduse(Tagproduse tagproduse) {
        try {
            beginTransaction();
            update(tagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteTagproduse(Tagproduse tagproduse) {
        try {
            beginTransaction();
            delete(tagproduse);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}