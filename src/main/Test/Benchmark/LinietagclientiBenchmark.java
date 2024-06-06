package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Linietagclienti.Linietagclienti;
import org.licenta2024JPA.Entities.Linietagclienti.LinietagclientiId;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.LinietagclientiRepository;
import org.licenta2024JPA.Repositories.SegmentRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class LinietagclientiBenchmark {

    private LinietagclientiRepository linietagclientiRepository;
    private ClientRepository clientRepository;
    private SegmentRepository segmentRepository;

    private List<Client> clients;
    private List<Segment> segments;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        linietagclientiRepository = new LinietagclientiRepository();
        clientRepository = new ClientRepository();
        segmentRepository = new SegmentRepository();

        clients = clientRepository.findAll();
        segments = segmentRepository.findAll();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddLinietagclienti(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Segment segment = segments.get(state.currentIndex % segments.size());
        state.currentIndex++;

        LinietagclientiId id = new LinietagclientiId(segment.getId(), client.getCodclient());

        if (linietagclientiRepository.findById(id) == null) {
            Linietagclienti linietagclienti = new Linietagclienti();
            linietagclienti.setId(id);
            linietagclienti.setCodclient(client);
            linietagclienti.setCodsegment(segment);

            linietagclientiRepository.addLinietagclienti(linietagclienti);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Segment segment = segments.get(state.currentIndex % segments.size());

        LinietagclientiId id = new LinietagclientiId(segment.getId(), client.getCodclient());
        linietagclientiRepository.findById(id);
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        linietagclientiRepository.closeEntityManager();
    }
}
