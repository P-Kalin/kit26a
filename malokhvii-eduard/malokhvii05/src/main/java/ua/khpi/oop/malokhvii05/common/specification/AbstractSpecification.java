package ua.khpi.oop.malokhvii05.common.specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;

public abstract class AbstractSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -6751585455772871305L;

    @Override
    public @Nonnull Iterator<Specification<T>> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public final @Nonnull Specification<T> or(
            @Nonnull final Specification<T> specification) {
        return Specifications.newDisjunctionSpecification(this, specification);
    }

    @Override
    public final @Nonnull Specification<T> and(
            @Nonnull final Specification<T> specification) {
        return Specifications.newConjuctionSpecification(this, specification);
    }

    @Override
    public final @Nonnull Specification<T> not() {
        return Specifications.newNegationSpecification(this);
    }

    @Override
    public @Nonnull List<Specification<T>> getSpecifications() {
        return Arrays.asList(this);
    }

    @Override
    public final @Nonnull Predicate<T> toPredicate() {
        return new Predicate<T>() {

            @Override
            public boolean apply(@Nonnull final T candidate) {
                return isSatisfiedBy(candidate);
            }
        };
    }

    @Override
    public @Nonnull String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}
