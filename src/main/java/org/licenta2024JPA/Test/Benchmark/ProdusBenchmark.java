package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ProdusBenchmark {

    private ProdusRepository produsRepository;
    private String testProdusId;

    @Setup(Level.Trial)
    public void setUp() {
        produsRepository = new ProdusRepository();
        Produs testProdus = new Produs();
        testProdus.setCodprodus("P0");
        testProdus.setNume("Test Produs");
        testProdus.setRating(4.5);
        testProdus.setStatus("INSTOCK");
        testProdus.setPret(100.00);
        produsRepository.addProdus(testProdus);
        testProdusId = testProdus.getCodprodus();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddProdus() {
        Produs produs = new Produs();
        produs.setCodprodus("P" + System.nanoTime());
        produs.setNume("Test Produs");
        produs.setRating(4.5);
        produs.setStatus("INSTOCK");
        produs.setPret(100.00);
        produsRepository.addProdus(produs);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById() {
        produsRepository.findById(testProdusId);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        produsRepository.closeEntityManager();
    }
}
