package org.licenta2024JPA.Test.Benchmark;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Entities.Wishlist.Wishlist;
import org.licenta2024JPA.Entities.Wishlist.WishlistId;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.licenta2024JPA.Repositories.WishlistRepository;
import org.openjdk.jmh.annotations.*;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class WishlistBenchmark {

    private WishlistRepository wishlistRepository;

    private List<Client> clients;
    private List<Produs> products;

    @State(Scope.Thread)
    public static class BenchmarkState {
        int currentIndex = 1;
    }

    @Setup(Level.Trial)
    public void setUp() {
        wishlistRepository = new WishlistRepository();
        ClientRepository clientRepository = new ClientRepository();
        ProdusRepository produsRepository = new ProdusRepository();

        clients = clientRepository.findAll();
        products = produsRepository.findAll();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkAddWishlist(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Produs product = products.get(state.currentIndex % products.size());
        state.currentIndex++;

        WishlistId id = new WishlistId(client.getCodclient(), product.getCodprodus());

        if (wishlistRepository.findById(id) == null) {
            Wishlist wishlist = new Wishlist();
            wishlist.setId(id);
            wishlist.setCodclient(client);
            wishlist.setCodprodus(product);
            wishlist.setDataadaugare(Instant.now());

            wishlistRepository.addWishlist(wishlist);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFindById(BenchmarkState state) {
        Client client = clients.get(state.currentIndex % clients.size());
        Produs product = products.get(state.currentIndex % products.size());

        WishlistId id = new WishlistId(client.getCodclient(), product.getCodprodus());
        wishlistRepository.findById(id);
        state.currentIndex++;
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        wishlistRepository.closeEntityManager();
    }
}
