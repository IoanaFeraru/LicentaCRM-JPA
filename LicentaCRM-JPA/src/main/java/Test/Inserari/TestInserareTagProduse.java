package Test.Inserari;

import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Repositories.TagproduseRepository;

public class TestInserareTagProduse {

    private final TagproduseRepository tagproduseRepository;

    public TestInserareTagProduse() {
        this.tagproduseRepository = new TagproduseRepository();
    }

    public void insertTags(int numberOfTags) {
        tagproduseRepository.beginTransaction();
        try {
            for (int i = 1; i <= numberOfTags; i++) {
                Tagproduse tagproduse = new Tagproduse();
                tagproduse.setElement("Tag" + i);
                tagproduseRepository.create(tagproduse);
                System.out.println("Inserted Tag: " + tagproduse.getElement());
            }
            tagproduseRepository.commitTransaction();
        } catch (Exception e) {
            tagproduseRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm(){
        tagproduseRepository.closeEntityManager();
    }
}
