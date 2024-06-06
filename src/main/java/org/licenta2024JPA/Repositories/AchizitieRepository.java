package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Oferta.Status;
import org.licenta2024JPA.Entities.Oferta.Tipreducere;
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

    public void applyVoucherDiscount(Achizitie achizitie) {
        try {
            //applied after all the linieAchizitie were added!
            beginTransaction();

            // Check if there is an associated voucher offer
            Oferta oferta = achizitie.getCodoferta();
            if (oferta != null && oferta.getTipreducere() == Tipreducere.VOUCHER && oferta.getStatus() == Status.ACTIVE) {
                Double totalSuma = achizitie.getTotalSuma();
                Double valoarereducere = oferta.getValoarereducere();

                // Apply voucher discount to the totalSuma
                totalSuma = Math.max(0, totalSuma - valoarereducere);
                achizitie.setTotalSuma(totalSuma);

                getEm().merge(achizitie);
            }else if(oferta.getStatus() == Status.NOTACTIVE){
                System.out.println("Offer not available anymore");
            }

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void calculateAndUpdateValueOfPoints(Achizitie achizitie) {
        try {
            beginTransaction();

            // Calculate valueofpoints
            Integer valueofpoints;
            if (achizitie.getPlatapuncte() != null) {
                valueofpoints = achizitie.getTotalSuma().intValue() - achizitie.getPlatapuncte();
            } else {
                valueofpoints = achizitie.getTotalSuma().intValue();
            }
            achizitie.setValueofpoints(valueofpoints);

            // Update client's puncteloialitate
            Client client = achizitie.getCodclient();
            client.setPuncteloialitate(client.getPuncteloialitate() + valueofpoints);
            getEm().merge(client);
            update(achizitie);

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}
