package org.licenta2024JPA.Test;

import org.licenta2024JPA.Test.Benchmark.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        runBenchmark(TagProduseBenchmark.class);
        runBenchmark(ClientBenchmark.class);
        runBenchmark(AdresaBenchmark.class);
        runBenchmark(ProdusBenchmark.class);
        runBenchmark(OfertaBenchmark.class);
        runBenchmark(LinietagproduseBenchmark.class);
        runBenchmark(SegmentBenchmark.class);
        runBenchmark(LinietagclientiBenchmark.class);
        runBenchmark(ComunicareBenchmark.class);
        runBenchmark(CampanieBenchmark.class);
        runBenchmark(AchizitieBenchmark.class);
        runBenchmark(FeedbackBenchmark.class);
    }

    private static void runBenchmark(Class<?> benchmarkClass) throws Exception {
        Options opt = new OptionsBuilder()
                .include(benchmarkClass.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(5)
                .forks(2)
                .result(benchmarkClass.getSimpleName() + "-Benchmark.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run();
    }
}


