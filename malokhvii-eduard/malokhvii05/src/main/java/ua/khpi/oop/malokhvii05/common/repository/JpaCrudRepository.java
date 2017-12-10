package ua.khpi.oop.malokhvii05.common.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

public class JpaCrudRepository<K, V extends Entity<K>>
        implements CrudRepository<K, V> {

    private final EntityManager entityManager;

    private final Class<V> entityType;

    @Inject
    public JpaCrudRepository(final EntityManager entityManager,
            final Class<V> entityType) {
        this.entityManager = checkNotNull(entityManager);
        this.entityType = checkNotNull(entityType);
    }

    @Override
    public void delete(final V entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(final K id) {
        final Optional<V> entity = findById(id);
        if (entity.isPresent()) {
            entityManager.remove(entity);
        }
    }

    @Override
    public boolean exist(final V entity) {
        return entityManager.contains(entity);
    }

    @Override
    public boolean existById(final K id) {
        return entityManager.find(entityType, id) != null;
    }

    @Override
    public List<V> findAll() {
        final CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        final CriteriaQuery<V> criteriaQuery = criteriaBuilder
                .createQuery(entityType);

        final Root<V> root = criteriaQuery.from(entityType);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<V> findAll(final EntityCriteria<V> criteria) {
        final CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        final CriteriaQuery<V> criteriaQuery = criteriaBuilder
                .createQuery(entityType);

        final Root<V> root = criteriaQuery.from(entityType);
        final Predicate predicate = criteria.toPredicate(root, criteriaBuilder);
        criteriaQuery.where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<V> findById(final K id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Override
    public void save(final V entity) {
        if (!entityManager.contains(entity)) {
            entityManager.persist(entity);
        }
        entityManager.merge(entity);
    }

    @Override
    public void update(final V entity) {
        entityManager.refresh(entity);
    }
}
