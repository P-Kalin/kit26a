package ua.khpi.oop.malokhvii05.common.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public interface CrudRepository<K, V extends Entity<K>> {

    default void delete(final V entity) {
        deleteById(checkNotNull(entity).getId());
    }

    default void deleteAll(final EntityCriteria<V> criteria) {
        findAll(criteria).forEach(this::delete);
    }

    default void deleteAll(final Iterable<V> entities) {
        checkNotNull(entities).forEach(this::delete);
    }

    @SuppressWarnings("unchecked")
    default void deleteAll(final V... entities) {
        deleteAll(Lists.newArrayList(checkNotNull(entities)));
    }

    void deleteById(K id);

    boolean exist(V entity);

    boolean existById(K id);

    List<V> findAll();

    default List<V> findAll(final EntityCriteria<V> criteria) {
        return findAll().stream()
                .filter(checkNotNull(criteria).toUtilPredicate())
                .collect(Collectors.toList());
    }

    default Optional<V> findById(final K id) {
        return findAll().stream().filter(entity -> entity.getId().equals(id))
                .findAny();
    }

    void save(V entity);

    default void saveAll(final Iterable<V> entities) {
        checkNotNull(entities).forEach(this::save);
    }

    @SuppressWarnings("unchecked")
    default void saveAll(final V... entities) {
        saveAll(Lists.newArrayList(checkNotNull(entities)));
    }

    void update(V entity);

    default void updateAll(final Iterable<V> entities) {
        checkNotNull(entities).forEach(this::update);
    }

    @SuppressWarnings("unchecked")
    default void updateAll(final V... entities) {
        updateAll(Lists.newArrayList(checkNotNull(entities)));
    }
}
