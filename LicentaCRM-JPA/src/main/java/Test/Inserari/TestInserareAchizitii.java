package Test.Inserari;

import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linieachizitie.LinieachizitieId;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.AchizitieRepository;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.IstoricpuncteRepository;
import org.licenta2024JPA.Repositories.LinieachizitieRepository;
import org.licenta2024JPA.Repositories.OfertaRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TestInserareAchizitii {

    private AchizitieRepository achizitieRepository;
    private ClientRepository clientRepository;
    private LinieachizitieRepository linieachizitieRepository;
    private OfertaRepository ofertaRepository;
    private ProdusRepository produsRepository;
    private IstoricpuncteRepository istoricpuncteRepository;

    public TestInserareAchizitii() {
        this.achizitieRepository = new AchizitieRepository();
        this.clientRepository = new ClientRepository();
        this.linieachizitieRepository = new LinieachizitieRepository();
        this.ofertaRepository = new OfertaRepository();
        this.produsRepository = new ProdusRepository();
        this.istoricpuncteRepository = new IstoricpuncteRepository();
    }

    public void insertAchizitii() {
        List<Client> clients = clientRepository.findAll();
        List<Oferta> oferte = ofertaRepository.findAll();
        List<Produs> produse = produsRepository.findAll();

        try {
            for (Client client : clients) {
                int numberOfAchizitii = new Random().nextInt(10) + 1;
                for (int i = 0; i < numberOfAchizitii; i++) {
                    achizitieRepository.beginTransaction();
                    Achizitie achizitie = new Achizitie();
                    achizitie.setCodclient(client);
                    achizitie.setDataachizitie(LocalDate.now());
                    achizitie.setStatus("NEW");
                    achizitie.setTotalSuma(0.0);

                    // Randomly assign an offer to the achizitie
                    if (!oferte.isEmpty() && new Random().nextBoolean()) {
                        Oferta oferta = oferte.get(new Random().nextInt(oferte.size()));
                        achizitie.setCodoferta(oferta);
                        if (new Random().nextBoolean()) {
                            achizitie.setPlatapuncte(oferta.getCostpuncte());
                        }
                    }

                    achizitieRepository.create(achizitie);
                    achizitieRepository.commitTransaction();

                    Set<LinieachizitieId> existingIds = new HashSet<>();
                    int numberOfProduse = new Random().nextInt(10) + 1;
                    for (int j = 0; j < numberOfProduse; j++) {
                        Produs produs;
                        LinieachizitieId linieachizitieId;
                        do {
                            produs = produse.get(new Random().nextInt(produse.size()));
                            linieachizitieId = new LinieachizitieId(achizitie.getId(), produs.getCodprodus());
                        } while (existingIds.contains(linieachizitieId));

                        existingIds.add(linieachizitieId);

                        Linieachizitie linieachizitie = new Linieachizitie();
                        linieachizitie.setId(linieachizitieId);
                        linieachizitie.setCodachizitie(achizitie);
                        linieachizitie.setCodprodus(produs);
                        linieachizitie.setCantitate(new Random().nextInt(10) + 1);

                        linieachizitieRepository.addLinieachizitie(linieachizitie);
                    }

                    // Apply voucher discount if applicable
                    if (achizitie.getCodoferta() != null) {
                        achizitieRepository.applyVoucherDiscount(achizitie);
                    }
                }
            }
        } catch (Exception e) {
            achizitieRepository.rollbackTransaction();
            throw e;
        } finally {
            achizitieRepository.closeEntityManager();
        }
    }

    public void closeEm() {
        achizitieRepository.closeEntityManager();
    }
}