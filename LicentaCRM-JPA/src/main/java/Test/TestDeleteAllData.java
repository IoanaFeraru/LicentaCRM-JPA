package Test;

import jakarta.persistence.Query;
import org.licenta2024JPA.Metamodels.AbstractRepository;
import org.licenta2024JPA.Repositories.*;
import java.util.List;
import jakarta.persistence.EntityManager;

public class TestDeleteAllData {
    public static void main(String[] args) {
        AchizitieRepository achizitieRepo = new AchizitieRepository();
        AdresaRepository adresaRepo = new AdresaRepository();
        CampanieRepository campanieRepo = new CampanieRepository();
        ClientRepository clientRepo = new ClientRepository();
        ComunicareRepository comunicareRepo = new ComunicareRepository();
        FeedbackRepository feedbackRepo = new FeedbackRepository();
        LinieachizitieRepository linieachizitieRepo = new LinieachizitieRepository();
        LinietagclientiRepository linietagclientiRepo = new LinietagclientiRepository();
        LinietagproduseRepository linietagproduseRepo = new LinietagproduseRepository();
        OfertaRepository ofertaRepo = new OfertaRepository();
        ProdusRepository produsRepo = new ProdusRepository();
        SegmentRepository segmentRepo = new SegmentRepository();
        TagproduseRepository tagproduseRepo = new TagproduseRepository();
        WishlistRepository wishlistRepo = new WishlistRepository();

        try {
            deleteAllEntries(linietagclientiRepo);
            deleteAllEntries(linietagproduseRepo);
            deleteAllEntries(linieachizitieRepo);
            deleteAllEntries(feedbackRepo);
            deleteAllEntries(wishlistRepo);
            deleteAllEntries(achizitieRepo);
            deleteAllEntries(adresaRepo);
            deleteAllEntries(campanieRepo);
            deleteAllEntries(comunicareRepo);
            deleteAllEntries(ofertaRepo);
            deleteAllEntries(segmentRepo);
            deleteAllEntries(tagproduseRepo);
            deleteAllEntries(produsRepo);
            deleteAllEntries(clientRepo);

            resetAllSequences(
                    achizitieRepo.getEm(),
                    "achizitie_codachizitie_seq",
                    "adresa_codadresa_seq",
                    "campanie_codcampanie_seq",
                    "comunicare_codcomunicare_seq",
                    "segment_codsegment_seq",
                    "tagproduse_codtag_seq"
            );

        } finally {
            closeAllEntityManagers(
                    achizitieRepo, adresaRepo, campanieRepo, clientRepo,
                    comunicareRepo, feedbackRepo, linieachizitieRepo,
                    linietagclientiRepo, linietagproduseRepo,
                    ofertaRepo, produsRepo, segmentRepo, tagproduseRepo, wishlistRepo
            );
        }
    }

    private static <T> void deleteAllEntries(AbstractRepository<T> repository) {
        List<T> entities = repository.findAll();
        for (T entity : entities) {
            repository.beginTransaction();
            try {
                repository.delete(entity);
                repository.commitTransaction();
                System.out.println("Deleted entity: " + entity);
            } catch (Exception e) {
                repository.rollbackTransaction();
                e.printStackTrace();
            }
        }
    }

    private static void resetAllSequences(EntityManager em, String... sequences) {
        em.getTransaction().begin();
        try {
            for (String sequence : sequences) {
                Query query = em.createNativeQuery("ALTER SEQUENCE " + sequence + " RESTART WITH 1");
                query.executeUpdate();
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private static void closeAllEntityManagers(AbstractRepository<?>... repositories) {
        for (AbstractRepository<?> repository : repositories) {
            if (repository.getEm().isOpen()) {
                repository.closeEntityManager();
            }
        }
    }
}


