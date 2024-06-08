package Test.Inserari;

import org.licenta2024JPA.Entities.Campanie.Campanie;
import org.licenta2024JPA.Entities.Campanie.TipCampanie;
import org.licenta2024JPA.Entities.Comunicare.Comunicare;
import org.licenta2024JPA.Entities.Oferta.Oferta;
import org.licenta2024JPA.Repositories.CampanieRepository;
import org.licenta2024JPA.Repositories.ComunicareRepository;
import org.licenta2024JPA.Repositories.OfertaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class TestInserareCampanii {

    private CampanieRepository campanieRepository;
    private ComunicareRepository comunicareRepository;
    private OfertaRepository ofertaRepository;

    public TestInserareCampanii() {
        this.campanieRepository = new CampanieRepository();
        this.comunicareRepository = new ComunicareRepository();
        this.ofertaRepository = new OfertaRepository();
    }

    public void insertCampanii() {
        List<Comunicare> comunicate = comunicareRepository.findAll();
        List<Oferta> oferte = ofertaRepository.findAll();

        campanieRepository.beginTransaction();
        try {
            for (Comunicare comunicare : comunicate) {
                Campanie campanie = new Campanie();
                campanie.setCodcomunicare(comunicare);
                campanie.setCodoferta(oferte.get(new Random().nextInt(oferte.size())));
                campanie.setNume("Campanie for Comunicarea " + comunicare.getId());
                campanie.setDatastart(LocalDate.now());
                campanie.setDatastop(LocalDate.now().plusMonths(1));

                if (comunicare.getCodsegment() != null) {
                    campanie.setTip(TipCampanie.EN_MASSE);
                } else if (comunicare.getCodclient() != null) {
                    campanie.setTip(TipCampanie.PERSONAL);
                }

                campanieRepository.create(campanie);
                System.out.println("Inserted Campanie: " + campanie.getNume());
            }
            campanieRepository.commitTransaction();
        } catch (Exception e) {
            campanieRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        campanieRepository.closeEntityManager();
    }
}
