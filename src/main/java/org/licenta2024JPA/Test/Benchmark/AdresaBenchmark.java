package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Adresa.Adresa;
import org.licenta2024JPA.Entities.Adresa.Judet;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Repositories.AdresaRepository;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AdresaBenchmark {

    private AdresaRepository adresaRepository;
    private String testAdresaId;

    @Setup(Level.Trial)
    public void setUp() {
        adresaRepository = new AdresaRepository();
        ClientRepository clientRepository = new ClientRepository();
        Client client = clientRepository.findById("C0");

        Adresa testAdresa = new Adresa();
        testAdresa.setCodclient(client);
        testAdresa.setJudet(Judet.BUCURESTI);
        testAdresa.setCodpostal(12345);
        testAdresa.setStrada("Test Strada");
        testAdresa.setBloc("Test Bloc");
        adresaRepository.addAdresa(testAdresa);
        testAdresaId = String.valueOf(testAdresa.getId());
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddAdresa() {
        ClientRepository clientRepository = new ClientRepository();
        Client client = clientRepository.findById("C0");

        Adresa adresa = new Adresa();
        adresa.setCodclient(client);
        adresa.setJudet(Judet.BUCURESTI);
        adresa.setCodpostal(12345);
        adresa.setStrada("Test Strada");
        adresa.setBloc("Test Bloc");
        adresaRepository.addAdresa(adresa);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById() {
        adresaRepository.findById(testAdresaId);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        adresaRepository.closeEntityManager();
    }
}
