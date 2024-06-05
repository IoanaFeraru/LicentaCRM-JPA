package Test.Inserari;

import org.licenta2024JPA.Entities.Adresa.Adresa;
import org.licenta2024JPA.Entities.Adresa.Judet;
import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Repositories.AdresaRepository;
import org.licenta2024JPA.Repositories.ClientRepository;

import java.util.List;
import java.util.Random;

public class TestInserareAdrese {

    private AdresaRepository adresaRepository;
    private ClientRepository clientRepository;

    public TestInserareAdrese() {
        this.adresaRepository = new AdresaRepository();
        this.clientRepository = new ClientRepository();
    }

    public void insertAdreseForClients() {
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            int numberOfAdrese = new Random().nextInt(3) + 1;

            for (int i = 1; i <= numberOfAdrese; i++) {
                adresaRepository.beginTransaction();

                Adresa adresa = new Adresa();
                adresa.setCodclient(client);
                adresa.setJudet(Judet.values()[new Random().nextInt(Judet.values().length)]);
                adresa.setCodpostal(new Random().nextInt(899999) + 100000);
                adresa.setStrada("Strada " + i);
                adresa.setBloc("Bloc " + i);

                try {
                    adresaRepository.create(adresa);
                    adresaRepository.commitTransaction();
                    System.out.println("Inserted Adresa for Client: " + client.getNume() + " " + client.getPrenume());
                } catch (Exception e) {
                    adresaRepository.rollbackTransaction();
                    System.err.println("Error inserting Adresa for Client: " + client.getNume() + " " + client.getPrenume());
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeEm() {
        adresaRepository.closeEntityManager();
    }
}
