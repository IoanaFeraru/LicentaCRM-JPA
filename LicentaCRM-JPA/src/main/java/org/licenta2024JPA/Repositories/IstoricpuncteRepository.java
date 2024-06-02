package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Istoricpuncte.Istoricpuncte;
import org.licenta2024JPA.Entities.Istoricpuncte.IstoricpuncteId;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class IstoricpuncteRepository extends AbstractRepository<Istoricpuncte> {
    @Override
    protected Class<Istoricpuncte> getEntityClass() {
        return Istoricpuncte.class;
    }

    public Istoricpuncte findById(IstoricpuncteId id) {
        return getEm().find(Istoricpuncte.class, id);
    }

    public Integer calculateValoarepuncte(Achizitie achizitie) {
        if (achizitie.getPlatapuncte() != null) {
            return achizitie.getTotalSuma().intValue() - achizitie.getPlatapuncte();
        } else {
            return achizitie.getTotalSuma().intValue();
        }
    }

    // Should never be used
    public void updateIstoricpuncte(Istoricpuncte istoricpuncte) {
        try {
            beginTransaction();
            update(istoricpuncte);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    // Should never be used
    public void deleteIstoricpuncte(Istoricpuncte istoricpuncte) {
        try {
            beginTransaction();
            delete(istoricpuncte);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
