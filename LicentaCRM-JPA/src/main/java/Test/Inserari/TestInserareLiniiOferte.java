package Test.Inserari;

import org.licenta2024JPA.Entities.Linieoferta.Linieoferta;
import org.licenta2024JPA.Entities.Linieoferta.LinieofertaId;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Oferta.Tipreducere;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.LinieofertaRepository;
import org.licenta2024JPA.Repositories.OfertaRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;

import java.util.List;
import java.util.Random;

public class TestInserareLiniiOferte {

    private final LinieofertaRepository linieofertaRepository;
    private final OfertaRepository ofertaRepository;
    private final ProdusRepository produsRepository;

    public TestInserareLiniiOferte() {
        this.linieofertaRepository = new LinieofertaRepository();
        this.ofertaRepository = new OfertaRepository();
        this.produsRepository = new ProdusRepository();
    }

    public void insertLiniiOferte() {
        linieofertaRepository.beginTransaction();
        try {
            List<Oferta> oferteProdus = ofertaRepository.findWithNamedQueryAndParameter("Oferta.findByTipreducere", "tipreducere", Tipreducere.PRODUS);
            List<Produs> produse = produsRepository.findAll();

            Random random = new Random();

            for (Oferta oferta : oferteProdus) {
                int numberOfProducts = random.nextInt(10) + 1;

                for (int i = 0; i < numberOfProducts; i++) {
                    Produs produs = produse.get(random.nextInt(produse.size()));

                    LinieofertaId linieofertaId = new LinieofertaId();
                    linieofertaId.setCodoferta(oferta.getCodoferta());
                    linieofertaId.setCodprodus(produs.getCodprodus());

                    // Check if the Linieoferta already exists
                    Linieoferta existingLinieoferta = linieofertaRepository.findById(linieofertaId);
                    if (existingLinieoferta != null) {
                        System.out.println("Linieoferta already exists: " + linieofertaId);
                        continue; // Skip this iteration if the entry already exists
                    }

                    Linieoferta linieoferta = new Linieoferta();
                    linieoferta.setId(linieofertaId);
                    linieoferta.setCodoferta(oferta);
                    linieoferta.setCodprodus(produs);

                    linieofertaRepository.create(linieoferta);
                    System.out.println("Inserted Linieoferta: " + linieoferta.getId());
                }
            }

            linieofertaRepository.commitTransaction();
        } catch (Exception e) {
            linieofertaRepository.rollbackTransaction();
            throw e;
        }
    }
    public void closeEm() {
        linieofertaRepository.closeEntityManager();
    }
}


