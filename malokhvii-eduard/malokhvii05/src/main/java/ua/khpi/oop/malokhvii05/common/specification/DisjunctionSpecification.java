package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class DisjunctionSpecification<T> extends AbstractSpecificationPair<T> {

    private static final long serialVersionUID = 1093108316049034877L;

    DisjunctionSpecification(@Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        super(specification1, specification2);
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return getFirstSpecification().isSatisfiedBy(candidate)
                || getSecondSpecification().isSatisfiedBy(candidate);
    }
}
