package Test.Inserari;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Client.StatusMembru;
import org.licenta2024JPA.Repositories.ClientRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

public class TestInserareClienti {

    private ClientRepository clientRepository;

    public TestInserareClienti() {
        this.clientRepository = new ClientRepository();
    }

    public void insertClients(int numberOfClients) {
        clientRepository.beginTransaction();
        try {
            for (int i = 1; i <= numberOfClients; i++) {
                Client client = new Client();
                client.setCodclient("CL" + i);
                client.setNume("Nume" + i);
                client.setPrenume("Prenume" + i);
                client.setDatanastere(LocalDate.now().minusYears(new Random().nextInt(30) + 18));
                client.setEmail("client" + i + "@example.com");
                client.setNumartelefon("07" + new Random().nextInt(100000000));
                client.setPuncteloialitate(new Random().nextInt(1000));
                client.setStatusmembru(StatusMembru.values()[new Random().nextInt(StatusMembru.values().length)]);
                client.setLastactive(Instant.now());

                clientRepository.create(client);
                System.out.println("Inserted Client: " + client.getNume() + " " + client.getPrenume());
            }
            clientRepository.commitTransaction();
        } catch (Exception e) {
            clientRepository.rollbackTransaction();
            throw e;
        }
    }
    public void closeEm(){
        clientRepository.closeEntityManager();
    }
}
