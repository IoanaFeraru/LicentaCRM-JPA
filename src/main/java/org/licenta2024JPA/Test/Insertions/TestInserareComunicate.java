package Test.Inserari;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Entities.Comunicare.Metoda;
import org.licenta2024JPA.Entities.Comunicare.StatusComunicare;
import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.ComunicareRepository;
import org.licenta2024JPA.Repositories.SegmentRepository;

import java.util.List;
import java.util.Random;

public class TestInserareComunicate {

    private ComunicareRepository comunicareRepository;
    private SegmentRepository segmentRepository;
    private ClientRepository clientRepository;

    public TestInserareComunicate() {
        this.comunicareRepository = new ComunicareRepository();
        this.segmentRepository = new SegmentRepository();
        this.clientRepository = new ClientRepository();
    }

    public void insertComunicate() {
        insertMassComunications();
        insertPersonalComunications();
    }

    private void insertMassComunications() {
        List<Segment> segments = segmentRepository.findAll();

        comunicareRepository.beginTransaction();
        try {
            for (Segment segment : segments) {
                Comunicare comunicare = new Comunicare();
                comunicare.setScop("MassComunication");
                comunicare.setStatus(StatusComunicare.SENT);
                comunicare.setMetoda(Metoda.EMAIL);
                comunicare.setCodsegment(segment);

                comunicareRepository.create(comunicare);
                System.out.println("Inserted MassComunication for segment: " + segment.getId());
            }
            comunicareRepository.commitTransaction();
        } catch (Exception e) {
            comunicareRepository.rollbackTransaction();
            throw e;
        }
    }

    private void insertPersonalComunications() {
        List<Client> clients = clientRepository.findAll();
        Random random = new Random();
        int numberOfClients = random.nextInt(clients.size()) + 1;

        comunicareRepository.beginTransaction();
        try {
            for (int i = 0; i < numberOfClients; i++) {
                Client client = clients.get(random.nextInt(clients.size()));

                Comunicare comunicare = new Comunicare();
                comunicare.setScop("PersonalComunication");
                comunicare.setStatus(StatusComunicare.SENT);
                comunicare.setMetoda(Metoda.EMAIL);
                comunicare.setCodclient(client);

                comunicareRepository.create(comunicare);
                System.out.println("Inserted PersonalComunication for client: " + client.getCodclient());
            }
            comunicareRepository.commitTransaction();
        } catch (Exception e) {
            comunicareRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        comunicareRepository.closeEntityManager();
    }
}
