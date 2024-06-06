package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Achizitie;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Linieachizitie.Linieachizitie;
import org.licenta2024JPA.Entities.Linieachizitie.LinieachizitieId;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.AchizitieRepository;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.LinieachizitieRepository;
import org.licenta2024JPA.Repositories.OfertaRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.openjdk.jmh.annotations.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AchizitieBenchmark {

    private AchizitieRepository achizitieRepository;
    private LinieachizitieRepository linieachizitieRepository;

    private List<Client> clients;
    private List<Oferta> oferte;
    private List<Produs> produse;
    private Random random;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        achizitieRepository = new AchizitieRepository();
        ClientRepository clientRepository = new ClientRepository();
        linieachizitieRepository = new LinieachizitieRepository();
        OfertaRepository ofertaRepository = new OfertaRepository();
        ProdusRepository produsRepository = new ProdusRepository();

        clients = clientRepository.findAll();
        oferte = ofertaRepository.findAll();
        produse = produsRepository.findAll();
        random = new Random();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddAchizitie(BenchmarkState state) {
        Achizitie achizitie = createAchizitie(state.currentIndex % clients.size());
        state.currentIndex++;

        achizitieRepository.addAchizitie(achizitie);

        addLinieachizitii(achizitie);

        if (achizitie.getCodoferta() != null) {
            achizitieRepository.applyVoucherDiscount(achizitie);
        }

        achizitieRepository.calculateAndUpdateValueOfPoints(achizitie);
    }

    private Achizitie createAchizitie(int clientIndex) {
        Client client = clients.get(clientIndex);
        Achizitie achizitie = new Achizitie();
        achizitie.setCodclient(client);
        achizitie.setDataachizitie(LocalDate.now());
        achizitie.setStatus("NEW");
        achizitie.setTotalSuma(0.0);

        if (!oferte.isEmpty() && random.nextBoolean()) {
            Oferta oferta = oferte.get(random.nextInt(oferte.size()));
            achizitie.setCodoferta(oferta);
            if (random.nextBoolean()) {
                achizitie.setPlatapuncte(oferta.getCostpuncte());
            }
        }

        return achizitie;
    }
    private void addLinieachizitii(Achizitie achizitie) {
        Set<LinieachizitieId> existingIds = new HashSet<>();
        int numberOfProduse = random.nextInt(10) + 1;
        for (int j = 0; j < numberOfProduse; j++) {
            Produs produs;
            LinieachizitieId linieachizitieId;
            do {
                produs = produse.get(random.nextInt(produse.size()));
                linieachizitieId = new LinieachizitieId(achizitie.getId(), produs.getCodprodus());
            } while (existingIds.contains(linieachizitieId));

            existingIds.add(linieachizitieId);

            Linieachizitie linieachizitie = new Linieachizitie();
            linieachizitie.setId(linieachizitieId);
            linieachizitie.setCodachizitie(achizitie);
            linieachizitie.setCodprodus(produs);
            linieachizitie.setCantitate(random.nextInt(10) + 1);

            linieachizitieRepository.addLinieachizitie(linieachizitie);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        achizitieRepository.findById(state.currentIndex);
        state.currentIndex++;
    }

    @TearDown
    public void tearDown() {
        achizitieRepository.closeEntityManager();
    }
}
