package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Segment;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class SegmentRepository extends AbstractRepository<Segment> {
    @Override
    protected Class<Segment> getEntityClass() {
        return Segment.class;
    }

    public Segment findById(Integer id) {
        return getEm().find(Segment.class, id);
    }

    public void addSegment(Segment segment) {
        try {
            beginTransaction();
            create(segment);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateSegment(Segment segment) {
        try {
            beginTransaction();
            update(segment);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteSegment(Segment segment) {
        try {
            beginTransaction();
            delete(segment);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}