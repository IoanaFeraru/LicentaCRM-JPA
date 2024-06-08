package Test.Inserari;

import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Repositories.ProdusRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class TestInserareProduse {

    private ProdusRepository produsRepository;

    public TestInserareProduse() {
        this.produsRepository = new ProdusRepository();
    }

    public void insertProduse(int numberOfProduse) {
        produsRepository.beginTransaction();
        try {
            for (int i = 1; i <= numberOfProduse; i++) {
                Produs produs = new Produs();
                produs.setCodprodus("PR" + i);
                produs.setNume("Produs" + i);
                Double rating = BigDecimal.valueOf(new Random().nextDouble() * 5 + 0)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue(); // Random rating between 0 and 5
                produs.setRating(rating);
                produs.setStatus("Available");
                Double price = BigDecimal.valueOf(new Random().nextDouble() * 1000 + 1)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue(); // Random price between 1 and 1000
                produs.setPret(price);

                produsRepository.create(produs);
                System.out.println("Inserted Produs: " + produs.getNume());
            }
            produsRepository.commitTransaction();
        } catch (Exception e) {
            produsRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        produsRepository.closeEntityManager();
    }
}
