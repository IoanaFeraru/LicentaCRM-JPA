package Test.Inserari;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Linietagclienti.Linietagclienti;
import org.licenta2024JPA.Entities.Linietagclienti.LinietagclientiId;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.LinietagclientiRepository;
import org.licenta2024JPA.Repositories.SegmentRepository;

import java.util.List;
import java.util.Random;

public class TestInserareLiniiTagClienti {

    private LinietagclientiRepository linietagclientiRepository;
    private ClientRepository clientRepository;
    private SegmentRepository segmentRepository;

    public TestInserareLiniiTagClienti() {
        this.linietagclientiRepository = new LinietagclientiRepository();
        this.clientRepository = new ClientRepository();
        this.segmentRepository = new SegmentRepository();
    }

    public void insertLiniiTagClienti() {
        List<Client> clients = clientRepository.findAll();
        List<Segment> segments = segmentRepository.findAll();

        linietagclientiRepository.beginTransaction();
        try {
            Random random = new Random();
            for (Client client : clients) {
                int numberOfTags = random.nextInt(5) + 1;
                for (int j = 0; j < numberOfTags; j++) {
                    Segment segment = segments.get(random.nextInt(segments.size()));

                    LinietagclientiId id = new LinietagclientiId();
                    id.setCodclient(client.getCodclient());
                    id.setCodsegment(segment.getId());

                    // Check if the entity already exists
                    if (linietagclientiRepository.findById(id) != null) {
                        continue; // Skip if it already exists
                    }

                    Linietagclienti linietagclienti = new Linietagclienti();
                    linietagclienti.setId(id);
                    linietagclienti.setCodclient(client);
                    linietagclienti.setCodsegment(segment);

                    linietagclientiRepository.create(linietagclienti);
                }
                System.out.println("Inserted LiniiTagClienti for client: " + client.getCodclient());
            }
            linietagclientiRepository.commitTransaction();
        } catch (Exception e) {
            linietagclientiRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        linietagclientiRepository.closeEntityManager();
    }
}
