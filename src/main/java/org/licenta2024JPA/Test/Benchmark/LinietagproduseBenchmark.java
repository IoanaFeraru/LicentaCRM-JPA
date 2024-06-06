package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Linietagproduse.Linietagproduse;
import org.licenta2024JPA.Entities.Linietagproduse.LinietagproduseId;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Repositories.LinietagproduseRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.licenta2024JPA.Repositories.TagproduseRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class LinietagproduseBenchmark {

    private LinietagproduseRepository linietagproduseRepository;
    private ProdusRepository produsRepository;
    private TagproduseRepository tagproduseRepository;

    private List<Produs> produse;
    private List<Tagproduse> taguri;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        linietagproduseRepository = new LinietagproduseRepository();
        produsRepository = new ProdusRepository();
        tagproduseRepository = new TagproduseRepository();

        produse = produsRepository.findAll();
        taguri = tagproduseRepository.findAll();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkSingleInsertOperation(BenchmarkState state) {
        int currentIndex = state.currentIndex++;

        Produs produs = produse.get(currentIndex % produse.size());
        Tagproduse tag = taguri.get(currentIndex % taguri.size());

        LinietagproduseId linietagproduseId = new LinietagproduseId();
        linietagproduseId.setCodprodus(produs.getCodprodus());
        linietagproduseId.setCodtag((Integer) tag.getId());

        Linietagproduse existingLinietagproduse = linietagproduseRepository.findById(linietagproduseId);
        if (existingLinietagproduse == null) {
            Linietagproduse linietagproduse = new Linietagproduse();
            linietagproduse.setId(linietagproduseId);
            linietagproduse.setCodprodus(produs);
            linietagproduse.setCodtag(tag);

            linietagproduseRepository.addLinietagproduse(linietagproduse);
        }
    }

    @TearDown
    public void tearDown() {
        linietagproduseRepository.closeEntityManager();
    }
}
