package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linieachizitie.LinieachizitieId;
import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Metamodels.AbstractRepository;
import org.licenta2024JPA.Entities.Oferta.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
            BigDecimal currentTotal = BigDecimal.valueOf(achizitie.getTotalSuma());

            // Calculate the price of the current line item
            BigDecimal lineItemTotal = BigDecimal.valueOf(linieachizitie.getCantitate())
                    .multiply(BigDecimal.valueOf(linieachizitie.getCodprodus().getPret()));

            // Check for any associated offers and apply them
            if (achizitie.getCodoferta() != null) {
                Oferta oferta = achizitie.getCodoferta();
                if (oferta.getStatus() == Status.ACTIVE) {
                    switch (oferta.getTipreducere()) {
                        case PRODUS:
                            // Apply product discount (free product)
                            lineItemTotal = BigDecimal.ZERO;
                            break;
                        case PROCENT:
                            // Apply percentage discount
                            lineItemTotal = lineItemTotal.multiply(BigDecimal.valueOf(1 - oferta.getValoarereducere() / 100));
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Offer not available anymore");
                }
            }

            // Round lineItemTotal to 2 decimal places
            lineItemTotal = lineItemTotal.setScale(2, RoundingMode.HALF_UP);

            // Update the total suma of the achizitie
            currentTotal = currentTotal.add(lineItemTotal).setScale(2, RoundingMode.HALF_UP);
            achizitie.setTotalSuma(currentTotal.doubleValue());

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
