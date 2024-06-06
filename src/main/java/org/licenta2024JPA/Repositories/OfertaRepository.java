package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Oferta.Tipreducere;
import org.licenta2024JPA.Metamodels.AbstractRepository;

import java.util.List;

public class OfertaRepository extends AbstractRepository<Oferta> {
    @Override
    protected Class<Oferta> getEntityClass() {
        return Oferta.class;
    }

    public Oferta findById(String id) {
        return getEm().find(Oferta.class, id);
    }

    public void addOferta(Oferta oferta) {
        try {
            beginTransaction();
            create(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateOferta(Oferta oferta) {
        try {
            beginTransaction();
            update(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteOferta(Oferta oferta) {
        try {
            beginTransaction();
            delete(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public List<Oferta> findByTipreducere(Tipreducere tipreducere) {
        return getEm().createNamedQuery("Oferta.findByTipreducere", Oferta.class)
                .setParameter("tipreducere", tipreducere)
                .getResultList();
    }
}
