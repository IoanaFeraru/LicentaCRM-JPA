package Test.Inserari;

import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Entities.Oferta.Status;
import org.licenta2024JPA.Entities.Oferta.Tipreducere;
import org.licenta2024JPA.Repositories.OfertaRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class TestInserareOferte {
    private OfertaRepository ofertaRepository;

    public TestInserareOferte() {
        this.ofertaRepository = new OfertaRepository();
    }

    public void insertOferte(int numberPerTypeOfOffers) {
        ofertaRepository.beginTransaction();
        try {
            for (int i = 0; i < numberPerTypeOfOffers; i++) {
                int buyable = new Random().nextInt(2); //if 1 then it will be buyable by customers

                Oferta ofertaP = new Oferta(); //Product list offer
                ofertaP.setCodoferta("OfertaProdus" + i);
                ofertaP.setStatus(Status.values()[new Random().nextInt(Status.values().length)]);
                ofertaP.setTipreducere(Tipreducere.PRODUS);
                ofertaP.setValoarereducere(1.0);
                ofertaP.setCostpuncte(new Random().nextInt(500)); //Products offers always buyable

                Oferta ofertaV = new Oferta();
                ofertaV.setCodoferta("OfertaVoucher" + i);
                ofertaV.setStatus(Status.values()[new Random().nextInt(Status.values().length)]);
                ofertaV.setTipreducere(Tipreducere.VOUCHER);
                Double valoarereducere = generateRandomDoubleWithStep(50.0, 100.0, 10.0);
                ofertaV.setValoarereducere(valoarereducere);

                Oferta ofertaPr = new Oferta();
                ofertaPr.setCodoferta("OfertaProcentLaComanda" + i);
                ofertaPr.setStatus(Status.values()[new Random().nextInt(Status.values().length)]);
                ofertaPr.setTipreducere(Tipreducere.PROCENT);
                valoarereducere = generateRandomDoubleWithStep(0.1, 1.0, 0.1);
                ofertaPr.setValoarereducere(valoarereducere);

                if (buyable == 1) {
                    //sets the cost of all offers buyable with points in the 100-600 range with a 20point step
                    ofertaV.setCostpuncte(generateRandomIntWithStep(100, 600, 20));
                    ofertaPr.setCostpuncte(generateRandomIntWithStep(100, 600, 20));
                }

                ofertaRepository.create(ofertaPr);
                ofertaRepository.create(ofertaV);
                ofertaRepository.create(ofertaP);

                System.out.println("Inserted Offers: " + ofertaP.getCodoferta() + ", " + ofertaV.getCodoferta() + ", " + ofertaPr.getCodoferta());
            }
            ofertaRepository.commitTransaction();
        } catch (Exception e) {
            ofertaRepository.rollbackTransaction();
            throw e;
        }
    }

    private int generateRandomIntWithStep(int min, int max, int step) {
        return new Random().nextInt((max - min) / step + 1) * step + min;
    }

    private Double generateRandomDoubleWithStep(double min, double max, double step) {
        Random random = new Random();
        int numSteps = (int) ((max - min) / step);
        double randomValue = min + random.nextInt(numSteps + 1) * step;
        return BigDecimal.valueOf(randomValue).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public void closeEm() {
        ofertaRepository.closeEntityManager();
    }
}
