package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class DisjunctionSpecification<T> extends SpecificationPair<T> {

    DisjunctionSpecification(@Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        super(specification1, specification2);
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return getFirst().isSatisfiedBy(candidate)
                || getSecond().isSatisfiedBy(candidate);
    }
}
