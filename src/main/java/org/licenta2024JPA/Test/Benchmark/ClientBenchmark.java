package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Client.StatusMembru;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.openjdk.jmh.annotations.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ClientBenchmark {

    private ClientRepository clientRepository;

    @Setup
    public void setUp() {
        clientRepository = new ClientRepository();
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddClient() {
        try {
            Client client = new Client();
            client.setCodclient("C" + System.nanoTime());
            client.setNume("TestNume");
            client.setPrenume("TestPrenume");
            client.setDatanastere(LocalDate.now());
            client.setEmail("test@example.com");
            client.setNumartelefon("1234567890");
            client.setPuncteloialitate(1000);
            client.setStatusmembru(StatusMembru.NONE);
            client.setLastactive(Instant.now());
            clientRepository.addClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById() {
        Client client = clientRepository.findById("C0");
    }

    @TearDown
    public void tearDown() {
        clientRepository.closeEntityManager();
    }
}
