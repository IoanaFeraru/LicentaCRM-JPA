package Test.Inserari;

import org.licenta2024JPA.Entities.Client.Client;
import org.licenta2024JPA.Entities.Feedback.Feedback;
import org.licenta2024JPA.Entities.Feedback.FeedbackId;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.ClientRepository;
import org.licenta2024JPA.Repositories.FeedbackRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;

import java.util.List;
import java.util.Random;

public class TestInserareFeedbackuri {

    private ClientRepository clientRepository;
    private ProdusRepository produsRepository;
    private FeedbackRepository feedbackRepository;

    public TestInserareFeedbackuri() {
        this.clientRepository = new ClientRepository();
        this.produsRepository = new ProdusRepository();
        this.feedbackRepository = new FeedbackRepository();
    }

    public void insertFeedbackuri(int feedbacksPerClient) {
        List<Client> clients = clientRepository.findAll();
        List<Produs> produse = produsRepository.findAll();
        Random random = new Random();

        try {
            for (Client client : clients) {
                for (int i = 0; i < feedbacksPerClient; i++) {
                    Produs produs = produse.get(random.nextInt(produse.size()));
                    FeedbackId feedbackId = new FeedbackId(client.getCodclient(), produs.getCodprodus());

                    // Check if feedback already exists
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeEm() {
        feedbackRepository.closeEntityManager();
    }
}