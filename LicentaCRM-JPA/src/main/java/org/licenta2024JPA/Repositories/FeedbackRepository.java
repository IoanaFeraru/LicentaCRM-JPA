package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Feedback.Feedback;
import org.licenta2024JPA.Entities.Feedback.FeedbackId;
import org.licenta2024JPA.Entities.Produs;
import org.licenta2024JPA.Metamodels.AbstractRepository;

import jakarta.persistence.TypedQuery;

public class FeedbackRepository extends AbstractRepository<Feedback> {
    @Override
    protected Class<Feedback> getEntityClass() {
        return Feedback.class;
    }

    public Feedback findById(FeedbackId id) {
        return getEm().find(Feedback.class, id);
    }

    public void addFeedback(Feedback feedback) {
        try {
            beginTransaction();
            create(feedback);
            updateProductRating(feedback.getCodprodus());
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateFeedback(Feedback feedback) {
        try {
            beginTransaction();
            update(feedback);
            updateProductRating(feedback.getCodprodus());
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteFeedback(Feedback feedback) {
        try {
            beginTransaction();
            delete(feedback);
            updateProductRating(feedback.getCodprodus());
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    private void updateProductRating(Produs produs) {
        TypedQuery<Double> query = getEm().createQuery(
                "SELECT AVG(f.rating) FROM Feedback f WHERE f.codprodus = :codprodus", Double.class);
        query.setParameter("codprodus", produs);
        Double averageRating = query.getSingleResult();

        produs.setRating(averageRating != null ? averageRating : 0.0);
        getEm().merge(produs);
    }
}