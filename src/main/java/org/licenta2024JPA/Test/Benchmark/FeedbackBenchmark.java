package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Feedback.Feedback;
import org.licenta2024JPA.Entities.Feedback.FeedbackId;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.FeedbackRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class FeedbackBenchmark {

    private FeedbackRepository feedbackRepository;

    private List<Client> clients;
    private List<Produs> produse;
    private Random random;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        ClientRepository clientRepository = new ClientRepository();
        ProdusRepository produsRepository = new ProdusRepository();
        feedbackRepository = new FeedbackRepository();

        clients = clientRepository.findAll();
        produse = produsRepository.findAll();
        random = new Random();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddFeedback(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Produs produs = produse.get(random.nextInt(produse.size()));
        state.currentIndex++;

        FeedbackId feedbackId = new FeedbackId(client.getCodclient(), produs.getCodprodus());

        Feedback existingFeedback = feedbackRepository.findById(feedbackId);
        if (existingFeedback == null && feedbackRepository.clientPurchasedProduct(client, produs)) {
            Feedback feedback = new Feedback();
            feedback.setId(feedbackId);
            feedback.setCodclient(client);
            feedback.setCodprodus(produs);
            feedback.setRating(random.nextDouble() * 5);

            feedbackRepository.addFeedback(feedback);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Produs produs = produse.get(random.nextInt(produse.size()));
        FeedbackId feedbackId = new FeedbackId(client.getCodclient(), produs.getCodprodus());

        feedbackRepository.findById(feedbackId);
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        feedbackRepository.closeEntityManager();
    }
}
