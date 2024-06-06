package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Campanie.Campanie;
import org.licenta2024JPA.Entities.Campanie.TipCampanie;
import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Repositories.CampanieRepository;
import org.licenta2024JPA.Repositories.ComunicareRepository;
import org.licenta2024JPA.Repositories.OfertaRepository;
import org.openjdk.jmh.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class CampanieBenchmark {

    private CampanieRepository campanieRepository;

    private List<Comunicare> comunicate;
    private List<Oferta> oferte;
    private Random random;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        campanieRepository = new CampanieRepository();
        ComunicareRepository comunicareRepository = new ComunicareRepository();
        OfertaRepository ofertaRepository = new OfertaRepository();
        comunicate = comunicareRepository.findAll();
        oferte = ofertaRepository.findAll();
        random = new Random();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddCampanie(BenchmarkState state) {
        Comunicare comunicare = comunicate.get(state.currentIndex % comunicate.size());
        Oferta oferta = oferte.get(random.nextInt(oferte.size()));
        state.currentIndex++;

        Campanie campanie = new Campanie();
        campanie.setCodcomunicare(comunicare);
        campanie.setCodoferta(oferta);
        campanie.setNume("Campanie for Comunicarea " + comunicare.getId());
        campanie.setDatastart(LocalDate.now());
        campanie.setDatastop(LocalDate.now().plusMonths(1));
        campanie.setTip(comunicare.getCodsegment() != null ? TipCampanie.EN_MASSE : TipCampanie.PERSONAL);

        campanieRepository.addCampanie(campanie);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        Integer id = state.currentIndex;
        campanieRepository.findById(id);
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        campanieRepository.closeEntityManager();
    }
}
