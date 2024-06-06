package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Entities.Comunicare.Metoda;
import org.licenta2024JPA.Entities.Comunicare.StatusComunicare;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.ComunicareRepository;
import org.licenta2024JPA.Repositories.SegmentRepository;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ComunicareBenchmark {

    private ComunicareRepository comunicareRepository;

    private List<Segment> segments;
    private List<Integer> comunicareIds;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        comunicareRepository = new ComunicareRepository();
        SegmentRepository segmentRepository = new SegmentRepository();
        segments = segmentRepository.findAll();
        comunicareIds = new ArrayList<>();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddMassComunicare(BenchmarkState state) {
        Segment segment = segments.get(state.currentIndex % segments.size());
        state.currentIndex++;
        Comunicare comunicare = new Comunicare();
        comunicare.setScop("MassComunication");
        comunicare.setStatus(StatusComunicare.SENT);
        comunicare.setMetoda(Metoda.EMAIL);
        comunicare.setCodsegment(segment);

        comunicareRepository.addComunicare(comunicare);
        comunicareIds.add(comunicare.getId());
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        if (!comunicareIds.isEmpty()) {
            Integer id = comunicareIds.get(state.currentIndex % comunicareIds.size());
            comunicareRepository.findById(id);
        }
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        comunicareRepository.closeEntityManager();
    }
}
