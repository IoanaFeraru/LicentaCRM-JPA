package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linieachizitie.LinieachizitieId;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Metamodels.AbstractRepository;
import org.licenta2024JPA.Entities.Oferta.*;

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

            // Calculate the price of the current line item
            Double lineItemTotal = linieachizitie.getCantitate() * linieachizitie.getCodprodus().getPret();

            // Check for any associated offers and apply them
            Oferta oferta = achizitie.getCodoferta();
            if (oferta != null && oferta.getStatus() == Status.ACTIVE) {
                switch (oferta.getTipreducere()) {
                    case PRODUS:
                        // Apply product discount (free product)
                        lineItemTotal = 0.00;
                        break;
                    case PROCENT:
                        // Apply percentage discount
                        lineItemTotal *= (1 - oferta.getValoarereducere() / 100);
                        break;
                    default:
                        break;
                }
            }else if(oferta.getStatus() == Status.NOTACTIVE){
                System.out.println("Offer not available anymore");
            }

            // Update the total suma of the achizitie
            currentTotal += lineItemTotal;
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
