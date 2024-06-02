package Test.Inserari;

import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Repositories.SegmentRepository;

import java.time.LocalDate;

public class TestInserareSegmente {

    private SegmentRepository segmentRepository;

    public TestInserareSegmente() {
        this.segmentRepository = new SegmentRepository();
    }

    public void insertSegmente(int numberOfSegments) {
        segmentRepository.beginTransaction();
        try {
            for (int i = 1; i <= numberOfSegments; i++) {
                Segment segment = new Segment();
                segment.setNume("Segment" + i);
                segment.setDatacreare(LocalDate.now().toString());
                segment.setCriterii("Criterii" + i);

                segmentRepository.create(segment);
                System.out.println("Inserted Segment: " + segment.getNume());
            }
            segmentRepository.commitTransaction();
        } catch (Exception e) {
            segmentRepository.rollbackTransaction();
            throw e;
        }
    }

    public void closeEm() {
        segmentRepository.closeEntityManager();
    }
}
