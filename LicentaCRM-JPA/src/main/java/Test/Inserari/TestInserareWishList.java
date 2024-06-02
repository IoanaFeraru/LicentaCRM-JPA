package Test.Inserari;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Entities.Wishlist.Wishlist;
import org.licenta2024JPA.Entities.Wishlist.WishlistId;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.licenta2024JPA.Repositories.WishlistRepository;

import java.time.Instant;
import java.util.List;
import java.util.Random;

public class TestInserareWishList {

    private WishlistRepository wishlistRepository;
    private ClientRepository clientRepository;
    private ProdusRepository produsRepository;

    public TestInserareWishList() {
        this.wishlistRepository = new WishlistRepository();
        this.clientRepository = new ClientRepository();
        this.produsRepository = new ProdusRepository();
    }

    public void insertWishList() {
        List<Client> clients = clientRepository.findAll();
        List<Produs> products = produsRepository.findAll();

        wishlistRepository.beginTransaction();
        try {
            Random random = new Random();
            for (Client client : clients) {
                int numberOfWishListItems = random.nextInt(5) + 1; // Up to 5 random products
                for (int j = 0; j < numberOfWishListItems; j++) {
                    Produs product = products.get(random.nextInt(products.size()));

                    WishlistId id = new WishlistId();
                    id.setCodclient(client.getCodclient());
                    id.setCodprodus(product.getCodprodus());

                    // Check if the entity already exists
                    if (wishlistRepository.findById(id) != null) {
                        continue; // Skip if it already exists
                    }

                    Wishlist wishlist = new Wishlist();
                    wishlist.setId(id);
                    wishlist.setCodclient(client);
                    wishlist.setCodprodus(product);
                    wishlist.setDataadaugare(Instant.now());

                    wishlistRepository.create(wishlist);
                }
                System.out.println("Inserted Wishlist items for client: " + client.getCodclient());
            }
            wishlistRepository.commitTransaction();
        } catch (Exception e) {
            wishlistRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        wishlistRepository.closeEntityManager();
    }
}
