package ua.khpi.oop.malokhvii05.common.specification;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public interface Specification<T> extends Iterable<Specification<T>> {

    boolean isSatisfiedBy(@Nullable T candidate);

    default @Nonnull Specification<T> or(
            @Nonnull final Specification<T> specification) {
        return Specifications.newDisjunctionSpecification(this, specification);
    }

    default @Nonnull Specification<T> and(
            @Nonnull final Specification<T> specification) {
        return Specifications.newConjuctionSpecification(this, specification);
    }

    default @Nonnull Specification<T> not() {
        return Specifications.newNegationSpecification(this);
    }

    default @Nonnull Predicate<T> toPredicate() {
        return new Predicate<T>() {

            @Override
            public boolean apply(@Nonnull T candidate) {
                return isSatisfiedBy(candidate);
            }
        };
    }

    default @Nonnull java.util.function.Predicate<T> toUtilPredicate() {
        return new java.util.function.Predicate<T>() {

            @Override
            public boolean test(@Nonnull T candidate) {
                return isSatisfiedBy(candidate);
            }
        };
    }

    default @Nonnull List<Specification<T>> getSpecifications() {
        return Lists.newArrayList(this);
    }

    default @Override Iterator<Specification<T>> iterator() {
        return Collections.emptyListIterator();
    }
}
