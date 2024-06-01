package Test.Inserari;

import org.licenta2024JPA.Entities.Tagproduse;
import org.licenta2024JPA.Repositories.TagproduseRepository;

public class TestInserareTagProduse {

    private final TagproduseRepository tagproduseRepository;

    public TestInserareTagProduse() {
        this.tagproduseRepository = new TagproduseRepository();
    }

    public void insertTags() {
        for (int i = 1; i <= 500; i++) {
            Tagproduse tagproduse = new Tagproduse();
            tagproduse.setElement("Tag" + i);

            try {
                tagproduseRepository.addTagproduse(tagproduse);
                System.out.println("Inserted Tag: " + tagproduse.getElement());
            } catch (Exception e) {
                System.err.println("Error inserting Tag: " + tagproduse.getElement());
                e.printStackTrace();
            }
        }
        // Closing the EntityManager to release resources
        tagproduseRepository.getEm().close();
    }
}
