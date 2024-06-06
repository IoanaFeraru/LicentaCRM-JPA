package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.SegmentRepository;
import org.openjdk.jmh.annotations.*;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class SegmentBenchmark {

    private SegmentRepository segmentRepository;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        segmentRepository = new SegmentRepository();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddSegment(BenchmarkState state) {
        Segment segment = new Segment();
        segment.setNume("Segment" + state.currentIndex);
        segment.setDatacreare(LocalDate.now().toString());
        segment.setCriterii("Criterii" + state.currentIndex);

        segmentRepository.addSegment(segment);
        state.currentIndex++;
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        segmentRepository.findById(state.currentIndex);
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        segmentRepository.closeEntityManager();
    }
}
