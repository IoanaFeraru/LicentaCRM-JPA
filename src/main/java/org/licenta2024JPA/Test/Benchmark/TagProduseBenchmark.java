package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Repositories.TagproduseRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class TagProduseBenchmark {

    private TagproduseRepository tagproduseRepository;
    private Integer testTagId;

    @Setup(Level.Trial)
    public void setUp() {
        tagproduseRepository = new TagproduseRepository();
        Tagproduse testTag = new Tagproduse();
        testTag.setElement("Tag0");
        tagproduseRepository.addTagproduse(testTag);
        testTagId = (Integer) testTag.getId();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddTagproduse() {
        Tagproduse tagproduse = new Tagproduse();
        tagproduse.setElement("Tag" + System.nanoTime());
        tagproduseRepository.addTagproduse(tagproduse);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById() {
        tagproduseRepository.findById((Integer) testTagId);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        tagproduseRepository.closeEntityManager();
    }
}
