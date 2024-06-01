package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class AchizitieRepository extends AbstractRepository<Achizitie> {
    @Override
    protected Class<Achizitie> getEntityClass() {
        return Achizitie.class;
    }

    public Achizitie findById(Integer id) {
        return getEm().find(Achizitie.class, id);
    }

    public void addAchizitie(Achizitie achizitie) {
        try {
            beginTransaction();
            create(achizitie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateAchizitie(Achizitie achizitie) {
        try {
            beginTransaction();
            update(achizitie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteAchizitie(Achizitie achizitie) {
        try {
            beginTransaction();
            delete(achizitie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
