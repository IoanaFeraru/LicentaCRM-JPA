package org.licenta2024JPA.Metamodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractRepository<T> {
    private static jakarta.persistence.EntityManager em = jakarta.persistence.Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();

    public void beginTransaction() {
        getEm().getTransaction().begin();
    }

    public void commitTransaction() {
        getEm().getTransaction().commit();
    }

    public void rollbackTransaction() {
        if (getEm().getTransaction().isActive()) {
            getEm().getTransaction().rollback();
        }
    }

    public T create(T entity) {
        getEm().persist(entity);
        return entity;
    }

    public T update(T entity) {
        return getEm().merge(entity);
    }

    public void delete(T entity) {
        getEm().remove(entity);
    }

    public T findById(Class<T> entityClass, Object id) {
        return getEm().find(entityClass, id);
    }

    public List<T> findWithNamedQuery(String queryName) {
        TypedQuery<T> query = getEm().createNamedQuery(queryName, getEntityClass());
        return query.getResultList();
    }

    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        TypedQuery<T> query = getEm().createNamedQuery(queryName, getEntityClass());
        query.setMaxResults(resultLimit);
        return query.getResultList();
    }

    public List<T> findAll() {
        String jpql = "SELECT e FROM " + getEntityClass().getSimpleName() + " e";
        TypedQuery<T> query = getEm().createQuery(jpql, getEntityClass());
        return query.getResultList();
    }

    public List<T> findWithDynamicQuery(String jpql, Object... params) {
        TypedQuery<T> query = getEm().createQuery(jpql, getEntityClass());
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

    protected abstract Class<T> getEntityClass();

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        AbstractRepository.em = em;
    }

    public void closeEntityManager() {
        if (em.isOpen()) {
            em.close();
        }
    }
}
