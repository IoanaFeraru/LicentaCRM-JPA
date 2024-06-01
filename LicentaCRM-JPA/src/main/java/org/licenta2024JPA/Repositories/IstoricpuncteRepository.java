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

    public void addIstoricpuncte(Istoricpuncte istoricpuncte) {
        try {
            beginTransaction();

            // Automatically set codClient from related Achizitie
            Achizitie achizitie = istoricpuncte.getCodachizitie();
            Client client = achizitie.getCodclient();
            istoricpuncte.setCodclient(client);

            // Calculate valoarepuncte based on totalSuma and plataPuncte from related Achizitie
            Integer valoarepuncte = achizitie.getTotalSuma().intValue() - achizitie.getPlatapuncte();
            istoricpuncte.setValoarepuncte(valoarepuncte);

            // Update client's puncteloialitate
            client.setPuncteloialitate(client.getPuncteloialitate() + valoarepuncte);

            // Persist the changes
            getEm().merge(client);
            create(istoricpuncte);

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
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
