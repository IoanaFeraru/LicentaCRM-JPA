package Test;

import org.licenta2024JPA.Metamodels.AbstractRepository;
import org.licenta2024JPA.Repositories.*;
import java.util.List;

public class TestDeleteAllData {
    public static void main(String[] args) {
        // Initialize repositories
        AchizitieRepository achizitieRepo = new AchizitieRepository();
        AdresaRepository adresaRepo = new AdresaRepository();
        CampanieRepository campanieRepo = new CampanieRepository();
        ClientRepository clientRepo = new ClientRepository();
        ComunicareRepository comunicareRepo = new ComunicareRepository();
        FeedbackRepository feedbackRepo = new FeedbackRepository();
        IstoricpuncteRepository istoricpuncteRepo = new IstoricpuncteRepository();
        LinieachizitieRepository linieachizitieRepo = new LinieachizitieRepository();
        LinieofertaRepository linieofertaRepo = new LinieofertaRepository();
        LinietagclientiRepository linietagclientiRepo = new LinietagclientiRepository();
        LinietagproduseRepository linietagproduseRepo = new LinietagproduseRepository();
        OfertaRepository ofertaRepo = new OfertaRepository();
        ProdusRepository produsRepo = new ProdusRepository();
        SegmentRepository segmentRepo = new SegmentRepository();
        TagproduseRepository tagproduseRepo = new TagproduseRepository();
        WishlistRepository wishlistRepo = new WishlistRepository();

        try {
            deleteAllEntries(istoricpuncteRepo);
            deleteAllEntries(linietagclientiRepo);
            deleteAllEntries(linietagproduseRepo);
            deleteAllEntries(linieachizitieRepo);
            deleteAllEntries(linieofertaRepo);
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
        } finally {
            // Close all EntityManagers
            closeAllEntityManagers(
                    achizitieRepo, adresaRepo, campanieRepo, clientRepo,
                    comunicareRepo, feedbackRepo, istoricpuncteRepo, linieachizitieRepo,
                    linieofertaRepo, linietagclientiRepo, linietagproduseRepo,
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

    private static void closeAllEntityManagers(AbstractRepository<?>... repositories) {
        for (AbstractRepository<?> repository : repositories) {
            if (repository.getEm().isOpen()) {
                repository.closeEntityManager();
            }
        }
    }
}


