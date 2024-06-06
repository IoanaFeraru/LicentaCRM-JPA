package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Oferta.Status;
import org.licenta2024JPA.Entities.Oferta.Tipreducere;
import org.licenta2024JPA.Repositories.OfertaRepository;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class OfertaBenchmark {

    private OfertaRepository ofertaRepository;
    private ProdusRepository produsRepository;
    private Produs produs;

    @Setup(Level.Trial)
    public void setUp() {
        ofertaRepository = new OfertaRepository();
        produsRepository = new ProdusRepository();
        produs = ensureProdusExists("P1");
    }

    private Produs ensureProdusExists(String codprodus) {
        Produs produs = produsRepository.findById(codprodus);
        if (produs == null) {
            produs = new Produs();
            produs.setCodprodus(codprodus);
            produs.setNume("Test Produs");
            produs.setRating(4.5);
            produs.setStatus("Available");
            produs.setPret(99.99);
            produsRepository.addProdus(produs);
        }
        return produs;
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddOferta() {
        Oferta oferta = new Oferta();
        oferta.setCodoferta("O" + System.nanoTime());
        oferta.setStatus(Status.ACTIVE);
        oferta.setTipreducere(Tipreducere.PROCENT);
        oferta.setValoarereducere(10.0);
        oferta.setCostpuncte(100);
        oferta.setCodprodus(produs);
        ofertaRepository.addOferta(oferta);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById() {
        ofertaRepository.findById("O1");
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindByTipreducere() {
        ofertaRepository.findByTipreducere(Tipreducere.PROCENT);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        ofertaRepository.closeEntityManager();
    }
}
