package ua.khpi.oop.malokhvii05.common.repository;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.khpi.oop.malokhvii05.common.specification.Specification;

public interface EntityCriteria<T> extends Specification<T> {

    @Nonnull
    Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
