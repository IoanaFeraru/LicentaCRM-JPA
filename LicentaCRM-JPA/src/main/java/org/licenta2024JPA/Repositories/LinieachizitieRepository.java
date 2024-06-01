package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linieachizitie.LinieachizitieId;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class LinieachizitieRepository extends AbstractRepository<Linieachizitie> {
    @Override
    protected Class<Linieachizitie> getEntityClass() {
        return Linieachizitie.class;
    }

    public Linieachizitie findById(LinieachizitieId id) {
        return getEm().find(Linieachizitie.class, id);
    }

    public void addLinieachizitie(Linieachizitie linieachizitie) {
        try {
            beginTransaction();
            create(linieachizitie);

            // Update totalSuma in Achizitie
            Achizitie achizitie = linieachizitie.getCodachizitie();
            Double currentTotal = achizitie.getTotalSuma();
            currentTotal += linieachizitie.getCantitate() * linieachizitie.getCodprodus().getPret();
            achizitie.setTotalSuma(currentTotal);

            getEm().merge(achizitie);

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateLinieachizitie(Linieachizitie linieachizitie) {
        try {
            beginTransaction();
            update(linieachizitie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteLinieachizitie(Linieachizitie linieachizitie) {
        try {
            beginTransaction();
            delete(linieachizitie);

            // Update totalSuma in Achizitie
            Achizitie achizitie = linieachizitie.getCodachizitie();
            Double currentTotal = achizitie.getTotalSuma();
            currentTotal -= linieachizitie.getCantitate() * linieachizitie.getCodprodus().getPret(); // Assuming each Produs has a price
            achizitie.setTotalSuma(currentTotal);

            getEm().merge(achizitie);

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
