package io.tatagulov.badproject.web.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class CrudRepository<T,K> {

    @PersistenceContext
    EntityManager entityManager;

    public T findById(K id) {
        return entityManager.find(getType(), id);
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public abstract Class<T> getType();

    public List<T> all() {
        Class<T> type = getType();
        String jpql = String.format("select a from %s a",type.getSimpleName());
        return entityManager.createQuery(jpql).getResultList();
    }
}
