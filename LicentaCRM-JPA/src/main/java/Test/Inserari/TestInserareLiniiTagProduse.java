package Test.Inserari;

import org.licenta2024JPA.Entities.Linietagproduse.Linietagproduse;
import org.licenta2024JPA.Entities.Linietagproduse.LinietagproduseId;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Repositories.LinietagproduseRepository;
import org.licenta2024JPA.Repositories.ProdusRepository;
import org.licenta2024JPA.Repositories.TagproduseRepository;

import java.util.List;
import java.util.Random;

public class TestInserareLiniiTagProduse {

    private final LinietagproduseRepository linietagproduseRepository;
    private final ProdusRepository produsRepository;
    private final TagproduseRepository tagproduseRepository;

    public TestInserareLiniiTagProduse() {
        this.linietagproduseRepository = new LinietagproduseRepository();
        this.produsRepository = new ProdusRepository();
        this.tagproduseRepository = new TagproduseRepository();
    }

    public void insertLiniiTagProduse() {
        linietagproduseRepository.beginTransaction();
        try {
            List<Produs> produse = produsRepository.findAll();
            List<Tagproduse> taguri = tagproduseRepository.findAll();

            Random random = new Random();

            for (Produs produs : produse) {
                int numberOfTags = random.nextInt(5) + 1;

                for (int i = 0; i < numberOfTags; i++) {
                    Tagproduse tag = taguri.get(random.nextInt(taguri.size()));

                    LinietagproduseId linietagproduseId = new LinietagproduseId();
                    linietagproduseId.setCodprodus(produs.getCodprodus());
                    linietagproduseId.setCodtag((Integer) tag.getId());

                    // Check if the Linietagproduse already exists
                    Linietagproduse existingLinietagproduse = linietagproduseRepository.findById(linietagproduseId);
                    if (existingLinietagproduse != null) {
                        System.out.println("Linietagproduse already exists: " + linietagproduseId);
                        continue; // Skip this iteration if the entry already exists
                    }

                    Linietagproduse linietagproduse = new Linietagproduse();
                    linietagproduse.setId(linietagproduseId);
                    linietagproduse.setCodprodus(produs);
                    linietagproduse.setCodtag(tag);

                    linietagproduseRepository.create(linietagproduse);
                    System.out.println("Inserted Linietagproduse: " + linietagproduse.getId());
                }
            }

            linietagproduseRepository.commitTransaction();
        } catch (Exception e) {
            linietagproduseRepository.rollbackTransaction();
            throw e;
        }
    }
    public void closeEm(){
        linietagproduseRepository.closeEntityManager();
    }
}
